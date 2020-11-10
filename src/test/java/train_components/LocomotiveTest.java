package train_components;

import exceptions.train_component_exceptions.TryToAccessNotEmptyPlaceException;
import helpClasses.CarriageBuilder;
import org.junit.jupiter.api.Test;
import users.Driver;
import users.User;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LocomotiveTest {
    private static final String OWNER = "BR";

    private Locomotive initLocomotive(){
        return new Locomotive(1, OWNER);
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
    void setCurrentDriver_setDriverWithoutRemovePrevious_TryToAccessNotEmptyPlaceException() {
        Locomotive locomotive = initLocomotive();
        Driver driver1 = new Driver(new User("Hanna", "Wann",1), LocalDate.now());
        Driver driver2 = new Driver(new User("Harry", "Kenn",1), LocalDate.now());

        locomotive.setCurrentDriver(driver1);
        assertEquals(locomotive.getCurrentDriver(), driver1);

        assertThrows(TryToAccessNotEmptyPlaceException.class, () -> locomotive.setCurrentDriver(driver2));
    }

    @Test
    void removeDriver_removeDriver_null() {
        Locomotive locomotive = initLocomotive();
        Driver driver = new Driver(new User("Hanna", "Wann",1), LocalDate.now());

        locomotive.setCurrentDriver(driver);
        assertEquals(locomotive.getCurrentDriver(), driver);

        locomotive.removeDriver();
        assertNull(locomotive.getCurrentDriver());
    }

    @Test
    void makeATrain_2carriages1locomotive_3carriages() {
        int expect = 3;
        int actual;

        CarriageBuilder carriageBuilder = new CarriageBuilder();
        Carriage[] passengerCarriages = carriageBuilder.buildCarriages(2, OWNER, 30, 3);
        Locomotive locomotive = new Locomotive(1, OWNER);

        locomotive.makeATrain(passengerCarriages);

        actual = countCars(locomotive);

        assertEquals(expect, actual);
        assertEquals(locomotive.getNext(), passengerCarriages[0]);
        assertEquals(passengerCarriages[0].getNext(), passengerCarriages[1]);
    }
}