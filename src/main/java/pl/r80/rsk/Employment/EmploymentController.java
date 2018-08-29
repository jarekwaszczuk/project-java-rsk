package pl.r80.rsk.Employment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Controller
@RequestMapping(value = "/employments")
public class EmploymentController implements WebMvcConfigurer {

    public final EmploymentService employmentService;

    @Autowired
    public EmploymentController(EmploymentService employmentService) {
        this.employmentService = employmentService;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/zatrudnienie").setViewName("zatrudnienie");
    }

    @GetMapping
    public String findAll(Model model) {

        List<Employment> employmentIterable = employmentService.findAll();
        model.addAttribute("employments", employmentIterable);
        return "zatrudnienie";
    }

}
