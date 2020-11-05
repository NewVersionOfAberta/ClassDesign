package users;

import exceptions.users_exception.ExperienceOutOfBoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Driver {
    public static final int MAX_EXPERIENCE = 80;

    private final User user;
    private int experience;
    private int currentCarriageId;



    public int getExperience() {
        return experience;
    }

    public void addYearOfWork(){
        if (experience > MAX_EXPERIENCE) {
            log.error("Driver (#{}) has too much experience (max: {})", user.getId(), MAX_EXPERIENCE);
            throw new ExperienceOutOfBoundException();
        }else{
            experience++;
            log.info("Driver's (#{}) experience grow to {}", user.getId(), experience);
        }
    }

    public int getCurrentCarriageId() {
        return currentCarriageId;
    }

    public void setCurrentCarriageId(int currentCarriageId) {
        this.currentCarriageId = currentCarriageId;
    }

    public Driver(User user, int experience) {
        this.user = user;
        if (experience > MAX_EXPERIENCE) {
            log.error("Driver (#{}) has too much experience (max: {})", user.getId(), MAX_EXPERIENCE);
            throw new ExperienceOutOfBoundException();
        }
        this.experience = experience;
        log.info("Driver #{} was created", user.getId());
    }

    public User getUser() {
        return user;
    }
}
