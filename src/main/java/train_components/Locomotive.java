package train_components;

import exceptions.train_component_exceptions.TryToAccessNotEmptyPlaceException;
import lombok.extern.slf4j.Slf4j;
import users.Driver;

@Slf4j
public class Locomotive extends Carriage {
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
            log.info("Locomotive #{}. Set new driver #{}",getId(), currentDriver != null ? currentDriver.getUser().getId() : "No driver");
            this.currentDriver = currentDriver;
        }
    }
    public void removeDriver(){
        currentDriver = null;
        log.info("Locomotive #{}. Driver #{} was removed", getId(), currentDriver.getUser().getId());
    }

    private Driver currentDriver;

}
