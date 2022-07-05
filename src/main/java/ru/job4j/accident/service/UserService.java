package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.User;
import ru.job4j.accident.repository.UserRepository;

import java.util.Optional;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.4. Security
 * 2. Регистрация пользователя [#296069]
 * UserService слой бизнес логики управления моделью User.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 05.07.2022
 */
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> saveUser(User user) {
        try {
            userRepository.save(user);
            return Optional.of(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }
}
