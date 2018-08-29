package pl.r80.rsk.Employment;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmploymentService {

    private final EmploymentRepository employmentRepository;

    public EmploymentService(EmploymentRepository employmentRepository) {
        this.employmentRepository = employmentRepository;
    }

    public List<Employment> findAll() {
        return (List<Employment>) employmentRepository.findAll();
    }
}
