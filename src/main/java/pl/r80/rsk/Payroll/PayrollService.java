package pl.r80.rsk.Payroll;

import org.springframework.stereotype.Service;
import pl.r80.rsk.Firm.Firm;

import java.util.List;

@Service
public class PayrollService {

    private final PayrollRepository payrollRepository;

    public PayrollService(PayrollRepository payrollRepository) {
        this.payrollRepository = payrollRepository;
    }

    public Iterable<Payroll> findAll() {
        return payrollRepository.findAll();
    }

    public List<Payroll> findAllByFirm(Firm firm) {
        return payrollRepository.findByFirm(firm);
    }
}
