package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.AccidentTypeService;
import ru.job4j.accident.service.RuleService;

import javax.servlet.http.HttpServletRequest;

/**
 * 3. Мидл
 * 3.4. Spring
 * 0. Spring MVC [#6877]
 * 3. @ModelAttribute. Создание инцидента. [#261013]
 * 4. Form с композиционным объектом [#305522 #312655]
 * 5. Form с аргегационными объектами [#305523]
 * AccidentControl контроллер управления CRUD Accident
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 14.06.2022
 */
@Controller
public class AccidentControl {
    private final RuleService ruleService;
    private final AccidentTypeService typeService;
    private final AccidentService accidentService;

    public AccidentControl(AccidentService service, AccidentTypeService typeService, RuleService ruleService) {
        this.typeService = typeService;
        this.accidentService = service;
        this.ruleService = ruleService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("accident", new Accident());
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("rules", ruleService.findAll());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        accident.setRules(ruleService.getSelectRule(req.getParameterValues("rIds")));
        accident.setType(typeService.findById(accident.getType().getId()).get());
        accidentService.create(accident);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String edit(Model model, @RequestParam("id") int id) {
        model.addAttribute("accident", accidentService.findById(id).get());
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("rules", ruleService.findAll());
        return "accident/edit";
    }

    @PostMapping("editPost")
    public String editPost(@ModelAttribute Accident accident, HttpServletRequest req) {
        accident.setRules(ruleService.getSelectRule(req.getParameterValues("rIds")));
        accident.setType(typeService.findById(accident.getType().getId()).get());
        accidentService.edit(accident.getId(), accident);
        return "redirect:/";
    }
}
