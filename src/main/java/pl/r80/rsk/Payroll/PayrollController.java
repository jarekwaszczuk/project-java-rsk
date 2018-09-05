package pl.r80.rsk.Payroll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.r80.rsk.Firm.Firm;
import pl.r80.rsk.Firm.FirmService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/payrolls")
public class PayrollController implements WebMvcConfigurer {

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
}
