package sia.tacocloud;

import jakarta.persistence.Table;
import lombok.Data;

/**
 * Класс, определяющий связь между Taco и Ingredient
 */
@Data
@Table(name = "ingredient_ref", schema = "public")
public class IngredientRef {
    private final String ingredient;
}
