package helpClasses;

import exceptions.help_class_exception.UnexpectedAmountOfCarsException;
import train_components.Carriage;
import train_components.FreightCar;
import train_components.Locomotive;
import train_components.PassengerCarriage;

public class CarriageBuilder {

    public static int useId() {
        return id++;
    }

    private static int id = 0;

    private void validateAmount(int amount){
        if (amount < 0){
            throw new UnexpectedAmountOfCarsException();
        }
    }

    public Carriage[] buildCarriages(int amount, String owner, int passengerCapacity, int conductorCapacity){
        validateAmount(amount);
        PassengerCarriage[] passengerCarriages = new PassengerCarriage[amount];
        for (int i = 0; i < amount; i++){
            passengerCarriages[i] = new PassengerCarriage(id, owner, passengerCapacity, conductorCapacity);
            id++;
        }
        return passengerCarriages;
    }

    public Carriage[] buildFreightCars(int amount, String owner, int liftingCapacity){
        validateAmount(amount);
        FreightCar[] freightCars = new FreightCar[amount];
        for (int i = 0; i < amount; i++){
            freightCars[i] = new FreightCar(id, owner, liftingCapacity);
            id++;
        }
        return freightCars;
    }

    public Carriage[] buildLocomotives(int amount, String owner){
        validateAmount(amount);
        Locomotive[] locomotives = new Locomotive[amount];
        for (int i = 0; i < amount; i++){
            locomotives[i] = new Locomotive(id, owner);
            id++;
        }
        return locomotives;
    }
}
