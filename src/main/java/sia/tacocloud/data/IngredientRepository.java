package sia.tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.Ingredient;

/**
 * Интерфейс для хранения объектов Ingredient должен поддерживать следующие операции:
 * 1. получение всех ингредиентов в виде коллекции объектов Ingredient;
 * 2. получение одного ингредиента по идентификатору;
 * 3. сохранение объекта Ingredient
 */
public interface IngredientRepository extends CrudRepository<Ingredient, String> {
    Ingredient findByName(String name);
}
