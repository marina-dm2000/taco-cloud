package sia.tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sia.tacocloud.Taco;

/**
 * Интерфейс для хранения объектов Taco.
 * Первый параметр - тип объектов, которые будут храниться в репозитории.
 * Второй параметр - тип поля идентификатора хранимого объекта
 */
@Repository
public interface TacoRepository extends CrudRepository<Taco, Long> {
}