package train_components;

public abstract class Carriage {
    private final int id;
    private int mileage;





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


    private String ownerCompany;
    private Carriage next;
    private Carriage prev;

    Carriage(int id, String owner) {
        this.id = id;

        this.ownerCompany = owner;

    }



    public int getId() {
        return id;
    }
}
