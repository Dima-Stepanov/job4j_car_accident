package ru.job4j.accident.controller;

import org.assertj.core.internal.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * 3. Мидл
 * 3.4. Spring
 * IndexControl testing mockito
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 04.07.2022
 */
@Disabled
class IndexControlTest {
    @Disabled
    @Test
    void index() {
        List<Accident> accidentList = List.of(new Accident(), new Accident());
        AccidentService service = mock(AccidentService.class);
        Model model = mock(Model.class);
        when(service.findAll()).thenReturn(accidentList);
        IndexControl indexControl = new IndexControl(service);
        String page = indexControl.index(model);
        String expectPage = "index/index";
        verify(model).addAttribute("accidents", service.findAll());
        assertEquals(expectPage, page);
    }
}