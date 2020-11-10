package train_components;

import exceptions.train_component_exceptions.TryToAccessNotEmptyPlaceException;
import lombok.extern.slf4j.Slf4j;
import users.Driver;

import static java.util.Objects.*;

@Slf4j
public class Locomotive extends Carriage {

    private Driver currentDriver;

    public Locomotive(int id, String owner) {
        super(id, owner);
        log.info("Locomotive #{} was created", id);
    }

    public Driver getCurrentDriver() {
        return currentDriver;
    }

    public void setCurrentDriver(Driver currentDriver) {
        if (this.currentDriver != null) {
            log.warn("Locomotive #{}. Driver #{} tries to sit in the seat of driver #{}",getId(), currentDriver.getUser().getId(),
                    this.currentDriver.getUser().getId());
            throw new TryToAccessNotEmptyPlaceException();
        }else{
            log.info("Locomotive #{}. Set new driver #{}",getId(),
                    currentDriver != null ? currentDriver.getUser().getId() : "No driver");
            requireNonNull(currentDriver).setCurrentCarriageId(getId());
            this.currentDriver = currentDriver;
        }
    }

    public Driver removeDriver(){
        Driver result = currentDriver;
        currentDriver = null;
        log.info("Locomotive #{}. Driver #{} was removed", getId(), result.getUser().getId());
        result.setCurrentCarriageId(-1);

        return result;
    }

}
