package pl.r80.rsk.Employment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import pl.r80.rsk.Firm.Firm;

import java.util.List;

@Controller
public interface EmploymentRepository extends CrudRepository<Employment, Integer> {

    List<Employment> findByFirm(Firm firm);
}
