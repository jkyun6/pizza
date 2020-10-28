package pizza;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SatisfactionViewRepository extends MongoRepository<SatisfactionView, Long> {


}