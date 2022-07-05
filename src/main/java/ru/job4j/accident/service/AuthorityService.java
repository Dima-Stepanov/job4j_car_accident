package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Authority;
import ru.job4j.accident.repository.AuthorityRepository;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.4. Security
 * 2. Регистрация пользователя [#296069]
 * AuthorityService слой бизнес логики управления моделью Authority.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 05.07.2022
 */
@Service
public class AuthorityService {
    private final AuthorityRepository authorityRepository;

    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public Authority findByAuthority(String authority) {
        return authorityRepository.findByAuthority(authority);
    }
}
