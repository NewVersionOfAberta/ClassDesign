package train_components;

import exceptions.train_component_exceptions.freight_carriage.FreightCarriageOverloadException;
import exceptions.train_component_exceptions.freight_carriage.TryToRemoveNonexistentCargoException;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class FreightCar extends Carriage {

    public FreightCar(int id, String owner, int liftingCapacity) {
        super(id, owner);
        this.liftingCapacity = liftingCapacity;
        log.info("Freight car #{} was created", id);
    }

    public int getLiftingCapacity() {
        return liftingCapacity;
    }

    private final int liftingCapacity;

    public int getCurrentCargo() {
        return currentCargo;
    }

    private int currentCargo;

    public void addCargo(int cargo) {
        if (currentCargo + cargo <= liftingCapacity){
            currentCargo += cargo;
            log.info("Freight car #{}. Add cargo {}, current value: {}", getId(), cargo, currentCargo);
        }else
            log.error("Freight car #{}. Too big cargo({}) for this carriage. Lifting capacity: {}", getId(),
                    currentCargo + cargo, liftingCapacity);
            throw new FreightCarriageOverloadException();
    }

    public void removeCargo(int cargoToRemove){
        if (currentCargo - cargoToRemove >= 0){
            currentCargo -= cargoToRemove;
            log.info("Freight car #{}. New current cargo: {}", getId(), currentCargo);
        }else {
            log.error("Freight car #{}. Trying to remove cargo {}, when total current cargo {}", getId(), cargoToRemove, currentCargo);
            throw new TryToRemoveNonexistentCargoException();
        }

    }


}
