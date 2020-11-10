package users;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Slf4j
public class Conductor {
    public static final int MAX_EXPERIENCE = 80;

    private final User user;
    private final LocalDate startOfWork;
    private int carriageNumber;
    private int placeNumber;

    public Conductor(User user, LocalDate startOfWork, int placeNumber, int carriageNumber) {
        this.user = user;
        this.startOfWork = startOfWork;
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



    public User getUser() {
        return user;
    }


    public int getCarriageNumber() {
        return carriageNumber;
    }

    public void setCarriageNumber(int carriageNumber) {
        this.carriageNumber = carriageNumber;
    }

    public LocalDate getStartOfWork() {
        return startOfWork;
    }
}
