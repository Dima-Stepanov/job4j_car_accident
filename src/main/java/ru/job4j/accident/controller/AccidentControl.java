package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.ARService;
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
 * 5. Form с агрегационными объектами [#305523]
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
    private final ARService arService;

    public AccidentControl(AccidentService service, AccidentTypeService typeService, RuleService ruleService, ARService arService) {
        this.typeService = typeService;
        this.accidentService = service;
        this.ruleService = ruleService;
        this.arService = arService;
    }

    /**
     * Метод Get отображение вида добавление инцидента.
     *
     * @param model Model
     * @return String.
     */
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("accident", new Accident());
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("rules", ruleService.findAll());
        return "accident/create";
    }

    /**
     * Метод Post сохраняет инцидент.
     *
     * @param accident Accident
     * @param req HttpServletRequest
     * @return String
     */
    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] rules = req.getParameterValues("rIds");
        accident.setRules(ruleService.getSelectRule(rules));
        accident.setType(typeService.findById(accident.getType().getId()).get());
        accidentService.create(accident);
        arService.saveAccidentRules(accident.getId(), rules);
        return "redirect:/";
    }

    /**
     * Метод Get отображение вида редактирования инцидента.
     *
     * @param model Model.
     * @param id    int
     * @return String
     */
    @GetMapping("/update")
    public String edit(Model model, @RequestParam("id") int id) {
        model.addAttribute("accident", accidentService.findById(id).get());
        model.addAttribute("types", typeService.findAll());
        model.addAttribute("rules", ruleService.findAll());
        return "accident/edit";
    }

    /**
     * Метод Post сохраняет измененный инцидент.
     *
     * @param accident Accident.
     * @param req      HttpServletRequest
     * @return String.
     */
    @PostMapping("editPost")
    public String editPost(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] rules = req.getParameterValues("rIds");
        accident.setRules(ruleService.getSelectRule(rules));
        accident.setType(typeService.findById(accident.getType().getId()).get());
        accidentService.edit(accident.getId(), accident);
        arService.updateAccidentRules(accident.getId(), rules);
        return "redirect:/";
    }
}
