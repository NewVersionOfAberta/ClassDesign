package train_components;

import exceptions.train_component_exceptions.passengerCarriage.WrongCarriageException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import users.Conductor;
import users.Passenger;
import users.User;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class PassengerCarriageTest {
    private static final String OWNER = "BR";
    private static final int MAX_PASSENGER_CAPACITY = 10;
    private static final int MAX_CONDUCTOR_CAPACITY = 4;

    private PassengerCarriage initPassengerCarriage(){
        return new PassengerCarriage(1, OWNER, MAX_PASSENGER_CAPACITY, MAX_CONDUCTOR_CAPACITY);
    }

    @Test
    void addPassenger_addPassenger_findInRightPlace() {
        Passenger actual;
        int passengerPlace = 2;
        int carriageNumber = 2;
        Passenger expectedPassenger = new Passenger(new User("Harry", "Kenn",1), 1, passengerPlace, carriageNumber);

        PassengerCarriage passengerCarriage = initPassengerCarriage();
        passengerCarriage.setNumberInTrain(carriageNumber);

        passengerCarriage.addPassenger(expectedPassenger);


        actual = passengerCarriage.getPassenger(passengerPlace);
        assertEquals(expectedPassenger, actual);
    }

    @Test
    void addPassenger_addPassenger11place_ArrayIndexOutOfBoundsException() {
        int passengerPlace = 11;
        int carriageNumber = 2;
        Passenger expectedPassenger = new Passenger(new User("Harry", "Kenn",1), 1, passengerPlace, carriageNumber);

        PassengerCarriage passengerCarriage = initPassengerCarriage();
        passengerCarriage.setNumberInTrain(carriageNumber);

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> passengerCarriage.addPassenger(expectedPassenger));
    }

    @Test
    void addPassenger_addPassengerWrongCarriage_WrongCarriageException() {
        int passengerPlace = 2;
        int carriageNumber = 2;
        Passenger expectedPassenger = new Passenger(new User("Harry", "Kenn", 1), 1, passengerPlace, carriageNumber);

        PassengerCarriage passengerCarriage = initPassengerCarriage();
        passengerCarriage.setNumberInTrain(carriageNumber + 1);

        assertThrows(WrongCarriageException.class, () -> passengerCarriage.addPassenger(expectedPassenger));
    }



    @Test
    void removePassenger_remove2place_null() {
        int passengerPlace = 2;
        int carriageNumber = 2;
        Passenger expectedPassenger = new Passenger(new User("Harry", "Kenn", 1), 1, passengerPlace, carriageNumber);

        PassengerCarriage passengerCarriage = initPassengerCarriage();
        passengerCarriage.setNumberInTrain(carriageNumber);
        passengerCarriage.addPassenger(expectedPassenger);

        passengerCarriage.removePassenger(passengerPlace);

        assertNull(passengerCarriage.getPassenger(passengerPlace));
    }

    @Test
    void removePassenger_remove11place_ArrayIndexOutOfBoundsException() {
        int passengerPlace = 11;

        PassengerCarriage passengerCarriage = initPassengerCarriage();

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> passengerCarriage.removePassenger(passengerPlace));
    }

    @Test
    void addConductor_addConductor_findInRightPlace() {
        Passenger actual;
        int passengerPlace = 2;
        int carriageNumber = 2;
        Passenger expectedPassenger = new Passenger(new User("Harry", "Kenn",1), 1, passengerPlace, carriageNumber);

        PassengerCarriage passengerCarriage = initPassengerCarriage();
        passengerCarriage.setNumberInTrain(carriageNumber);

        passengerCarriage.addPassenger(expectedPassenger);


        actual = passengerCarriage.getPassenger(passengerPlace);
        assertEquals(expectedPassenger, actual);
    }

    @Test
    void addConductor_addConductor11place_ArrayIndexOutOfBoundsException() {
        int conductorPlace = 11;
        int carriageNumber = 2;
        Conductor expectedConductor = new Conductor(new User("Harry", "Kenn",1), 1, conductorPlace, carriageNumber);

        PassengerCarriage passengerCarriage = initPassengerCarriage();
        passengerCarriage.setNumberInTrain(carriageNumber);

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> passengerCarriage.addConductor(expectedConductor));
    }

    @Test
    void addConductor_addConductorWrongCarriage_WrongCarriageException() {
        int conductorPlace = 2;
        int carriageNumber = 2;
        Conductor expectedConductor = new Conductor(new User("Harry", "Kenn",1), 1, conductorPlace, carriageNumber);

        PassengerCarriage passengerCarriage = initPassengerCarriage();
        passengerCarriage.setNumberInTrain(carriageNumber + 1);

        assertThrows(WrongCarriageException.class, () -> passengerCarriage.addConductor(expectedConductor));
    }



    @Test
    void removeConductor_remove2place_null() {
        int conductorPlace = 2;
        int carriageNumber = 2;
        Conductor expectedConductor = new Conductor(new User("Harry", "Kenn",1), 1, conductorPlace, carriageNumber);

        PassengerCarriage passengerCarriage = initPassengerCarriage();
        passengerCarriage.setNumberInTrain(carriageNumber);
        passengerCarriage.addConductor(expectedConductor);

        passengerCarriage.removeConductor(conductorPlace);

        assertNull(passengerCarriage.getPassenger(conductorPlace));
    }

    @Test
    void removeConductor_remove11place_ArrayIndexOutOfBoundsException() {
        int conductorPlace = 11;

        PassengerCarriage passengerCarriage = initPassengerCarriage();

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> passengerCarriage.removeConductor(conductorPlace));
    }
}