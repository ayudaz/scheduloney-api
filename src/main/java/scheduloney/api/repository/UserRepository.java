package scheduloney.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import scheduloney.api.entities.User;

import java.util.List;

/**
 * User repository
 * <p>
 * Created by Thomas Lelievre on 30/08/2017.
 */

public interface UserRepository extends CrudRepository<User, Long> {

}
