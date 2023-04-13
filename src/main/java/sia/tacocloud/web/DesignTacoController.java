package sia.tacocloud.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.Ingredient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import sia.tacocloud.Ingredient.Type;
import sia.tacocloud.Taco;
import sia.tacocloud.TacoOrder;
import sia.tacocloud.data.IngredientRepository;
import sia.tacocloud.data.TacoRepository;

@Slf4j // журналирование
@Controller
@RequestMapping("/design") // класс обрабатывает запросы с путем design
@SessionAttributes("tacoOrder") // класс tacoOrder поддерживается на уровне сеанса
public class DesignTacoController {
    private final IngredientRepository ingredientRepo;
    @Autowired
    private TacoRepository tacoRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @ModelAttribute
    public void addIngredientsModel(Model model) {

        // извлекает все ингредиенты из БД
        Iterable<Ingredient> ingredients = ingredientRepo.findAll();

        /*
        * Фильтруем ингредиенты по типам, используя метод filterByType()
        */
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            // добавляет список ингредиентов в модель
            model.addAttribute(type.toString().toLowerCase(), filterByType((List<Ingredient>) ingredients, type));
        }
    }

    /**
     * Хранит состояние собираемого заказа
     * @return новый заказ
     */
    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    /**
     * Метод для обработки запроса с путем design
     * @param taco тако с проверкой корректности введенных данных
     * @param errors проверяет наличие ошибок
     * @param tacoOrder используется tacoOrder, который был помещен в модель методом order()
     * @return имя представления
     */
    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
        // если найдены ошибки в заполнении форму, возвращает имя представления design
        if (errors.hasErrors()) {
            return "design";
        }

        // добавление тако в заказ
        tacoOrder.addTaco(taco);
        tacoRepo.save(taco);
        log.info("Processing taco: {}", taco);

        return "redirect:/orders/current";
    }

    /**
     * Фильтрует ингредиенты по типу
     * @param ingredients список ингредиентов
     * @param type тип ингредиентов, по которому необходима фильтрация
     * @return список ингредиентов с типом type
     */
    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
