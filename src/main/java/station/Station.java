package station;


import exceptions.station_exception.CannotManipulateNullCarriageException;
import exceptions.station_exception.LocomotiveMustNotBeNULL;
import exceptions.station_exception.TrainAlreadyAddedException;
import lombok.extern.slf4j.Slf4j;
import train_components.Carriage;
import train_components.Locomotive;
import train_components.PassengerCarriage;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Station {

    private final int id;

    public Station(int id) {
        this.id = id;
        currentCarriages = new ArrayList<>();
        log.info("Station #{} was created", id);
    }

    private List<Carriage> currentCarriages;

    public static void couple(Carriage firstCarriage, Carriage secondCarriage) {
        if (firstCarriage == null || secondCarriage == null){
            log.error("Trying to couple non-existent cars");
            throw new CannotManipulateNullCarriageException();
        }
        firstCarriage.setNext(secondCarriage) ;
        secondCarriage.setPrev(firstCarriage);
        log.info("Carriage #{} was coupled with carriage #{}", firstCarriage.getId(), secondCarriage.getId());
    }

    public static Carriage makeATrain(Locomotive locomotive, Carriage... carriages) {
        int carriageNumber = 1;
        if (locomotive == null) {
            log.error("Trying to create train without locomotive");
            throw new LocomotiveMustNotBeNULL();
        }
        Carriage tempCarriage = locomotive;
        tempCarriage.setPrev(null);

        for (Carriage carriage : carriages) {
            couple(tempCarriage, carriage);
            if (tempCarriage instanceof PassengerCarriage){
                ((PassengerCarriage) tempCarriage).setNumberInTrain(carriageNumber);
                carriageNumber++;
            }
            tempCarriage = carriage;
        }
        log.info("Train with first locomotive #{} was created", locomotive.getId());
        return locomotive;
    }

    public static void uncoupleCarriage(Carriage targetCarriage) throws CannotManipulateNullCarriageException {
        if (targetCarriage == null){
            log.error("Trying to uncouple non-existent carriage");
            throw new CannotManipulateNullCarriageException();
        }
        Carriage prevCarriage = targetCarriage.getPrev();
        Carriage nextCarriage = targetCarriage.getNext();
        if (prevCarriage != null) {
            prevCarriage.setNext(nextCarriage);
        }
        if (nextCarriage != null) {
            nextCarriage.setPrev(prevCarriage);
        }
        targetCarriage.setPrev(null);
        targetCarriage.setNext(null);
        log.info("Carriage #{} was uncoupled", targetCarriage.getId());
    }

    public int getId() {
        return id;
    }

    public void addTrain(Carriage firstCarriage){
        if (getTrain(firstCarriage.getId()) == null) {
            currentCarriages.add(firstCarriage);
        }else{
            log.error("Try to add train #{} second time", firstCarriage.getId());
            throw new TrainAlreadyAddedException();
        }
    }

    public Carriage getTrain(int id){
        for (Carriage carriage : currentCarriages) {
            if (carriage.getId() == id) {
                log.info("Carriage #{} was found at the station #{}", carriage.getId(), this.id);
                return carriage;
            }
        }
        log.info("Carriage #{} was not found at the station #{}", id, this.id);
        return null;
    }

    public List<Carriage> getCurrentCarriages() {
        return List.copyOf(currentCarriages);
    }
}
