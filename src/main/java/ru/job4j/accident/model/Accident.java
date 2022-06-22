package ru.job4j.accident.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.2. MVC
 * 2. IndexControl. Таблица и вид. [#2092]
 * Accident модель данных инцидент.
 * 3.4.3. Template, ORM
 * 1. Spring ORM [#2093]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 14.06.2022
 */
@Entity
@Table(name = "accident")
public class Accident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ac_id")
    private int id;
    @Column(name = "ac_name")
    private String name;
    @Column(name = "ac_text")
    private String text;
    @Column(name = "ac_address")
    private String address;
    @ManyToOne
    @JoinColumn(name = "at_id", foreignKey = @ForeignKey(name = "AT_ID_FK"))
    private AccidentType type;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "accident_rule", joinColumns = {
            @JoinColumn(name = "ac_id", nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "r_id", nullable = false)
            })
    private Set<Rule> rules = new HashSet<>();

    public static Accident of(int id, String name, String text, String address, AccidentType type, Set<Rule> rules) {
        Accident accident = new Accident();
        accident.id = id;
        accident.name = name;
        accident.text = text;
        accident.address = address;
        accident.type = type;
        accident.rules = rules;
        return accident;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AccidentType getType() {
        return type;
    }

    public void setType(AccidentType type) {
        this.type = type;
    }

    public Set<Rule> getRules() {
        return rules;
    }

    public void setRules(Set<Rule> rules) {
        this.rules = rules;
    }

    public void addRule(Rule rule) {
        this.rules.add(rule);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Accident accident = (Accident) o;
        return id == accident.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Accident{id=" + id + ", name='" + name + '\'' + ", text='" + text + '\''
                + ", address='" + address + '\'' + ", type=" + type + ", rules=" + rules + '}';
    }
}
