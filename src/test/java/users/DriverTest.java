package users;

import exceptions.users_exception.ExperienceOutOfBoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DriverTest {
    private final int START_EXPERIENCE = 4;

    private Driver initDriver(int experience){
        return new Driver(new User("Igor", "Oloev",1), experience);
    }

    @Test
    void addYearOfWork_4add1_5() {
        int expected = 5;
        int actual;

        Driver driver = initDriver(START_EXPERIENCE);
        driver.addYearOfWork();

        actual = driver.getExperience();

        assertEquals(expected, actual);
    }

    @Test
    void addYearOfWork_maxAdd1_ExperienceOutOfBoundException() {

        Driver driver = initDriver(Driver.MAX_EXPERIENCE);
        driver.addYearOfWork();

        assertThrows(ExperienceOutOfBoundException.class, driver::addYearOfWork);
    }
}