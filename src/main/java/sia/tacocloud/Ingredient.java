package sia.tacocloud;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

/**
 * Класс, представляющий ингредиенты тако
 */
@Data
@Entity
@AllArgsConstructor // упрощает создание объекта со всеми инициализированными свойствами
// генерирует приватный конструктор без параметров
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor
public class Ingredient {
    @Id
    private String id;
    private String name;
    private Type type;

    public enum Type {
        WRAP,
        PROTEIN,
        VEGGIES,
        CHEESE,
        SAUCE
    }
}
