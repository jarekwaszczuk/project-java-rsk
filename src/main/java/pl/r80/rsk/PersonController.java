package pl.r80.rsk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
            model.addAttribute("firm", person.get());
        } else {
            throw new IllegalPersonException("No such person");
        }
        return "osoby_read";
    }

}
