package users;

import exceptions.users_exception.ExperienceOutOfBoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConductorTest {

    private final int START_EXPERIENCE = 4;

    private Conductor initConductor(int experience){
        return new Conductor(new User("Igor", "Oloev", 1), experience, 1, 1);
    }

    @Test
    void addYearOfWork_4add1_5() {
        int expected = 5;
        int actual;

        Conductor conductor = initConductor(START_EXPERIENCE);
        conductor.addYearOfWork();

        actual = conductor.getExperience();

        assertEquals(expected, actual);
    }

    @Test
    void addYearOfWork_maxAdd1_ExperienceOutOfBoundException() {

        Conductor driver = initConductor(Driver.MAX_EXPERIENCE);
        driver.addYearOfWork();

        assertThrows(ExperienceOutOfBoundException.class, driver::addYearOfWork);
    }
}