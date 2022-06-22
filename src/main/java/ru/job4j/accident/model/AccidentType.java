package ru.job4j.accident.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.2. MVC
 * 4. Form с композиционным объектом [#305522]
 * AccidentType модель описывает категорию инцидента.
 * 1. Spring ORM [#2093]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 15.06.2022
 */
@Entity
@Table(name = "accident_type")
public class AccidentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "at_id")
    private int id;
    @Column(name = "at_name", nullable = false, unique = true)
    private String name;

    public static AccidentType of(int id, String name) {
        AccidentType type = new AccidentType();
        type.id = id;
        type.name = name;
        return type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccidentType type = (AccidentType) o;
        return id == type.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AccidentType{id=" + id + ", name='" + name + '\'' + '}';
    }
}
