package techtonic.academy.cardealership.sales;

import techtonic.academy.cardealership.vehicles.Vehicle;

public class Customer {

    private String name;
    private String address;
    private String phone;
    private Vehicle vehicle;

    public Customer(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
