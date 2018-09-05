package pl.r80.rsk.Payroll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.r80.rsk.Firm.Firm;
import pl.r80.rsk.Firm.FirmService;
import pl.r80.rsk.InterfaceRsk;
import pl.r80.rsk.Person.IllegalPersonException;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/payrolls")
public class PayrollController implements WebMvcConfigurer, InterfaceRsk<Payroll> {

    @Autowired
    private HttpSession httpSession;

    private final FirmService firmService;
    private final PayrollService payrollService;

    @Autowired
    public PayrollController(FirmService firmService, PayrollService payrollService) {
        this.firmService = firmService;
        this.payrollService = payrollService;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/listyplac").setViewName("listyplac");
        registry.addViewController("/listyplac_read").setViewName("listyplac_read");
        registry.addViewController("/listyplac_update").setViewName("listyplac_update");
        registry.addViewController("/listyplac_delete").setViewName("listyplac_delete");
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("firmKontekst", httpSession.getAttribute("KONTEKST"));
        Iterable<Payroll> payrolls = payrollService.findAll();
        model.addAttribute("listyplac", payrolls);
        return "listyplac";
    }

    @GetMapping("/byfirm")
    public String findAllByFirm(Model model) {
        model.addAttribute("firmKontekst", httpSession.getAttribute("KONTEKST"));
        Firm firm = (Firm) httpSession.getAttribute("KONTEKST");
        List<Payroll> payrolls = payrollService.findAllByFirm(firm);
        model.addAttribute("listyplac", payrolls);
        return "listyplac";
    }

    @GetMapping("/readone/{id}")
    public String readOneById(@PathVariable Integer id, Model model) {
        model.addAttribute("firmKontekst", httpSession.getAttribute("KONTEKST"));
        Optional<Payroll> payroll = payrollService.findById(id);
        if (payroll.isPresent()) {
            model.addAttribute("payroll", payroll.get());
        } else {
            throw new IllegalPersonException("No such payroll");
        }
        return "listyplac_read";
    }

    //TODO
    @GetMapping("/update/{id}")
    public String updateById (@PathVariable Integer id, Model model) {

        return "listyplac_update";
    }

    //TODO
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id, Model model) {

        return "listyplac_delete";
    }

    @Override
    public String addView(Payroll payroll, Model model) {
        return null;
    }

    @Override
    public String add(@Valid Payroll payroll, BindingResult bindingResult, Model model) {
        return null;
    }

    @Override
    public String update(@Valid Payroll payroll, BindingResult bindingResult, Model model) {
        return null;
    }


}
