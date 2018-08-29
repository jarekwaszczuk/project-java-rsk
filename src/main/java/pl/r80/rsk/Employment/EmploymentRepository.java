package pl.r80.rsk.Employment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;

@Controller
public interface EmploymentRepository extends CrudRepository<Employment, Integer> {

}
