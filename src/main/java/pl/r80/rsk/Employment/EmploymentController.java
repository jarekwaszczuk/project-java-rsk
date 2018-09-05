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
import pl.r80.rsk.InterfaceRsk;
import pl.r80.rsk.Person.Person;
import pl.r80.rsk.Person.PersonRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/employments")
public class EmploymentController implements WebMvcConfigurer, InterfaceRsk<Employment> {

    @Autowired
    private HttpSession httpSession;
    @Autowired
    private HttpServletRequest request;

    private final EmploymentService employmentService;
    private final PersonRepository personRepository;
    private final FirmRepository firmRepository;

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
        model.addAttribute("firmKontekst", httpSession.getAttribute("KONTEKST"));
        List<Employment> employmentIterable = employmentService.findAll();
        model.addAttribute("employments", employmentIterable);
        return "zatrudnienie";
    }

    @GetMapping("/readone/{id}")
    public String readOneById(@PathVariable Integer id, Model model) {
        model.addAttribute("firmKontekst", httpSession.getAttribute("KONTEKST"));
        verifyEmploymentExist(id, model);
        return "zatrudnienie_read";
    }

    @GetMapping("/update/{id}")
    public String updateById(@PathVariable Integer id, Model model) {
        model.addAttribute("firmKontekst", httpSession.getAttribute("KONTEKST"));
        verifyEmploymentExist(id, model);
        return "zatrudnienie_update";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id, Model model) {
        model.addAttribute("firmKontekst", httpSession.getAttribute("KONTEKST"));
        verifyEmploymentExist(id, model);
        Optional<Employment> employment = employmentService.findById(id);
        employmentService.delete(employment.get());
        return "logged";
    }

    @GetMapping("/add")
    public String addView(@ModelAttribute Employment employment, Model model) {
        model.addAttribute("firmKontekst", httpSession.getAttribute("KONTEKST"));
        addDataPrepare(model);
        return "zatrudnienie_add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute @Valid Employment employment, BindingResult bindingResult, Model model) {
        model.addAttribute("firmKontekst", httpSession.getAttribute("KONTEKST"));
        Integer personId = Integer.parseInt(request.getParameter("personId"));
        AgreementType agreementType = AgreementType.valueOf(request.getParameter("agreementType"));
        Integer firmId = Integer.parseInt(request.getParameter("firmId"));
        String hireDate = request.getParameter("hireDate");

        Optional<Person> personDB = personRepository.findById(personId);
        Person person = personDB.get();
        Optional<Firm> firmDB = firmRepository.findById(firmId);
        Firm firm = firmDB.get();

        if (bindingResult.hasErrors()) {
            addDataPrepare(model);
            model.addAttribute("personId", personId);
            model.addAttribute("firmId", firmId);
            model.addAttribute("agreementType", agreementType);
            model.addAttribute("hireDate", hireDate);
            return "zatrudnienie_add";
        }

        employment.setPerson(person);
        employment.setFirm(firm);
        employment.setAgreementType(agreementType);

        employmentService.save(employment);

        return "zatrudnienie_read";
    }

    private void addDataPrepare(Model model) {
        model.addAttribute("firmKontekst", httpSession.getAttribute("KONTEKST"));
        Iterable<Person> persons = personRepository.findAll();
        Iterable<Firm> firms = firmRepository.findAll();
        AgreementType[] agreementTypes = AgreementType.values();
        model.addAttribute("agreementTypes", agreementTypes);
        model.addAttribute("persons", persons);
        model.addAttribute("firms", firms);
    }

    private void verifyEmploymentExist(@PathVariable Integer id, Model model) {
        model.addAttribute("firmKontekst", httpSession.getAttribute("KONTEKST"));
        Optional<Employment> employment = employmentService.findById(id);
        if (employment.isPresent()) {
            model.addAttribute("employment", employment.get());
        } else {
            throw new IllegalEmploymentException("No such employment");
        }
    }

    @PostMapping("/update")
    public String update(@Valid Employment employment, BindingResult bindingResult, Model model) {
        model.addAttribute("firmKontekst", httpSession.getAttribute("KONTEKST"));
        if (bindingResult.hasErrors()) {
            return "zatrudnienie_update";
        }
        employmentService.update(employment);
        return "zatrudnienie_read";
    }
}
