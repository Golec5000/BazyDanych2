package Entity;

public class Warehouse {
    private int id;
    private String address;
    private int administratorId;

    public Warehouse(int id, String address, int administratorId) {
        this.id = id;
        this.address = address;
        this.administratorId = administratorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(int administratorId) {
        this.administratorId = administratorId;
    }
}
