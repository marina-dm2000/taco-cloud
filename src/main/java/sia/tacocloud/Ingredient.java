package sia.tacocloud;

import lombok.Data;

/**
 * Класс, представляющий ингредиенты тако
 */
@Data
public class Ingredient {
    private final String id;
    private final String name;
    private final Type type;

    public enum Type {
        WRAP,
        PROTEIN,
        VEGGIES,
        CHEESE,
        SAUCE
    }
}
