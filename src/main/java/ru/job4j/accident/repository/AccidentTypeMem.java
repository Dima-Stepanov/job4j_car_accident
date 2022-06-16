package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.2. MVC
 * 4. Form с композиционным объектом [#305522]
 * AccidentTypeMem хранилище моделей AccidentType хранит данные в памяти.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 15.06.2022
 */
@Repository
public class AccidentTypeMem implements IStore<AccidentType> {
    private final Map<Integer, AccidentType> types = new ConcurrentHashMap<>();
    private final AtomicInteger key = new AtomicInteger();

    public AccidentTypeMem() {
        types.computeIfAbsent(key.incrementAndGet(), key -> AccidentType.of(key, "Две машины"));
        types.computeIfAbsent(key.incrementAndGet(), key -> AccidentType.of(key, "Машина и человек"));
        types.computeIfAbsent(key.incrementAndGet(), key -> AccidentType.of(key, "Машина и велосипед"));
    }

    @Override
    public AccidentType create(AccidentType type) {
        type.setId(key.incrementAndGet());
        return this.types.putIfAbsent(type.getId(), type);
    }

    @Override
    public AccidentType edit(int id, AccidentType type) {
        return this.types.replace(id, type);
    }

    @Override
    public AccidentType findById(int id) {
        return types.get(id);
    }

    @Override
    public List<AccidentType> findAll() {
        return types.values().stream().toList();
    }
}
