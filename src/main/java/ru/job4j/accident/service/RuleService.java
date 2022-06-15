package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.IStore;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 3. Мидл
 * 3.4. Spring
 * 0. Spring MVC [#6877]
 * 5. Form с аргегационными объектами [#305523]
 * RuleService слой бизнес логики.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 15.06.2022
 */
@Service
public class RuleService {
    private final IStore<Rule> storeRules;

    public RuleService(IStore<Rule> storeRules) {
        this.storeRules = storeRules;
    }

    public Optional<Rule> findById(int id) {
        return Optional.ofNullable(storeRules.findById(id));
    }

    public List<Rule> findAll() {
        return storeRules.findAll();
    }

    public Set<Rule> getSelectRule(String[] ruleId) {
        Set<Rule> result = new CopyOnWriteArraySet<>();
        for (String id : ruleId) {
            result.add(findById(Integer.valueOf(id)).get());
        }
        return result;
    }
}
