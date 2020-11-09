package train_components;

import exceptions.train_component_exceptions.passengerCarriage.NoSuchPassengerFoundException;
import exceptions.train_component_exceptions.TryToAccessNotEmptyPlaceException;
import exceptions.train_component_exceptions.passengerCarriage.WrongCarriageException;
import lombok.extern.slf4j.Slf4j;
import users.Conductor;
import users.Passenger;



@Slf4j
public class PassengerCarriage extends Carriage {

    private final int passengerCapacity;
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

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public int getConductorCapacity() {
        return conductorCapacity;
    }


    public void addPassenger(Passenger passenger) {
        if (passenger.getCarriageNumber() != this.numberInTrain){
            log.error("Try to access carriage #{} in carriage #{}", passenger.getCarriageNumber(), getId());
            throw new WrongCarriageException();
        }
        addToArray(passengers, passenger, passenger.getPlaceNumber() - 1, passengerCapacity);
    }

    private <T> void removeFromArray(T[] entities, int index, int capacity){
        if (index >= capacity || index < 0){
            log.error("Try to access place №{}. {} places available in carriage #{}", index, capacity, getId());
            throw new ArrayIndexOutOfBoundsException();
        }
        if (entities[index] == null) {
            log.warn("No one was found in place №{}, carriage #{}", index, getId());
            throw new NoSuchPassengerFoundException();
        }
        log.info("{} in place №{} leave the carriage #{}", entities[index].getClass().getName(), index, getId());
        entities[index] = null;
    }

    private <T> void addToArray(T[] entities, T entity, int index, int capacity){
        if (index >= capacity || index < 0) {
            log.error("Unexpected place number: {}, carriage #{}", index, getId());
            throw new ArrayIndexOutOfBoundsException(capacity);
        }
        if (entities[index] != null){
            log.warn("Trying to take not empty place #{}, carriage #{}", index, getId());
            throw new TryToAccessNotEmptyPlaceException();
        }
        entities[index] = entity;
        log.info("{} was added to the seat #{}, in carriage #{}",entity.getClass().getName(), index, getId());
    }


    public Passenger getPassenger(int placeNumber){
        if (placeNumber > passengerCapacity || placeNumber <= 0){
            log.error("Try to access place №{}. {} places available", placeNumber, passengerCapacity);
            throw new ArrayIndexOutOfBoundsException();
        }
        return passengers[placeNumber - 1];
    }


    public Conductor getConductor(int placeNumber){
        if (placeNumber > conductorCapacity || placeNumber <= 0){
            log.error("Try to access place №{}. {} places available", placeNumber, conductorCapacity);
            throw new ArrayIndexOutOfBoundsException(conductorCapacity);
        }
        return conductors[placeNumber - 1];
    }


    public void removePassenger(int placeNumber) {
        removeFromArray(passengers, placeNumber - 1, passengerCapacity);
    }


    public void addConductor(Conductor conductor) {
        if (conductor.getCarriageNumber() != this.numberInTrain){
            log.error("Try to access carriage #{} in carriage #{}", conductor.getCarriageNumber(), getId());
            throw new WrongCarriageException();
        }
        addToArray(conductors, conductor, conductor.getPlaceNumber() - 1, conductorCapacity);
    }


    public void removeConductor(int placeNumber) {
        removeFromArray(conductors, placeNumber - 1, conductorCapacity);
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
