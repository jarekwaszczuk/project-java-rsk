package pl.r80.rsk.Access;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessRepository extends CrudRepository<Access, Integer> {

    Optional<Access> findByUser(String user);
}
