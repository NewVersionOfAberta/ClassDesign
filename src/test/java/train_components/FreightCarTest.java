package train_components;

import exceptions.train_component_exceptions.freight_carriage.FreightCarriageOverloadException;
import exceptions.train_component_exceptions.freight_carriage.TryToRemoveNonexistentCargoException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class FreightCarTest {

    private final String OWNER = "BR";
    private final int LIFTING_CAPACITY = 10;

    private FreightCar init(){
        return new FreightCar(1, OWNER, LIFTING_CAPACITY);
    }

    @Test
    void addCargo_CargoOverload_FreightCarriageOverloadException() {

        FreightCar freightCar = init();

        assertThrows(FreightCarriageOverloadException.class, () -> freightCar.addCargo(LIFTING_CAPACITY * 2));
    }

    @Test
    void addCargo_4and2_6() {
        int expected = 6;
        int firstCargo = 4;
        int secondCargo = 2;
        int actual;

        FreightCar freightCar = init();

        freightCar.addCargo(firstCargo);
        freightCar.addCargo(secondCargo);
        actual = freightCar.getCurrentCargo();
        assertEquals(expected, actual);
    }

    @Test
    void removeCargo_add4remove2_2() {
        int expected = 2;
        int startCargo = 4;
        int cargoToRemove = 2;
        int actual;

        FreightCar freightCar = init();

        freightCar.addCargo(startCargo);
        freightCar.removeCargo(cargoToRemove);
        actual = freightCar.getCurrentCargo();

        assertEquals(expected, actual);

    }

    @Test
    void removeCargo_add10remove11_TryToRemoveNonexistentCargoException() {
        int initCargo = 10;
        int cargoToRemove = 11;

        FreightCar freightCar = init();
        freightCar.addCargo(initCargo);

        assertThrows(TryToRemoveNonexistentCargoException.class, () -> freightCar.removeCargo(cargoToRemove));
    }
}