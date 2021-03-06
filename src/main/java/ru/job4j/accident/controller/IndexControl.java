package ru.job4j.accident.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.service.AccidentService;

/**
 * 3. Мидл
 * 3.3. Hibernate
 * 3.3.2. Mapping
 * 0. Spring MVC [#6877]
 * IndexControl слой контроллера главной страницы.
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

    /**
     * Метод Get отображение главной страницы со всеми инцидентами.
     *
     * @param model Model.
     * @return String.
     */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("accidents", service.findAll());
        return "index/index";
    }
}
