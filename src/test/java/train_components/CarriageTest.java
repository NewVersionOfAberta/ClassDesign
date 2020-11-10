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

        freightCars[0].couple(freightCars[1]);

        assertEquals(freightCars[0].getNext(), freightCars[1]);
        assertEquals(freightCars[1].getPrev(), freightCars[0]);
    }


    @Test
    void uncoupleCarriage_2coupledCars_2separatedCars() {
        CarriageBuilder carriageBuilder = new CarriageBuilder();
        Carriage[] freightCars = carriageBuilder.buildFreightCars(2, OWNER, 3);

        freightCars[0].couple(freightCars[1]);

        freightCars[0].uncouple();

        assertNull(freightCars[0].getNext());
        assertNull(freightCars[0].getPrev());
        assertNull(freightCars[1].getPrev());
        assertNull(freightCars[1].getNext());
    }
}