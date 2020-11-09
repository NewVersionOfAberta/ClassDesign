package train_components;

import helpClasses.CarriageBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarriageTest {

    private static final String OWNER = "BR";

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
    void couple_couple2carriages_expect2carriagesList() {
        CarriageBuilder carriageBuilder = new CarriageBuilder();
        Carriage[] freightCars = carriageBuilder.buildFreightCars(2, OWNER, 3);

        Carriage.couple(freightCars[0], freightCars[1]);

        assertEquals(freightCars[0].getNext(), freightCars[1]);
        assertEquals(freightCars[1].getPrev(), freightCars[0]);
    }

    @Test
    void makeATrain_2carriages1locomotive_3carriages() {
        int expect = 3;
        int actual;

        CarriageBuilder carriageBuilder = new CarriageBuilder();
        Carriage[] passengerCarriages = carriageBuilder.buildCarriages(2, OWNER, 30, 3);
        Locomotive locomotive = new Locomotive(1, OWNER);

        Carriage.makeATrain(locomotive, passengerCarriages);

        actual = countCars(locomotive);

        assertEquals(expect, actual);
        assertEquals(locomotive.getNext(), passengerCarriages[0]);
        assertEquals(passengerCarriages[0].getNext(), passengerCarriages[1]);
    }

    @Test
    void makeATrain_nullLocomotive_LocomotiveMustNotBeNULL() {
        assertThrows(NullPointerException.class, () -> Carriage.makeATrain(null));
    }

    @Test
    void uncoupleCarriage_2coupledCars_2separatedCars() {
        CarriageBuilder carriageBuilder = new CarriageBuilder();
        Carriage[] freightCars = carriageBuilder.buildFreightCars(2, OWNER, 3);

        Carriage.couple(freightCars[0], freightCars[1]);

        Carriage.uncouple(freightCars[0]);

        assertNull(freightCars[0].getNext());
        assertNull(freightCars[0].getPrev());
        assertNull(freightCars[1].getPrev());
        assertNull(freightCars[1].getNext());
    }
}