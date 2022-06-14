package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

/**
 * 3. Мидл
 * 3.4. Spring
 * 0. Spring MVC [#6877]
 * 3. @ModelAttribute. Создание инцидента. [#261013]
 * AccidentControl контроллер управления CRUD Accident
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 14.06.2022
 */
@Controller
public class AccidentControl {
    private final AccidentService service;

    public AccidentControl(AccidentService service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String create() {
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        service.create(accident);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable int id) {
        model.addAttribute("accident", service.findById(id));
        return "accident/edit";
    }

    @PostMapping("editPost")
    public String editPost(@ModelAttribute Accident accident) {
        service.edit(accident.getId(), accident);
        return "redirect:/";
    }

}
