package sia.tacocloud;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Класс, представляяющий рецепт
 */
@Data
@Entity
@RestResource(rel = "tacos", path = "tacos")
@Table(name = "taco", schema = "public")
public class Taco {
    @Id
    // На создание значения полагаемся на БД
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // дата создания тако
    private Date createdAt = new Date();

    @NotNull // непустое поле
    @Size(min = 5, message = "Name must be at least 5 characters long") // минимальный размер поля 5
    private String name;

    @NotNull
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    // объект Taco может включать в список несколько оъектов Ingredient,
    // один объект Ingredient может быть частью списков в нескольких объектах Taco
    @ManyToMany
    private transient List<Ingredient> ingredients = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "taco_order")
    private TacoOrder tacoOrder;
}
