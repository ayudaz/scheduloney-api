package scheduloney.api.repository;

import org.springframework.data.repository.CrudRepository;
import scheduloney.api.entities.Spent;

/**
 * Spent Repository
 * <p>
 * Created by Thomas Lelievre on 30/08/2017.
 */

public interface SpentRepository extends CrudRepository<Spent, Long> {

}
