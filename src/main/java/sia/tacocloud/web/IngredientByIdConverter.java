package sia.tacocloud.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import sia.tacocloud.Ingredient;
import sia.tacocloud.Ingredient.Type;
import sia.tacocloud.data.IngredientRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс создается как bean-компонент в контексте приложения Spring
 *
 * Преобразование строк в объекты Ingredient
 */
@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {
    private IngredientRepository ingredientRepo;

    @Autowired
    public IngredientByIdConverter(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    /**
     * Получает значение типа String и преобразует его в Ingredient
     *
     * @param id искомого ингредиента
     * @return ингредиент по его id
     */
    @Override
    public Ingredient convert(String id) {
        return ingredientRepo.findById(id).orElse(null);
    }
}
