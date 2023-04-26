package sia.tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.TacoOrder;

import java.util.List;

/**
 * Интерфейс для хранения объектов Order.
 * Первый параметр - тип объектов, которые будут храниться в репозитории.
 * Второй параметр - тип поля идентификатора хранимого объекта
 */
public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
}
