package ru.job4j.accident.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.User;
import ru.job4j.accident.service.AuthorityService;
import ru.job4j.accident.service.UserService;

import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.4. Security
 * 2. Регистрация пользователя [#296069]
 * RegController контроллер регистрации пользователей.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 05.07.2022
 */
@Controller
public class RegControl {
    private final PasswordEncoder encoder;
    private final UserService users;
    private final AuthorityService authorities;

    public RegControl(PasswordEncoder encoder, UserService users, AuthorityService authorities) {
        this.encoder = encoder;
        this.users = users;
        this.authorities = authorities;
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user) {
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorities.findByAuthority("ROLE_USER"));
        Optional<User> newUser = users.saveUser(user);
        if (newUser.isEmpty()) {
            return "redirect:/reg?error=true";
        }
        return "redirect:/login";
    }

    @GetMapping("/reg")
    public String regPage(Model model,
                          @RequestParam(value = "error", required = false) String error) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = "Имя пользователя уже существует!!!";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "user/reg";
    }
}
