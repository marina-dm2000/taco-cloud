package sia.tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.Ingredient;

/**
 * Интерфейс для хранения объектов Ingredient.
 * Первый параметр - тип объектов, которые будут храниться в репозитории.
 * Второй параметр - тип поля идентификатора хранимого объекта
 */
public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
