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


    public void couple(Carriage nextCarriage) {
        this.setNext(nextCarriage) ;
        nextCarriage.setPrev(this);
        log.info("Carriage #{} was coupled with carriage #{}", this.getId(), nextCarriage.getId());
    }

    public void uncouple(){

        Carriage nextCar = this.next;
        Carriage prevCar = this.prev;

        if (nextCar != null){
            nextCar.prev = prevCar;
        }
        if (prevCar != null){
            prevCar.next = nextCar;
        }

        this.next = null;
        this.prev = null;
    }
}
