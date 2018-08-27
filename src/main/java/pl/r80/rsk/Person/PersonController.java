package pl.r80.rsk.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/persons")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String findAll(Model model) {

        List<Person> personIterable = personService.findAll();
        model.addAttribute("persons", personIterable);
        return "osoby";
    }

    @GetMapping("/readone/{id}")
    public String readOneById(@PathVariable Integer id, Model model) {
        Optional<Person> person = personService.findById(id);
        if (person.isPresent()) {
            model.addAttribute("person", person.get());
        } else {
            throw new IllegalPersonException("No such person");
        }
        return "osoby_read";
    }

    @GetMapping("/update/{id}")
    public String updatePerson(@PathVariable Integer id, Model model) {
        Optional<Person> person = personService.findById(id);
        if (person.isPresent()) {
            model.addAttribute("person", person.get());
        } else {
            throw new IllegalPersonException("No such person");
        }
        return "osoby_update";
    }


    @GetMapping("/delete/{id}")
    public String deletePerson(@PathVariable Integer id, Model model) {
        Optional<Person> person = personService.findById(id);
        if (person.isPresent()) {
            model.addAttribute("person", person.get());
        } else {
            throw new IllegalPersonException("No such person");
        }
        return "osoby_delete";
    }

    //todo
    @PostMapping("/add")
    public void add(){

    }

    //todo
    @PostMapping("/update")
    public void update(){

    }

    //todo
    @PostMapping("/delete")
    public void delete(){

    }
}
