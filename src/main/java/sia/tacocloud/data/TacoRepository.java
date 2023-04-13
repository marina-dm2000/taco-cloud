package sia.tacocloud.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sia.tacocloud.Taco;
@Repository
public interface TacoRepository extends CrudRepository<Taco, Long> {
}