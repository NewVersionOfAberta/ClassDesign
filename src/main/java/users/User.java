package users;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class User {
    public static final int MAX_NAME_LENGTH = 60;
    private String name;
    private String surname;
    private final int id;

    public User(String name, String surname, int id) {
        if (name == null || surname == null){
            log.error("Name and surname must not be null");
            throw new NullPointerException();
        }
        if (name.isEmpty() || name.length() > MAX_NAME_LENGTH || surname.isEmpty() || surname.length() > MAX_NAME_LENGTH){
            log.error("Name and surname must have less then {} and greater then {} characters", MAX_NAME_LENGTH, 0);
            throw new IllegalArgumentException();
        }
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
