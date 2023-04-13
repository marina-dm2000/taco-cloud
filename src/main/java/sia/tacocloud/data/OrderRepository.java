package sia.tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.TacoOrder;

import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
}
