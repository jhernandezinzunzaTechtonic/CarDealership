package techtonic.academy.cardealership.sales;

import techtonic.academy.cardealership.vehicles.Vehicle;

public class Receipt {

    private Vehicle vehicle;
    private Customer customer;

    public Receipt(Vehicle vehicle, Customer customer) {
        this.vehicle = vehicle;
        this.customer = customer;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
