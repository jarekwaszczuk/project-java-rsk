package pl.r80.rsk.Payroll;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.r80.rsk.Firm.Firm;

import java.util.List;

@Repository
public interface PayrollRepository extends CrudRepository<Payroll, Integer> {

    List<Payroll> findByFirm(Firm firm);
}
