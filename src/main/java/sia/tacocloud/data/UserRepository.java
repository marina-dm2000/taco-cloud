package sia.tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.User;

/**
 * Интерфейс для хранения объектов User.
 * Первый параметр - тип объектов, которые будут храниться в репозитории.
 * Второй параметр - тип поля идентификатора хранимого объекта
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
