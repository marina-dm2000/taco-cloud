package sia.tacocloud;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(HomeController.class) // Тест для HomeController
public class HomeControllerTest {
    @Autowired
    private MockMvc mockMvc; // Внедрить MockMvc

    @Test
    public void testHomePage() throws Exception {
        mockMvc.perform(get("/")) // выполнить запрос GET /
                .andExpect(status().isOk()) // ожидается код ответа HTTP 200
                .andExpect(view().name("home")) // ожидается имя представления home
                .andExpect(content().string(
                        containsString("Welcome to..."))); // ожидается наличие строки "Welcome to..."
    }
}
