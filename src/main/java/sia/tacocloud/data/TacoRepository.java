package sia.tacocloud.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sia.tacocloud.Taco;

import java.util.Optional;

/**
 * Интерфейс для хранения объектов Taco.
 * Первый параметр - тип объектов, которые будут храниться в репозитории.
 * Второй параметр - тип поля идентификатора хранимого объекта
 */
@Repository
public interface TacoRepository extends CrudRepository<Taco, Long> {
    Page<Taco> findAll(Pageable pageable);
}