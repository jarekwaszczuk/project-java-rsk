package pl.r80.rsk.PayrolPositions;

import org.springframework.stereotype.Service;
import pl.r80.rsk.Firm.Firm;
import pl.r80.rsk.Payroll.Payroll;

import java.util.List;
import java.util.Optional;

@Service
public class PayrollPositionsService {

    private final PayrollPositionsRepository payrollPositionsRepository;

     public PayrollPositionsService(PayrollPositionsRepository payrollPositionsRepository) {
        this.payrollPositionsRepository = payrollPositionsRepository;
    }

    public List<PayrollPositions> findByFirmAndPayroll(Firm firm, Payroll payroll) {
        return payrollPositionsRepository.findByFirmAndPayroll(firm, payroll);
    }

    public Optional<PayrollPositions> findById(Integer id) {
         return payrollPositionsRepository.findById(id);
    }
}
