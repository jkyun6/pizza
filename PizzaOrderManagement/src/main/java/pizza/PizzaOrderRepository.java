package pizza;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.Query;
;import java.util.List;

public interface PizzaOrderRepository extends JpaRepository<PizzaOrder, Long> {

}