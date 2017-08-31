package scheduloney.api.repository;

import org.springframework.data.repository.CrudRepository;
import scheduloney.api.entities.Account;

/**
 * Account Repository
 * <p>
 * Created by Thomas Lelievre on 31/08/2017.
 */
public interface AccountRepository extends CrudRepository<Account, Long> {
}
