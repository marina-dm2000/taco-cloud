package sia.tacocloud.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Контроллер представления
 */
@Controller
public class WebConfig implements WebMvcConfigurer {
    @GetMapping("/") // Обрабатываем запросы с корневым путем /
    public String home() {
        return "home"; // Возвращает имя представления
    }

    /**
     *
     * @param registry объект для регистрации одного или нескольких контроллеров представлений
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login");
    }
}
