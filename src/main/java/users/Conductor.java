package users;

import exceptions.users_exception.ExperienceOutOfBoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Conductor {
    public static final int MAX_EXPERIENCE = 80;

    private final User user;
    private int experience;
    private int carriageNumber;
    private int placeNumber;

    public Conductor(User user, int experience, int placeNumber, int carriageNumber) {
        this.user = user;
        this.experience = experience;
        this.carriageNumber = carriageNumber;
        this.placeNumber = placeNumber;
        log.info("Conductor #{} was created", user.getId());
    }


    public int getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }


    public void addYearOfWork(){
        if (experience > MAX_EXPERIENCE) {
            log.error("Conductor (#{}) has too much experience (max: {})", user.getId(), MAX_EXPERIENCE);
            throw new ExperienceOutOfBoundException();
        }else{
            experience++;
            log.info("Conductor's (#{}) experience grow to {}", user.getId(), experience);
        }
    }

    public User getUser() {
        return user;
    }

    public int getExperience() {
        return experience;
    }

    public int getCarriageNumber() {
        return carriageNumber;
    }

    public void setCarriageNumber(int carriageNumber) {
        this.carriageNumber = carriageNumber;
    }
}
