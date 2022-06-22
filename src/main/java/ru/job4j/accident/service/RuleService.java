package ru.job4j.accident.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.IStore;
import ru.job4j.accident.repository.RuleRepository;

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
 * 2. Spring Data [#296073]
 * @author Dmitry Stepanov, user Dmitry
 * @since 15.06.2022
 */
@Service
public class RuleService {
    private final RuleRepository storeRules;

    public RuleService(RuleRepository storeRules) {
        this.storeRules = storeRules;
    }

    public Optional<Rule> findById(int id) {
        return storeRules.findById(id);
    }

    public Iterable<Rule> findAll() {
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
