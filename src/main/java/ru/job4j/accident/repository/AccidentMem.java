package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 3. Мидл
 * 3.4. Spring
 * 0. Spring MVC [#6877]
 * AccidentMem хранилище моделей Accident хранит данные в памяти.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 14.06.2022
 */
@Repository
public class AccidentMem implements IStore<Accident> {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final AtomicInteger key = new AtomicInteger(0);

    private AccidentMem() {
        for (int i = 0; i < 5; i++) {
            accidents.computeIfAbsent(key.incrementAndGet(),
                    k -> Accident.of(k, "Инцидент № " + k, "Описание инцидента № " + k,
                            "Адрес инцидента № " + k));
        }
    }

    @Override
    public Accident create(Accident accident) {
        return accidents.computeIfAbsent(key.incrementAndGet(),
                k -> Accident.of(k, accident.getName(), accident.getText(), accident.getAddress()));
    }

    @Override
    public Accident edit(int id, Accident accident) {
        return accidents.computeIfPresent(id,
                (key, value) -> Accident.of(key, accident.getName(), accident.getText(), accident.getAddress()));
    }

    @Override
    public Accident findById(int id) {
        return accidents.get(id);
    }

    @Override
    public List<Accident> findAll() {
        return accidents.values().stream().toList();
    }

}
