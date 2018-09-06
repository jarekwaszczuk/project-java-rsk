package pl.r80.rsk.PayrolPositions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.r80.rsk.Employment.EmploymentService;
import pl.r80.rsk.Firm.Firm;
import pl.r80.rsk.Firm.FirmService;
import pl.r80.rsk.InterfaceRsk;
import pl.r80.rsk.Payroll.Payroll;
import pl.r80.rsk.Payroll.PayrollService;
import pl.r80.rsk.Person.PersonService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/payrollpositions")
public class PayrollPositionsController implements WebMvcConfigurer, InterfaceRsk<PayrollPositions> {

    @Autowired
    private HttpSession httpSession;

    private final PersonService personService;
    private final PayrollService payrollService;
    private final PayrollPositionsService payrollPositionsService;

    @Autowired
    public PayrollPositionsController(PersonService personService, PayrollService payrollService, PayrollPositionsService payrollPositionsService) {
        this.personService = personService;
        this.payrollService = payrollService;
        this.payrollPositionsService = payrollPositionsService;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/listyplacpozycje").setViewName("listyplacpozycje");
        registry.addViewController("/listyplacpozycje_read").setViewName("listyplacpozycje_read");
        registry.addViewController("/listyplacpozycje_update").setViewName("listyplacpozycje_update");
        registry.addViewController("/listyplacpozycje_delete").setViewName("listyplacpozycje_delete");
    }

    @Override
    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("firmKontekst", httpSession.getAttribute("KONTEKST"));
        return "index";
    }

    @GetMapping("/payroll/{id}")
    public String findAllByFirmAndPayroll(@PathVariable Integer id, Model model){
        Firm firm = (Firm) httpSession.getAttribute("KONTEKST");
        Optional<Payroll> payroll = payrollService.findById(id);
        model.addAttribute("firmKontekst", firm);
        model.addAttribute("listyplacpozycje",payrollPositionsService.findByFirmAndPayroll(firm, payroll.get()));
        return "listyplacpozycje";
    }

    @Override
    public String readOneById(@PathVariable Integer id, Model model) {
        model.addAttribute("firmKontekst", httpSession.getAttribute("KONTEKST"));
        Optional<PayrollPositions> payrollPositions = payrollPositionsService.findById(id);
        model.addAttribute("payrollPositions", payrollPositions.get());
        return "listyplacpozycje_read";
    }

    @Override
    public String updateById(@PathVariable Integer id, Model model) {
        model.addAttribute("firmKontekst", httpSession.getAttribute("KONTEKST"));
        return "listyplacpozycje_update";
    }

    @Override
    public String deleteById(@PathVariable Integer id, Model model) {
        model.addAttribute("firmKontekst", httpSession.getAttribute("KONTEKST"));
        return "listyplacpozycje_delete";
    }

    @Override
    public String addView(PayrollPositions payrollPositions, Model model) {
        model.addAttribute("firmKontekst", httpSession.getAttribute("KONTEKST"));
        return "index";
    }

    @Override
    public String add(@Valid PayrollPositions payrollPositions, BindingResult bindingResult, Model model) {
        model.addAttribute("firmKontekst", httpSession.getAttribute("KONTEKST"));
        return "index";
    }

    @Override
    public String update(@Valid PayrollPositions payrollPositions, BindingResult bindingResult, Model model) {
        model.addAttribute("firmKontekst", httpSession.getAttribute("KONTEKST"));
        return "index";
    }
}
