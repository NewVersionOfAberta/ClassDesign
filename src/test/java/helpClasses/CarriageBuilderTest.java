package helpClasses;

import exceptions.help_class_exception.UnexpectedAmountOfCarsException;
import org.junit.jupiter.api.Test;
import train_components.FreightCar;
import train_components.Locomotive;
import train_components.PassengerCarriage;
import users.Passenger;

import static org.junit.jupiter.api.Assertions.*;

class CarriageBuilderTest {

    private static final String OWNER = "BR";

    @Test
    void buildCarriages_negativeAmount_UnexpectedAmountOfCarsException() {
        CarriageBuilder carriageBuilder = new CarriageBuilder();
        assertThrows(UnexpectedAmountOfCarsException.class, () -> carriageBuilder.buildCarriages
                (-1, OWNER, 10, 2));
    }

    @Test
    void buildCarriages_2carriages_arrayLength2() {
        int expected = 2;
        int actual;
        CarriageBuilder carriageBuilder = new CarriageBuilder();

        PassengerCarriage[] carriages = carriageBuilder.buildCarriages(expected, OWNER, 10, 2);
        actual = carriages.length;

        assertEquals(expected, actual);
    }

    @Test
    void buildFreightCars_3cars_arrayLength3() {
        int expected = 3;
        int actual;
        CarriageBuilder carriageBuilder = new CarriageBuilder();

        FreightCar[] carriages = carriageBuilder.buildFreightCars(expected, OWNER, 10);
        actual = carriages.length;

        assertEquals(expected, actual);
    }

    @Test
    void buildLocomotives_4locomotives_arrayLength4() {
        int expected = 4;
        int actual;
        CarriageBuilder carriageBuilder = new CarriageBuilder();

        Locomotive[] carriages = carriageBuilder.buildLocomotives(expected, OWNER);
        actual = carriages.length;

        assertEquals(expected, actual);
    }
}