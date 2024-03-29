package sia.tacocloud;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Класс, представляющий заказ
 */
@Data
@Entity
@Table(name = "taco_order", schema = "public")
public class TacoOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // дата создания заказа
    private Date placedAt = new Date();

    @NotBlank(message = "Delivery name is required") // Все ли поля заполнены
    private String deliveryName;
    @NotBlank(message = "Street is required")
    private String deliveryStreet;
    @NotBlank(message = "City is required")
    private String deliveryCity;
    @NotBlank(message = "State is required")
    private String deliveryState;
    @NotBlank(message = "Zip code is required")
    private String deliveryZip;

    @CreditCardNumber(message = "Not a valid credit card number") // корректность номера карты
    private String ccNumber;

    // проверка формата даты и времени ММ/ГГ
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$", message = "Must be formatted MM/YY")
    private String ccExpiration;

    // проверка, что значение содержит ровно 3 цифры
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCvv;

    // все тако в списке относятся к одному заказу.
    // При удалении заказа все связанные с ним тако тоже будут удалены
    @OneToMany(cascade = CascadeType.ALL)
    private transient List<Taco> tacos = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "\"user\"")
    private User user;

    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }
}
