package users;

import exceptions.users_exception.ExperienceOutOfBoundException;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Slf4j
public class Driver {
    public static final int MAX_EXPERIENCE = 80;

    private final User user;
    private final LocalDate startOfWork;
    private int currentCarriageId;

    public Driver(User user, LocalDate startOfWork) {
        this.user = user;
        this.startOfWork = startOfWork;
        log.info("Driver #{} was created", user.getId());
    }

    public int getCurrentCarriageId() {
        return currentCarriageId;
    }

    public void setCurrentCarriageId(int currentCarriageId) {
        this.currentCarriageId = currentCarriageId;
    }

    public User getUser() {
        return user;
    }

    public LocalDate getStartOfWork() {
        return startOfWork;
    }
}
