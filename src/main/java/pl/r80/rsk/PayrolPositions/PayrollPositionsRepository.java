package pl.r80.rsk.PayrolPositions;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.r80.rsk.Firm.Firm;
import pl.r80.rsk.Payroll.Payroll;
import pl.r80.rsk.Person.Person;

import java.util.List;

@Repository
public interface PayrollPositionsRepository extends CrudRepository<PayrollPositions, Integer> {

    List<PayrollPositions> findByFirm(Firm firm);

    List<PayrollPositions> findByPerson(Person person);

    List<PayrollPositions> findByPayroll(Payroll payroll);
}
