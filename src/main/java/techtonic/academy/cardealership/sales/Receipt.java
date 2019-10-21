package techtonic.academy.cardealership.sales;

import techtonic.academy.cardealership.Utils;
import techtonic.academy.cardealership.vehicles.Vehicle;

import java.text.NumberFormat;
import java.time.LocalDate;

public class Receipt {

    private Vehicle vehicle;
    private Customer customer;
    private LocalDate transactionDate;

    public Receipt(Vehicle vehicle, Customer customer) {
        this.transactionDate = LocalDate.now();
        this.vehicle = vehicle;
        this.customer = customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String receiptDetails() {
        String details =
            Utils.printHzLine(60) +
            "\nCustomer: " + customer.getName() +
            "\nPurchase Date: " + transactionDate +
            "\nVehicle: " + vehicle.getDescription() +
            "\nVIN: " + vehicle.getVin() +
            "\n" + Utils.printHzLine(21) + "Total: " + NumberFormat.getCurrencyInstance().format(vehicle.getPrice()) + Utils.printHzLine(21) +
            "\n" + Utils.printHzLine(60) +
            "\n\n";

        return details;
    }

}
