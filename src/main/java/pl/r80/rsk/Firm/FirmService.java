package pl.r80.rsk.Firm;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class FirmService {
    private final FirmRepository firmRepository;

    public FirmService(FirmRepository firmRepository) {
        this.firmRepository = firmRepository;
    }

    public List<Firm> findAll(){
        return (List<Firm>) firmRepository.findAll();
    }

    public Optional<Firm> findById(Integer id) {
        return firmRepository.findById(id);
    }

    public void save(Firm firm) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        firm.setChangeLog(timestamp);
        firmRepository.save(firm);
    }

}
