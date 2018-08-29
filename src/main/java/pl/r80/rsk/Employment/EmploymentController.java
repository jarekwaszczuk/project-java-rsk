package pl.r80.rsk.Employment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.r80.rsk.Firm.Firm;
import pl.r80.rsk.Firm.FirmRepository;
import pl.r80.rsk.Person.Person;
import pl.r80.rsk.Person.PersonRepository;
import pl.r80.rsk.Person.PersonService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/employments")
public class EmploymentController implements WebMvcConfigurer {

    public final EmploymentService employmentService;
    public final PersonRepository personRepository;
    public final FirmRepository firmRepository;

    @Autowired
    public EmploymentController(EmploymentService employmentService, PersonRepository personRepository, FirmRepository firmRepository) {
        this.employmentService = employmentService;
        this.personRepository = personRepository;
        this.firmRepository = firmRepository;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/zatrudnienie").setViewName("zatrudnienie");
        registry.addViewController("/zatrudnienie_read").setViewName("zatrudnienie_read");
        registry.addViewController("/zatrudnienie_update").setViewName("zatrudnienie_update");
        registry.addViewController("/zatrudnienie_delete").setViewName("zatrudnienie_delete");
        registry.addViewController("/zatrudnienie_add").setViewName("zatrudnienie_add");
    }

    @GetMapping
    public String findAll(Model model) {

        List<Employment> employmentIterable = employmentService.findAll();
        model.addAttribute("employments", employmentIterable);
        return "zatrudnienie";
    }

    @GetMapping("/readone/{id}")
    public String readOneById(@PathVariable Integer id, Model model) {
        Optional<Employment> employment = employmentService.findById(id);
        if (employment.isPresent()) {
            model.addAttribute("employment", employment.get());
        } else {
            throw new IllegalEmploymentException("No such employment");
        }
        return "zatrudnienie_read";
    }

    @GetMapping("/update/{id}")
    public String updateEmployment(@PathVariable Integer id, Model model) {
        Optional<Employment> employment = employmentService.findById(id);
        if (employment.isPresent()) {
            model.addAttribute("employment", employment.get());
        } else {
            throw new IllegalEmploymentException("No such employment");
        }
        return "zatrudnienie_update";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployment(@PathVariable Integer id, Model model) {
        Optional<Employment> firm = employmentService.findById(id);
        if (firm.isPresent()) {
            model.addAttribute("firm", firm.get());
        } else {
            throw new IllegalEmploymentException("No such employment");
        }
        return "zatrudnienie_delete";
    }

    @GetMapping("/add")
    public String addEmployment(@ModelAttribute Employment employment, Model model) {
        Iterable<Person> persons = personRepository.findAll();
        Iterable<Firm> firms = firmRepository.findAll();
        AgreementType[] agreementTypes = AgreementType.values();
        model.addAttribute("agreementTypes", agreementTypes);
        model.addAttribute("persons", persons);
        model.addAttribute("firms", firms);
        return "zatrudnienie_add";
    }

    @PostMapping("/add")
    public String add(@RequestParam Integer personId, @RequestParam AgreementType agreementType, @RequestParam Integer firmId, @RequestParam String hireDate,
                      @ModelAttribute @Valid Employment employment, BindingResult bindingResult, Model model) {
        Optional<Person> personDB = personRepository.findById(personId);
        Person person = personDB.get();
        Optional<Firm> firmDB = firmRepository.findById(firmId);
        Firm firm = firmDB.get();

        employment.setPerson(person);
        employment.setFirm(firm);
        employment.setAgreementType(agreementType);

        employmentService.save(employment);

//        if (bindingResult.hasErrors()) {
//            return "zatrudnienie_add";
//        }

        return "zatrudnienie_read";
    }
}
