package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.accident.service.AccidentService;

import java.util.Arrays;
import java.util.List;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.2. Mapping
 * 0. Spring MVC [#6877]
 * AccidentControl слой контроллера главной страницы.
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 14.06.2022
 */
@Controller
public class IndexControl {
    private final AccidentService service;

    public IndexControl(AccidentService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", service.getAllAccident());
        return "index";
    }
}
