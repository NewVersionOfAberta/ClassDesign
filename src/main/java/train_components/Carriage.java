package train_components;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Carriage {

    private final int id;
    private int mileage;
    private String ownerCompany;
    private Carriage next;
    private Carriage prev;



    Carriage(int id, String owner) {
        if (id < 0) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.ownerCompany = owner;
    }


    public String getOwnerCompany() {
        return ownerCompany;
    }

    public void setOwnerCompany(String ownerCompany) {
        this.ownerCompany = ownerCompany;
    }

    public Carriage getNext() {
        return next;
    }

    public void setNext(Carriage next) {
        this.next = next;
    }

    public Carriage getPrev() {
        return prev;
    }

    public void setPrev(Carriage prev) {
        this.prev = prev;
    }


    public int getId() {
        return id;
    }

    public static Carriage makeATrain(Locomotive locomotive, Carriage... carriages) {
        int carriageNumber = 1;
        if (locomotive == null) {
            log.error("Trying to create train without locomotive");
            throw new NullPointerException();
        }
        Carriage tempCarriage = locomotive;
        tempCarriage.setPrev(null);

        for (Carriage carriage : carriages) {
            couple(tempCarriage, carriage);
            if (tempCarriage instanceof PassengerCarriage){
                ((PassengerCarriage) tempCarriage).setNumberInTrain(carriageNumber);
                carriageNumber++;
            }
            tempCarriage = carriage;
        }
        log.info("Train with first locomotive #{} was created", locomotive.getId());
        return locomotive;
    }

    public static void couple(Carriage firstCarriage, Carriage secondCarriage) {
        if (firstCarriage == null || secondCarriage == null){
            log.error("Trying to couple non-existent cars");
            throw new NullPointerException();
        }
        firstCarriage.setNext(secondCarriage) ;
        secondCarriage.setPrev(firstCarriage);
        log.info("Carriage #{} was coupled with carriage #{}", firstCarriage.getId(), secondCarriage.getId());
    }

    public static void uncouple(Carriage carriage){
        if (carriage == null){
            throw new NullPointerException();
        }

        Carriage nextCar = carriage.next;
        Carriage prevCar = carriage.prev;

        if (nextCar != null){
            nextCar.prev = prevCar;
        }
        if (prevCar != null){
            prevCar.next = nextCar;
        }

        carriage.next = null;
        carriage.prev = null;
    }
}
