package sia.tacocloud;

import jakarta.persistence.*;
import lombok.*;

/**
 * Класс, представляющий ингредиенты тако
 */
@Data
@Entity
@Table(name = "ingredient", schema = "public")
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
