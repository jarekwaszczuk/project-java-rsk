package pl.r80.rsk.Employment;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class EmploymentService {

    private final EmploymentRepository employmentRepository;

    public EmploymentService(EmploymentRepository employmentRepository) {
        this.employmentRepository = employmentRepository;
    }

    public List<Employment> findAll() {
        return (List<Employment>) employmentRepository.findAll();
    }

    public Optional<Employment> findById(Integer id) {
        return employmentRepository.findById(id);
    }

    public void save(Employment employment) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        employment.setChangeLog(timestamp);
        employmentRepository.save(employment);
    }
}
