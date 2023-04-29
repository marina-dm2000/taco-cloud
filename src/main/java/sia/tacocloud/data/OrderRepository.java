package sia.tacocloud.data;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import sia.tacocloud.TacoOrder;
import sia.tacocloud.User;

import java.util.List;

/**
 * Интерфейс для хранения объектов Order.
 * Первый параметр - тип объектов, которые будут храниться в репозитории.
 * Второй параметр - тип поля идентификатора хранимого объекта
 */
public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
    List<TacoOrder> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
    @PreAuthorize("hasRole('ADMIN')")
    @Modifying
    @Query(
            value =
                    "delete from TacoOrder"
    )
    void deleteAllOrders();

    @PostAuthorize("hasRole('ADMIN') ||" +
            "returnObject.user.username == authentication.name")
    TacoOrder getTacoOrderById(long id);
}
