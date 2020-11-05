package users;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Passenger {

    public Passenger(User user, int ticketId, int placeNumber, int carriageNumber) {
        this.user = user;
        this.ticketId = ticketId;
        this.placeNumber = placeNumber;
        this.carriageNumber = carriageNumber;
        log.info("Passenger #{} was created", ticketId);
    }

    private final User user;
    private int ticketId;
    private int placeNumber;
    private int carriageNumber;


    public User getUser() {
        return user;
    }

    public int getTicketId() {
        return ticketId;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public int getCarriageNumber() {
        return carriageNumber;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }

    public void setCarriageNumber(int carriageNumber) {
        this.carriageNumber = carriageNumber;
    }
}
