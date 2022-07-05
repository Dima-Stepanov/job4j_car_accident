package ru.job4j.accident.init;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 3. Мидл
 * 3.4. Spring
 * Генерация пароля для пользователя.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 05.07.2022
 */
public class PasswordGenerate {
    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();

    public static void main(String[] args) {
        System.out.println(getPass("secret"));
    }

    public static String getPass(String pass) {
        return ENCODER.encode(pass);
    }
}
