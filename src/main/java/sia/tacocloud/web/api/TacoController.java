package sia.tacocloud.web.api;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.Taco;
import sia.tacocloud.TacoOrder;
import sia.tacocloud.data.OrderRepository;
import sia.tacocloud.data.TacoRepository;

import java.util.Optional;

@RestController // конечная точка REST
@RequestMapping(path = "/api/tacos", produces = "application/json") //  любой метод-обработчик будет обрабатывать
                                                                       // запросы, только если запрос клиента содержит
                                                                        // заголовок Accept со значением "application/json"
@CrossOrigin(origins = "http://tacocloud:8080") // разрешает обработку межсайтовых запросов
public class TacoController {
    private final TacoRepository tacoRepo;
    private OrderRepository orderRepo;

    public TacoController(TacoRepository tacoRepo) {
        this.tacoRepo = tacoRepo;
    }

    /**
     * Извлекает и возвращает последние рецепты тако
     *
     * @return последние рецепты тако
     */
    @GetMapping(params = "recent")
    public Iterable<Taco> recentTacos() {
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        return tacoRepo.findAll(page).getContent();
    }

    /**
     *
     * @param id переменная-заполнитель
     * @return рецепт тако по его идентификатору
     */
    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        Optional<Taco> optTaco = tacoRepo.findById(id);
        return optTaco.map(taco -> new ResponseEntity<>(taco, HttpStatus.OK)).orElseGet(()
                -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = "application/json") // определяет формат входных данных
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoRepo.save(taco);
    }

    @PatchMapping(path = "/{orderId}", consumes = "application/json")
    public TacoOrder patchOrder(@PathVariable("orderId") Long orderId, @RequestBody TacoOrder patch) {
        TacoOrder order = orderRepo.findById(orderId).get();
        if (patch.getDeliveryName() != null) {
            order.setDeliveryName(patch.getDeliveryName());
        }

        if (patch.getDeliveryStreet() != null) {
            order.setDeliveryStreet(patch.getDeliveryStreet());
        }

        if (patch.getDeliveryCity() != null) {
            order.setDeliveryCity(patch.getDeliveryCity());
        }

        if (patch.getDeliveryState() != null) {
            order.setDeliveryState(patch.getDeliveryState());
        }

        if (patch.getDeliveryZip() != null) {
            order.setDeliveryZip(patch.getDeliveryZip());
        }

        if (patch.getCcNumber() != null) {
            order.setCcNumber(patch.getCcNumber());
        }

        if (patch.getCcExpiration() != null) {
            order.setCcExpiration(patch.getCcExpiration());
        }

        if (patch.getCcCvv() != null) {
            order.setCcCvv(patch.getCcCvv());
        }

        return orderRepo.save(order);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            orderRepo.deleteById(orderId);
        } catch (EmptyResultDataAccessException ignored) {}
    }
}