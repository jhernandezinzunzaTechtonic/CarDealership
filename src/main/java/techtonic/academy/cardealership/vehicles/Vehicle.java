package techtonic.academy.cardealership.vehicles;

import com.google.gson.JsonObject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Vehicle {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
    private String type, vin, make, model, color;
    private int year, mileage;

    protected int wheels, fuelCapacity;
    // for gasoline vehicles this is the number of gallons
    // for electric vehicles it will always be 100

    private int fuel;
    // for gasoline vehicles this is the number of gallons
    // for electric vehicles it will be battery percentage

    private BigDecimal price; //  a number displaying the price that the vehicle will be sold at to customers of the dealership.
    private BigDecimal costToDealership; // this number represents the overall amount of money a dealership has invested in a vehicle.
    private String description; // a brief description of the vehicle using the vehicles color, year, make, model and mileage.
    private boolean clean; // this boolean represents if a car is clean or not. (true === clean)

    private LocalDate lastServiced, lastInsured;

    public Vehicle(JsonObject object) {
        this.type = object.get("type").getAsString();
        this.vin = object.get("vin").getAsString();
        this.make = object.get("make").getAsString();
        this.model = object.get("model").getAsString();
        this.color = object.get("color").getAsString();
        this.year = object.get("year").getAsInt();
        this.wheels = object.get("wheels").getAsInt();
        this.mileage = object.get("mileage").getAsInt();
        this.fuel = object.get("fuel").getAsInt();
        this.clean = object.get("clean").getAsBoolean();
        this.price = object.get("price").getAsBigDecimal();
        this.description = object.get("description").getAsString();
        this.costToDealership = price.multiply(BigDecimal.valueOf(.80)); // starts at 80% of the price

        // Parse date values
        String lastServicedString = object.get("maintenanceAndInsurance").getAsJsonObject().get("lastServiced").getAsString();
        String lastInsuredString = object.get("maintenanceAndInsurance").getAsJsonObject().get("lastInsured").getAsString();
        LocalDate servicedDate = LocalDate.parse(lastServicedString, formatter);
        LocalDate insuredDate = LocalDate.parse(lastInsuredString, formatter);
        this.lastServiced = servicedDate;
        this.lastInsured = insuredDate;
    }

    public String getType() {
        return type;
    }

    public String getVin() {
        return vin;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public int getYear() {
        return year;
    }

    public int getWheels() {
        return wheels;
    }

    public int getMileage() {
        return mileage;
    }

    public int getFuel() {
        return fuel;
    }

    public int getFuelCapacity() {
        return fuelCapacity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getCostToDealership() {
        return costToDealership;
    }

    public String getDescription() {
        return description;
    }

    public boolean isClean() {
        return clean;
    }

    public LocalDate getLastServiced() {
        return lastServiced;
    }

    public LocalDate getLastInsured() {
        return lastInsured;
    }

    public void setLastInsured(LocalDate lastInsured) {
        this.lastInsured = lastInsured;
    }

    public void setCostToDealership(BigDecimal cost) {
        this.costToDealership = costToDealership.add(cost);
    }

    public void setLastServiced(LocalDate lastServiced) {
        this.lastServiced = lastServiced;
    }

    public void setFuel(int fuel) {
        System.out.println("Refueling...");
        this.fuel = fuel;
    }

    public void clean() {
        this.clean = true;
    }
}
