package ru.job4j.accident.repository.mem;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.IStore;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 3. Мидл
 * 3.4. Spring
 * 0. Spring MVC [#6877]
 * 5. Form с аргегационными объектами [#305523]
 * RoleMem хранилище моделей Rule хранит данные в памяти.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 15.06.2022
 */
public class RuleMem implements IStore<Rule> {
    private final AtomicInteger key = new AtomicInteger();
    private final Map<Integer, Rule> rules = new ConcurrentHashMap<>();

    public RuleMem() {
        for (int i = 0; i < 3; i++) {
            rules.computeIfAbsent(key.incrementAndGet(), key -> Rule.of(key, "Статья. " + key));
        }
    }

    @Override
    public Rule create(Rule rule) {
        rule.setId(key.incrementAndGet());
        return this.rules.putIfAbsent(rule.getId(), rule);
    }

    @Override
    public Rule edit(int id, Rule rule) {
        return this.rules.replace(id, rule);
    }

    @Override
    public Rule findById(int id) {
        return rules.get(id);
    }

    @Override
    public List<Rule> findAll() {
        return rules.values().stream().toList();
    }
}
