package sia.tacocloud;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

/**
 * Класс, представляяющий рецепт
 */
@Data
public class Taco {
    @NotNull // непустое поле
    @Size(min = 5, message = "Name must be at least 5 characters long") // минимальный размер поля 5
    private String name;

    @NotNull
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;
}
