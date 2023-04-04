package sia.tacocloud.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import sia.tacocloud.Taco;
import sia.tacocloud.TacoOrder;

/**
 * Контроллер, представляющий форму заказа тако
 */
@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {
    /**
     * Обрабатывает запросы с путем /orders/current
     * @return
     */
    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    /**
     * Метод для обработки запроса с путем orders
     * @param order заказ с проверкой корректности введенных данных
     * @param errors проверяет наличие ошибок
     * @param sessionStatus статус текущей сессии
     * @return имя представления
     */
    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "orderForm";
        }

        log.info("Order submitted: {}", order);
        // когда пользователь создаст тако,
        // сеанс будет очищен и готов к приему нового заказа
        sessionStatus.setComplete();

        // перенаправляет на домашнюю страницу
        return "redirect:/";
    }
}