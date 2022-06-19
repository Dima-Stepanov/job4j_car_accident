package ru.job4j.accident;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.0. Проект. "Автонарушители".
 * 0. Spring MVC [#6877]
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 09.06.2022
 */
@SpringBootApplication
public class CarAccidentApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarAccidentApplication.class, args);
        System.out.println("http://localhost:8080/");
    }
}
