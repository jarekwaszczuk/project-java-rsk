package pl.r80.rsk;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;

@Controller
public interface FirmRepository extends CrudRepository<Firm, Integer> {

}
