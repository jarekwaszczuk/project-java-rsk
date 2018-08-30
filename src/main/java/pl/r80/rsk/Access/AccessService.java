package pl.r80.rsk.Access;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccessService {

    public final AccessRepository accessRepository;

    @Autowired
    public AccessService(AccessRepository accessRepository) {
        this.accessRepository = accessRepository;
    }

    public Optional<Access> findUser(String user) {
        return accessRepository.findByUser(user);
    }
}
