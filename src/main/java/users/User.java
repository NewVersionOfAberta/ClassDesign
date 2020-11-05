package users;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class User {
    private String name;
    private String surname;
    private final int id;

    public User(String name, String surname, int id) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        log.info("User {} {} was created (#{})", name, surname, id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getId() {
        return id;
    }
}
