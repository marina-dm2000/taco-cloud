package sia.tacocloud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import sia.tacocloud.data.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SecurityConfig {
    /**
     * bean-компонент, который используется при создании новых пользователей и при аутентификации
     *
     * @return BCryptPasswordEncoder - один из средств шифрования паролей
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Служба хранения учетных записей
     *
     * @param userRepo Репозиторий
     * @return находит пользователя с помощью репозитория и возвращает его.
     * В случае, когда user == null, бросает исключение
     */
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            sia.tacocloud.User user = userRepo.findByUsername(username);
            if (user != null) {
                return user;
            }

            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }

    /**
     * Запросы с путями /design и /orders будут обрабатываться, только если они отправлены
     * аутентифицированными пользователями; все другие запросы должны обрабатываться независимо от
     * факта аутентификации
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests()
                .requestMatchers("/design", "/orders").hasRole("USER")
                .requestMatchers("/", "/**").permitAll()
                .and()
                .formLogin() // настройка формы входа
                .and()
                .oauth2Login()
                .loginPage("/login")
                .defaultSuccessUrl("/design")
                .and()
                .logout()
                .and()
                .build();
    }
}
