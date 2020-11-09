package train_components;

import exceptions.train_component_exceptions.passengerCarriage.NoSuchPassengerFoundException;
import exceptions.train_component_exceptions.passengerCarriage.NotEnoughPlacesException;
import exceptions.train_component_exceptions.TryToAccessNotEmptyPlaceException;
import exceptions.train_component_exceptions.passengerCarriage.WrongCarriageException;
import lombok.extern.slf4j.Slf4j;
import users.Conductor;
import users.Passenger;



@Slf4j
public class PassengerCarriage extends Carriage {
    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    private final int passengerCapacity;

    public int getConductorCapacity() {
        return conductorCapacity;
    }

    private final int conductorCapacity;
    private int numberInTrain;

    private Passenger[] passengers;
    private Conductor[] conductors;

    public PassengerCarriage(int id, String owner, int passengerCapacity, int conductorCapacity) {
        super(id, owner);
        this.passengerCapacity = passengerCapacity;
        this.conductorCapacity = conductorCapacity;
        passengers = new Passenger[passengerCapacity];

        conductors = new Conductor[conductorCapacity];
        log.info("Carriage #{} was created; conductors capacity: {}, passenger capacity:{}",
                id, conductors.length, passengers.length);
    }


    public void addPassenger(Passenger passenger) {

        if (passenger.getCarriageNumber() != this.numberInTrain){
            log.error("Try to access carriage #{} in carriage #{}", passenger.getCarriageNumber(), getId());
            throw new WrongCarriageException();
        }
        if (passenger.getPlaceNumber() <= passengerCapacity && passenger.getPlaceNumber() > 0) {
            if (passengers[passenger.getPlaceNumber() - 1] != null){
                log.warn("Trying to take not empty place #{}, carriage #{}", passenger.getPlaceNumber(), getId());
                throw new TryToAccessNotEmptyPlaceException();
            }
            passengers[passenger.getPlaceNumber() - 1] = passenger;
            log.info("Passenger was added to the seat #{}, in carriage #{}", passenger.getPlaceNumber(), getId());
        }else{
            log.error("Unexpected place number: {}, carriage #{}", passenger.getPlaceNumber(), getId());
            throw new NotEnoughPlacesException(passengerCapacity);

        }
    }

    public Passenger getPassenger(int placeNumber){
        if (placeNumber > passengerCapacity || placeNumber <= 0){
            log.error("Try to access place №{}. {} places available", placeNumber, passengerCapacity);
            throw new NotEnoughPlacesException(passengerCapacity);
        }
        return passengers[placeNumber - 1];
    }

    public Conductor getConductor(int placeNumber){
        if (placeNumber > conductorCapacity || placeNumber <= 0){
            log.error("Try to access place №{}. {} places available", placeNumber, conductorCapacity);
            throw new NotEnoughPlacesException(conductorCapacity);
        }
        return conductors[placeNumber - 1];
    }

    public void removePassenger(int placeNumber) {
        if (placeNumber > passengerCapacity || placeNumber <= 0){
            log.error("Try to access place №{}. {} places available in carriage #{}", placeNumber, passengerCapacity, getId());
            throw new NotEnoughPlacesException(passengerCapacity);
        }
        if (passengers[placeNumber - 1] != null) {
            log.info("Passenger in place №{} leave the carriage #{}", placeNumber, getId());
            passengers[placeNumber - 1] = null;
        }else{
            log.warn("No passenger was found in place №{}, carriage #{}", placeNumber, getId());
            throw new NoSuchPassengerFoundException();
        }
    }


    public void addConductor(Conductor conductor) {
        if (conductor.getCarriageNumber() != this.numberInTrain){
            log.error("Try to access carriage #{} in carriage #{}", conductor.getCarriageNumber(), getId());
            throw new WrongCarriageException();
        }
        if (conductor.getPlaceNumber() <= conductors.length && conductor.getPlaceNumber() > 0) {
            conductors[conductor.getPlaceNumber() - 1] = conductor;
            log.info("Conductor added in the carriage #{}, place #{}",conductor.getCarriageNumber(), conductor.getPlaceNumber());
        }else{
            log.error("Try to access place №{}. {} places available", conductor.getPlaceNumber(), conductors.length);
            throw new NotEnoughPlacesException(conductorCapacity);
        }
    }

    public void removeConductor(int placeNumber) {
        if (placeNumber > conductorCapacity || placeNumber <= 0){
            log.error("Try to access place №{}. {} places available", placeNumber, conductorCapacity);
            throw new NotEnoughPlacesException(conductorCapacity);
        }
        if (conductors[placeNumber - 1] != null) {
            log.info("Conductor in place №{} leave the carriage #{}", placeNumber, getId());
            conductors[placeNumber - 1] = null;
        }else{
            log.warn("No conductor was found in place №{}, carriage #{}", placeNumber, getId());
            throw new NoSuchPassengerFoundException();
        }
    }

    public void cleanCarriage(){
        for (int i = 0; i < passengers.length; i++){
            passengers[i] = null;
        }
        for (int i = 0; i < conductors.length; i++){
            conductors[i] = null;
        }
        log.info("Carriage #{} was cleaned", this.numberInTrain);
    }

    public int getNumberInTrain() {
        return numberInTrain;
    }

    public void setNumberInTrain(int numberInTrain) {
        this.numberInTrain = numberInTrain;
    }
}
