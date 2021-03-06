package ru.job4j.accident.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.2. MVC
 * 5. Form с аргегационными объектами [#305523]
 * Role модель списка статей.
 * 1. Spring ORM [#2093]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 15.06.2022
 */
@Entity
@Table(name = "rule")
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_id")
    private int id;
    @Column(name = "r_name", unique = true, nullable = false)
    private String name;

    public static Rule of(int id, String name) {
        Rule rule = new Rule();
        rule.id = id;
        rule.name = name;
        return rule;
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
        Rule rule = (Rule) o;
        return id == rule.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Rule{id=" + id + ", name='" + name + '\'' + '}';
    }
}
