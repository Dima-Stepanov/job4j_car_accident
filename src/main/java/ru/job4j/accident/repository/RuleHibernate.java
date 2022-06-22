package ru.job4j.accident.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.List;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.3. Template, ORM
 * 1. Spring ORM [#2093]
 * 1. Spring ORM [#2093]
 * RuleHibernate класс для работы с базой данны через Hibernate,
 * модель Rule.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 22.06.2022
 */
@Repository
public class RuleHibernate implements IStore<Rule> {
    private final SessionFactory sf;

    public RuleHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public Rule create(Rule rule) {
        return tx(session -> {
            session.save(rule);
            return rule;
        }, sf);
    }

    @Override
    public Rule edit(int id, Rule rule) {
        return tx(session -> {
            rule.setId(id);
            session.update(rule);
            return rule;
        }, sf);
    }

    @Override
    public Rule findById(int id) {
        return tx(session -> session
                        .get(Rule.class, id),
                sf);
    }

    @Override
    public List<Rule> findAll() {
        return tx(session -> session
                        .createQuery("from Rule order by id").list(),
                sf);
    }
}
