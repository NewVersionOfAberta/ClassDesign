import helpClasses.CarriageBuilder;
import lombok.extern.slf4j.Slf4j;
import train_components.Carriage;
import train_components.FreightCar;
import train_components.Locomotive;
import train_components.PassengerCarriage;
import users.Conductor;
import users.Passenger;
import users.User;

import java.time.LocalDate;
import java.util.*;

import static helpClasses.CarriageBuilder.useId;


@Slf4j
public class Runner {

    final static String[] NAMES = {"Ivan", "Petr", "Dmitry", "Andre", "Ilya", "Maxim",
            "Mira", "Victoria", "Natalia", "Helen", "Marya", "Daria"};
    final static String[] SURNAMES = {"Karpinovich", "Fox", "Wright", "Edgeworth", "May", "Smith", "Doy",
            "Gurilevich", "Сracker"};

    static private int id = 0;
    private static Random random = new Random();

    private static User makeUser(){
        return new User(NAMES[random.nextInt(NAMES.length)], SURNAMES[random.nextInt(SURNAMES.length)], id);
    }

    private static void fillPassengerCarriages(Carriage[] carriages){

        PassengerCarriage tempCarriage;
        for (Carriage carriage : carriages){
            if (carriage instanceof PassengerCarriage){
                tempCarriage = (PassengerCarriage) carriage;
                for (int i = 0; i < tempCarriage.getPassengerCapacity(); i++) {
                    tempCarriage.addPassenger(
                            new Passenger(makeUser(), id, i + 1, tempCarriage.getNumberInTrain()));
                    id++;
                }
                for (int i = 0; i < tempCarriage.getConductorCapacity(); i++){
                    tempCarriage.addConductor(new Conductor(makeUser(), LocalDate.now(), i + 1, tempCarriage.getNumberInTrain()));
                    id++;
                }
            }
        }

    }


    public static void main(String[] args) {
        int locomotivesAmount = 3;
        int passengerCarriages = 4;
        int passengerCapacity = 30;
        int conductorCapacity = 4;
        int freightCarsAmount = 5;
        int liftingCapacity = 100;

        String owner = "BR";
        List<Carriage[]> carriages = new ArrayList<>();
        CarriageBuilder carriageBuilder = new CarriageBuilder();


        Carriage[] locomotives = carriageBuilder.buildLocomotives(locomotivesAmount, owner);


        carriages.add(carriageBuilder.buildCarriages(passengerCarriages, owner, passengerCapacity, conductorCapacity));
        carriages.add(carriageBuilder.buildFreightCars(freightCarsAmount, owner, liftingCapacity));
        carriages.add(new Carriage[]{
                new PassengerCarriage(useId(), owner, passengerCapacity, conductorCapacity),
                new FreightCar(useId(), owner, liftingCapacity)});

        for(Carriage[] cars : carriages){
            fillPassengerCarriages(cars);
        }
        for (int i = 0; i < locomotivesAmount; i++ )
        Carriage.makeATrain((Locomotive) locomotives[i], carriages.get(i));
    }
}
