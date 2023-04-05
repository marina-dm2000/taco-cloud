package sia.tacocloud.data;

import sia.tacocloud.Ingredient;

import java.util.Optional;

/**
 * Интерфейс для хранения объектов Ingredient должен поддерживать следующие операции:
 * 1. получение всех ингредиентов в виде коллекции объектов Ingredient;
 * 2. получение одного ингредиента по идентификатору;
 * 3. сохранение объекта Ingredient
 */
public interface IngredientRepository {
    // получение всех ингредиентов в виде коллекции объектов Ingredient
    Iterable<Ingredient> findAll();
    // получение одного ингредиента по идентификатору
    Optional<Ingredient> findById(String id);
    // сохранение объекта Ingredient
    Ingredient save(Ingredient ingredient);
}
