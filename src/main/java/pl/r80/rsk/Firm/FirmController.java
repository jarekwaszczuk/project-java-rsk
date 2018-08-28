package pl.r80.rsk.Firm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/firms")
public class FirmController implements WebMvcConfigurer {

    public final FirmService firmService;

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
    }

    @GetMapping
    public String findAll(Model model) {
        List<Firm> firmIterable = firmService.findAll();
        model.addAttribute("firms", firmIterable);
        return "stowarzyszenia";
    }

    @GetMapping("/readone/{id}")
    public String readOneById(@PathVariable Integer id, Model model) {
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
        Optional<Firm> firm = firmService.findById(id);
        if (firm.isPresent()) {
            model.addAttribute("firm", firm.get());
        } else {
            throw new IllegalFirmException("No such firm");
        }
        return "stowarzyszenie_delete";
    }

    @GetMapping("/add")
    public String addFirm(@ModelAttribute Firm firm, Model model) {
        return "stowarzyszenie_add";
    }

    //todo
    @PostMapping("/add")
    public String add(@ModelAttribute @Valid Firm firm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "stowarzyszenie_add";
        }
        if (firm.accidentDues == null || firm.accidentDuesDate == null) {
            firm.accidentDues = 0.00;
            firm.accidentDuesDate = LocalDate.now();
            firmService.save(firm);
            return "stowarzyszenie_read";
        }
        firmService.save(firm);
        return "redirect:/stowarzyszenie_read";
    }

    //todo
    @PostMapping("/update")
    public String update(@Valid Firm firm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "stowarzyszenie_update";
        }
        return "redirect:/stowarzyszenie_read";
    }

    //todo
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        return "redirect:/stowarzyszenia";
    }
}
