package sia.tacocloud.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import sia.tacocloud.TacoOrder;
import sia.tacocloud.User;
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
    private OrderProps props;

    public OrderController(OrderRepository orderRepo, OrderProps props) {
        this.orderRepo = orderRepo;
        this.props = props;
    }

    /**
     * Обрабатывает запросы с путем /orders/current
     *
     * @return имя нового представления (HTML-разметку)
     */
    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @GetMapping
    public String ordersForUser(@AuthenticationPrincipal User user, Model model) {
        Pageable pageable = PageRequest.of(0, props.getPageSize());
        model.addAttribute("orders", orderRepo.findByUserOrderByPlacedAtDesc(user, pageable));

        return "orderList";
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
    public String processOrder(@Valid TacoOrder order, Errors errors,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user /* получаем текущего пользователя */) {
        if (errors.hasErrors()) {
            return "orderForm";
        }

        //

        /**
         * Этот способ получения текущего пользователя можно использовать
         * в любом месте приложения, а не только в методах контроллеров.
         *
         * User user = (User) SecurityContextHolder.getContext()
         * .getAuthentication()
         * .getPrincipal();
         */

        order.setUser(user);
        // чтобы в БД у тако указывался номер заказа, необходимо сначала сохранить заказ со всеми тако,
        orderRepo.save(order);
        // получить все тако в заказе
        order.getTacos().forEach(taco -> {
            taco.setTacoOrder(order);
        });
        // затем каждый тако из заказа сохранить в БД
        tacoRepo.saveAll(order.getTacos());
        // когда пользователь создаст тако,
        // сеанс будет очищен и готов к приему нового заказа
        sessionStatus.setComplete();

        // перенаправляет на домашнюю страницу
        return "redirect:/";
    }
}
