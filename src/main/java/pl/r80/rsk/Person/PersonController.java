package pl.r80.rsk.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.r80.rsk.Employment.Employment;
import pl.r80.rsk.Employment.EmploymentService;
import pl.r80.rsk.Firm.Firm;
import pl.r80.rsk.Firm.FirmService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/persons")
public class PersonController implements WebMvcConfigurer {

    @Autowired
    private HttpSession httpSession;

    private final PersonService personService;
    private final FirmService firmService;
    private final EmploymentService employmentService;

    @Autowired
    public PersonController(PersonService personService, FirmService firmService, EmploymentService employmentService) {
        this.personService = personService;
        this.firmService = firmService;
        this.employmentService = employmentService;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/osoby").setViewName("osoby");
        registry.addViewController("/osoby_add").setViewName("osoby_add");
        registry.addViewController("/osoby_update").setViewName("osoby_update");
        registry.addViewController("/osoby_delete").setViewName("osoby_delete");
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("firmKontekst", httpSession.getAttribute("KONTEKST"));
        List<Person> personIterable = personService.findAll();
        model.addAttribute("persons", personIterable);
        return "osoby";
    }

    @GetMapping("/readone/{id}")
    public String readOneById(@PathVariable Integer id, Model model) {
        model.addAttribute("firmKontekst", httpSession.getAttribute("KONTEKST"));
        Optional<Person> person = personService.findById(id);
        if (person.isPresent()) {
            model.addAttribute("person", person.get());
        } else {
            throw new IllegalPersonException("No such person");
        }
        return "osoby_read";
    }

    @GetMapping("/update/{id}")
    public String updateById(@PathVariable Integer id, Model model) {
        model.addAttribute("firmKontekst", httpSession.getAttribute("KONTEKST"));
        Optional<Person> person = personService.findById(id);
        if (person.isPresent()) {
            model.addAttribute("person", person.get());
        } else {
            throw new IllegalPersonException("No such person");
        }
        return "osoby_update";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id, Model model) {
        model.addAttribute("firmKontekst", httpSession.getAttribute("KONTEKST"));
        Optional<Person> person = personService.findById(id);
        if (person.isPresent()) {
            model.addAttribute("person", person.get());
        } else {
            throw new IllegalPersonException("No such person");
        }
        personService.delete(person);
        return "logged";
    }

    @GetMapping("/add")
    public String addPerson(@ModelAttribute Person person, Model model) {
        model.addAttribute("firmKontekst", httpSession.getAttribute("KONTEKST"));
        return "osoby_add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute @Valid Person person, BindingResult bindingResult, Model model) {
        model.addAttribute("firmKontekst", httpSession.getAttribute("KONTEKST"));
        //TODO walidacja peselu
//        if (!validate(person.pesel)) {
//            bindingResult.addError(new ObjectError("pesel", "Pesel niepoprawny");
//            //return "osoby_add";
//        }
        if (bindingResult.hasErrors()) {
            return "osoby_add";
        }
        personService.save(person);
        return "osoby_read";
    }

    @PostMapping("/update")
    public String update(@Valid Person person, BindingResult bindingResult, Model model){
        model.addAttribute("firmKontekst", httpSession.getAttribute("KONTEKST"));
        personService.update(person);
        return "osoby_read";
    }

    @GetMapping("/byfirm")
    public String findAllByFirm(Model model) {
        model.addAttribute("firmKontekst", httpSession.getAttribute("KONTEKST"));
        Firm firm = (Firm) httpSession.getAttribute("KONTEKST");
        List<Employment> employmentList = employmentService.findByFirm(firm);
        model.addAttribute("employments", employmentList);
        return "osoby_firma";
    }

}
