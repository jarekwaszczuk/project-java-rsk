package pl.r80.rsk;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

public interface InterfaceRsk<T> {

    @GetMapping
    public String findAll(Model model);

    @GetMapping("/readone/{id}")
    public String readOneById(@PathVariable Integer id, Model model);

    @GetMapping("/update/{id}")
    public String updateById(@PathVariable Integer id, Model model);

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id, Model model);

    @GetMapping("/add")
    public String addView(@ModelAttribute T t, Model model);

    @PostMapping("/add")
    public String add(@ModelAttribute @Valid T t, BindingResult bindingResult, Model model);

    @PostMapping("/update")
    public String update(@Valid T t, BindingResult bindingResult, Model model);
}
