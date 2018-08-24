package pl.r80.rsk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/employees")
public class EmployeeController {



    private final EmployeeService employeeService;

    @Autowired //opcjonalny
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String findAll(Model model) {

        List<Employee> employeeIterable = employeeService.findAll();
        model.addAttribute("employees", employeeIterable);
        return "kadrykartoteki";
    }

}
