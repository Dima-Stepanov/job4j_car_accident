package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 3. Мидл
 * 3.4. Spring
 * AccidentMem хранилище моделей Accident хранит данные в памяти.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 14.06.2022
 */
@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final AtomicInteger key = new AtomicInteger(0);

    private AccidentMem() {
        for (int i = 0; i < 5; i++) {
            accidents.computeIfAbsent(key.incrementAndGet(),
                    k -> Accident.of(k, "Инцидент № " + k, "Описание инцидента № " + k,
                            "Адрес инцидента № " + k));
        }
    }

    public List<Accident> getAll() {
        return accidents.values().stream().toList();
    }

}
