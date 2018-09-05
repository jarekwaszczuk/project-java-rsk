package pl.r80.rsk.Firm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/firms")
public class FirmController implements WebMvcConfigurer {

    @Autowired
    private HttpSession httpSession;

    private final FirmService firmService;

    @Autowired
    public FirmController(FirmService firmService) {
        this.firmService = firmService;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/stowarzyszenia").setViewName("stowarzyszenia");
        registry.addViewController("/stowarzyszenie_add").setViewName("stowarzyszenie_add");
        registry.addViewController("/stowarzyszenie_update").setViewName("stowarzyszenie_update");
        registry.addViewController("/stowarzyszenie_delete").setViewName("stowarzyszenie_delete");
        registry.addViewController("/stowarzyszenie_change").setViewName("stowarzyszenie_change");
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("firmKontekst", httpSession.getAttribute("KONTEKST"));
        List<Firm> firmIterable = firmService.findAll();
        model.addAttribute("firms", firmIterable);
        return "stowarzyszenia";
    }

    @GetMapping("/readone/{id}")
    public String readOneById(@PathVariable Integer id, Model model) {
        model.addAttribute("firmKontekst", httpSession.getAttribute("KONTEKST"));
        Optional<Firm> firm = firmService.findById(id);
        if (firm.isPresent()) {
            model.addAttribute("firm", firm.get());
        } else {
            throw new IllegalFirmException("No such firm");
        }
        return "stowarzyszenie_read";
    }

    @GetMapping("/update/{id}")
    public String updateFirm(@PathVariable Integer id, Model model) {
        model.addAttribute("firmKontekst", httpSession.getAttribute("KONTEKST"));
        Optional<Firm> firm = firmService.findById(id);
        if (firm.isPresent()) {
            model.addAttribute("firm", firm.get());
        } else {
            throw new IllegalFirmException("No such firm");
        }
        return "stowarzyszenie_update";
    }

    @GetMapping("/delete/{id}")
    public String deleteFirm(@PathVariable Integer id, Model model) {
        model.addAttribute("firmKontekst", httpSession.getAttribute("KONTEKST"));
        Optional<Firm> firm = firmService.findById(id);
        if (firm.isPresent()) {
            model.addAttribute("firm", firm.get());
        } else {
            throw new IllegalFirmException("No such firm");
        }
        firmService.delete(firm);
        return "stowarzyszenie_delete";
    }

    @GetMapping("/add")
    public String addFirm(@ModelAttribute Firm firm, Model model) {
        model.addAttribute("firmKontekst", httpSession.getAttribute("KONTEKST"));
        return "stowarzyszenie_add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute @Valid Firm firm, BindingResult bindingResult, Model model) {
        model.addAttribute("firmKontekst", httpSession.getAttribute("KONTEKST"));
        if (bindingResult.hasErrors()) {
            return "stowarzyszenie_add";
        }
        if (firm.accidentDues == null || firm.accidentDuesDate == null) {
            firm.accidentDues = 0.00;
            firm.accidentDuesDate = LocalDate.now();
            firmService.save(firm);
            return "stowarzyszenie_read";
        }

        Double accidental = Double.valueOf(firm.accidentDues);
        firm.setAccidentDues(accidental);

        firmService.save(firm);
        return "stowarzyszenie_read";
    }

    @GetMapping("/change")
    public String changeFirmKontekst(@ModelAttribute Firm firm, Model model) {
        model.addAttribute("firmKontekst", httpSession.getAttribute("KONTEKST"));
        Iterable<Firm> firms = firmService.findAll();
        model.addAttribute("firms", firms);
        return "stowarzyszenie_change";
    }

    @PostMapping("/change")
    public String doChangeFirmKontekst(HttpServletRequest request, HttpServletResponse response,
                                       @RequestParam Integer firmId, Model model){

        Optional<Firm> firmDB = firmService.findById(firmId);
        Firm firm = firmDB.get();
        request.getSession().setAttribute("KONTEKST", firm);
        model.addAttribute("firmKontekst", firm);
        return "logged";
    }

    //todo
    @PostMapping("/update")
    public String update(@Valid Firm firm, BindingResult bindingResult, Model model) {
        model.addAttribute("firmKontekst", httpSession.getAttribute("KONTEKST"));
        if (bindingResult.hasErrors()) {
            return "stowarzyszenie_update";
        }
        firmService.update(firm);
        return "stowarzyszenie_read";
    }
}
