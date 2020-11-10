package train_components;

import exceptions.train_component_exceptions.TryToAccessNotEmptyPlaceException;
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
}