package sia.tacocloud.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import sia.tacocloud.Taco;
import sia.tacocloud.TacoOrder;
import sia.tacocloud.data.OrderRepository;
import sia.tacocloud.data.TacoRepository;

/**
 * Контроллер, представляющий форму заказа тако
 */
@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {
    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private TacoRepository tacoRepo;

    public OrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    /**
     * Обрабатывает запросы с путем /orders/current
     *
     * @return имя нового представления
     */
    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    /**
     * Метод для обработки запроса с путем orders
     *
     * @param order         заказ с проверкой корректности введенных данных
     * @param errors        проверяет наличие ошибок
     * @param sessionStatus статус текущей сессии
     * @return имя представления
     */
    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus) {
        if (errors.hasErrors()) {
            return "orderForm";
        }

        // чтобы в БД у тако указывался номер заказа, необходимо сначала сохранить заказ со всеми тако,
        orderRepo.save(order);
        // получить все тако в заказе
        order.getTacos().forEach(taco -> {taco.setTacoOrder(order);});
        // затем каждый тако из заказа сохранить в БД
        tacoRepo.saveAll(order.getTacos());
        // когда пользователь создаст тако,
        // сеанс будет очищен и готов к приему нового заказа
        sessionStatus.setComplete();

        // перенаправляет на домашнюю страницу
        return "redirect:/";
    }
}
