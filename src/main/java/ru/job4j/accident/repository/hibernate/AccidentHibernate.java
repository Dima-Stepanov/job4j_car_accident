package ru.job4j.accident.repository.hibernate;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.IStore;

import java.util.List;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.3. Template, ORM
 * 1. Spring ORM [#2093]
 * AccidentHibernate класс для работы с базой данны через Hibernate,
 * модель Accident.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 22.06.2022
 */
public class AccidentHibernate implements IStore<Accident> {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public Accident create(Accident type) {
        return tx(session -> {
            session.save(type);
            return type;
        }, sf);
    }

    @Override
    public Accident edit(int id, Accident type) {
        return tx(session -> {
            type.setId(id);
            session.update(type);
            return type;
        }, sf);
    }

    @Override
    public Accident findById(int id) {
        return tx(session -> session.createQuery(
                                "select distinct ac from Accident ac "
                                        + "join fetch ac.type at "
                                        + "join fetch ac.rules r "
                                        + "where ac.id = :acId", Accident.class)
                        .setParameter("acId", id).uniqueResult(),
                sf);
    }

    @Override
    public List<Accident> findAll() {
        return tx(session -> session.createQuery(
                        "select distinct ac from Accident ac "
                                + "join fetch ac.type at "
                                + "join fetch ac.rules r "
                                + "order by ac.id asc ", Accident.class).list(),
                sf);
    }
}
