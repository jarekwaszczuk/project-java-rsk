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
@RequestMapping(value = "/firms")
public class FirmController {

    public final FirmService firmService;

    @Autowired
    public FirmController(FirmService firmService) {
        this.firmService = firmService;
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
}