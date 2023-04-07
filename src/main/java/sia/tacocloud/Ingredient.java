package sia.tacocloud;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;

/**
 * Класс, представляющий ингредиенты тако
 */
@Data
@Entity
@AllArgsConstructor // упрощает создание объекта со всеми инициализированными свойствами
// генерирует защищенный конструктор без параметров
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ingredient {
    @Id
    private String id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Type type;

    public enum Type {
        WRAP,
        PROTEIN,
        VEGGIES,
        CHEESE,
        SAUCE
    }
}
