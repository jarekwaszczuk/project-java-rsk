package pl.r80.rsk;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirmService {
    private final FirmRepository firmRepository;

    public FirmService(FirmRepository firmRepository) {
        this.firmRepository = firmRepository;
    }

    public List<Firm> findAll(){
        return (List<Firm>) firmRepository.findAll();
    }
}
