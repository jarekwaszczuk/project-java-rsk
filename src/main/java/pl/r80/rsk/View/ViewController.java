package pl.r80.rsk.View;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.r80.rsk.Access.Access;
import pl.r80.rsk.Access.AccessService;
import pl.r80.rsk.Firm.Firm;
import pl.r80.rsk.Firm.FirmService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping(value = "/")
public class ViewController implements WebMvcConfigurer {

    private final AccessService accessService;
    private final FirmService firmService;

    @Autowired
    public ViewController(AccessService accessService, FirmService firmService) {
        this.accessService = accessService;
        this.firmService = firmService;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/kalendarz").setViewName("kalendarz");
        registry.addViewController("/logged").setViewName("logged");
        registry.addViewController("/bootstrap-year-calendar-1.1.0/js/bootstrap-year-calendar.js").setViewName("bootstrap-year-calendar-1.1.0/js/bootstrap-year-calendar.js");
        registry.addViewController("/bootstrap-year-calendar-1.1.0/js/languages/bootstrap-year-calendar.pl.js").setViewName("bootstrap-year-calendar-1.1.0/js/languages/bootstrap-year-calendar.pl.js");
        registry.addViewController("/bootstrap-year-calendar-1.1.0/css/bootstrap-year-calendar.css").setViewName("bootstrap-year-calendar-1.1.0/css/bootstrap-year-calendar.css");
    }

    @GetMapping("/kalendarz")
    public String calendar(Model model) {
        return "kalendarz";
    }

    @GetMapping("/")
    public String login(HttpServletRequest req, Model model) {

        if (req.getSession().getAttribute("SECURITY_CONTEXT_KEY") == null) {
            return "index";
        } else if (("zalogowany").equals(req.getSession().getAttribute("SECURITY_CONTEXT_KEY").toString())) {
            return "logged";
        }
        return "index";
    }

    @PostMapping("/")
    public String login(HttpServletRequest req, HttpServletResponse res, Model model) {

        if (req.getSession().getAttribute("SECURITY_CONTEXT_KEY") == null) {
            String login = req.getParameter("login");
            String password = req.getParameter("password");

            Optional<Access> userDB = accessService.findUser(login);
            Access userEntity = userDB.get();

            if (userEntity.getPassword().equals(password)) {
                HttpSession session = req.getSession(true);
                session.setAttribute("SECURITY_CONTEXT_KEY", "zalogowany");

                Optional<Firm> firmDB = firmService.findById(userEntity.getDefaultContext());
                Firm firm = firmDB.get();
                session.setAttribute("KONTEKST", firm.getId());
                model.addAttribute("firmKontekst", firm);

                return "logged";
            } else {
                return "index";
            }

        } else if (("zalogowany").equals(req.getSession().getAttribute("SECURITY_CONTEXT_KEY").toString())) {
            return "logged";
        }
        return "index";
    }
}
