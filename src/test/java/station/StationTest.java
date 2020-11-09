package station;

import exceptions.station_exception.CannotManipulateNullCarriageException;
import exceptions.station_exception.LocomotiveMustNotBeNULL;
import exceptions.station_exception.TrainAlreadyAddedException;
import helpClasses.CarriageBuilder;
import org.junit.jupiter.api.Test;
import train_components.Carriage;
import train_components.FreightCar;
import train_components.Locomotive;
import train_components.PassengerCarriage;

import static org.junit.jupiter.api.Assertions.*;

class StationTest {

    private static final String OWNER = "BR";



    @Test
    void couple_couple2carriages_expect2carriagesList() {
        CarriageBuilder carriageBuilder = new CarriageBuilder();
        Carriage[] freightCars = carriageBuilder.buildFreightCars(2, OWNER, 3);

        Station.couple(freightCars[0], freightCars[1]);

        assertEquals(freightCars[0].getNext(), freightCars[1]);
        assertEquals(freightCars[1].getPrev(), freightCars[0]);
    }

    @Test
    void couple_couple2nullCars_CannotManipulateNullCarriageException() {
        PassengerCarriage passengerCarriage = new PassengerCarriage(1, OWNER, 1, 1);
        assertThrows(CannotManipulateNullCarriageException.class, () -> Station.couple(null, null));
        assertThrows(CannotManipulateNullCarriageException.class, () -> Station.couple(null, passengerCarriage));
        assertThrows(CannotManipulateNullCarriageException.class, () -> Station.couple(passengerCarriage, null));
    }

    private int countCars(Carriage firstCarriage){
        Carriage tempCarriage = firstCarriage;
        int result = 0;
        while (tempCarriage != null){
            result++;
            tempCarriage = tempCarriage.getNext();
        }
        return result;
    }

    @Test
    void makeATrain_2carriages1locomotive_3carriages() {
        int expect = 3;
        int actual;

        CarriageBuilder carriageBuilder = new CarriageBuilder();
        Carriage[] passengerCarriages = carriageBuilder.buildCarriages(2, OWNER, 30, 3);
        Locomotive locomotive = new Locomotive(1, OWNER);

        Station.makeATrain(locomotive, passengerCarriages);

        actual = countCars(locomotive);

        assertEquals(expect, actual);
        assertEquals(locomotive.getNext(), passengerCarriages[0]);
        assertEquals(passengerCarriages[0].getNext(), passengerCarriages[1]);
    }

    @Test
    void makeATrain_nullLocomotive_LocomotiveMustNotBeNULL() {
        assertThrows(LocomotiveMustNotBeNULL.class, () -> Station.makeATrain(null));
    }

    @Test
    void uncoupleCarriage_2coupledCars_2separatedCars() {
        CarriageBuilder carriageBuilder = new CarriageBuilder();
        Carriage[] freightCars = carriageBuilder.buildFreightCars(2, OWNER, 3);

        Station.couple(freightCars[0], freightCars[1]);

        Station.uncoupleCarriage(freightCars[0]);

        assertNull(freightCars[0].getNext());
        assertNull(freightCars[0].getPrev());
        assertNull(freightCars[1].getPrev());
        assertNull(freightCars[1].getNext());
    }

    @Test
    void uncoupleCarriage_uncoupleNullCar_CannotManipulateNullCarriageException() {

        assertThrows(CannotManipulateNullCarriageException.class, ()->Station.uncoupleCarriage(null));

    }

    @Test
    void addTrain() {
        Station station = new Station(1);
        Locomotive locomotive = new Locomotive(1, OWNER);

        Station.makeATrain(locomotive);
        station.addTrain(locomotive);

        assertEquals(locomotive, station.getCurrentCarriages().get(0));
    }

    @Test
    void getTrain() {
        int trainId = 1;
        Station station = new Station(1);
        Locomotive locomotive = new Locomotive(trainId, OWNER);

        Station.makeATrain(locomotive);
        station.addTrain(locomotive);

        assertEquals(locomotive, station.getTrain(trainId));
    }

    @Test
    void getTrain_emptyList_null() {
        int nonexistentTrainId = 1;
        Station station = new Station(1);

        assertNull(station.getTrain(nonexistentTrainId));
    }

    @Test
    void addTrain_2timesAdd_TrainAlreadyAddedException() {
        Station station = new Station(1);
        Locomotive locomotive = new Locomotive(1, OWNER);

        Station.makeATrain(locomotive);
        station.addTrain(locomotive);

        assertThrows(TrainAlreadyAddedException.class, () -> station.addTrain(locomotive));
    }
}