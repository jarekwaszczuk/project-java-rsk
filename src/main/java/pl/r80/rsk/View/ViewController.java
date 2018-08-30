package pl.r80.rsk.View;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
@RequestMapping(value = "/")
public class ViewController implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/kalendarz").setViewName("kalendarz");
        registry.addViewController("/bootstrap-year-calendar-1.1.0/js/bootstrap-year-calendar.js").setViewName("bootstrap-year-calendar-1.1.0/js/bootstrap-year-calendar.js");
        registry.addViewController("/bootstrap-year-calendar-1.1.0/css/bootstrap-year-calendar.css").setViewName("bootstrap-year-calendar-1.1.0/css/bootstrap-year-calendar.css");
    }

    @GetMapping("/kalendarz")
    public String calendar(Model model){
        return "kalendarz";
    }

}
