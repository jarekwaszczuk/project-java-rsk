package pl.r80.rsk.Utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/error")
public class Errors implements ErrorController {

    @Autowired
    private HttpSession httpSession;

    private static final String ERROR_PATH = "/error";

    @RequestMapping
    public String errors(Model model) {
        model.addAttribute("errors", HttpStatus.INTERNAL_SERVER_ERROR);
        model.addAttribute("httpsession", httpSession.getServletContext());
        return "errors";
    }

//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<String> genericErrorHandler() {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error on page: " + );
//    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}