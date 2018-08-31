package pl.r80.rsk.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return (List<Person>) personRepository.findAll();
    }

    public Optional<Person> findById(Integer id) {
        return personRepository.findById(id);
    }

    public void save(Person person) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        person.setChangeLog(timestamp);
        personRepository.save(person);
    }

    public void update(Person person) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        person.setChangeLog(timestamp);
        personRepository.save(person);
    }

    public void delete(Optional<Person> person) {
        personRepository.delete(person.get());
    }
}
