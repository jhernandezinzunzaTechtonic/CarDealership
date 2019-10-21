package techtonic.academy.cardealership;

import techtonic.academy.cardealership.sales.Customer;
import techtonic.academy.cardealership.sales.Receipt;
import techtonic.academy.cardealership.vehicles.*;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class Dealership {

    public String name;
    public BigDecimal balance;
    public ArrayList<Vehicle> carLot = new ArrayList<>();
    public ArrayList<Receipt> salesHistory = new ArrayList<>();
    public ArrayList<String> models = new ArrayList<>();

    public Dealership(String name, BigDecimal startingBalance) {
        this.name = name;
        this.balance = startingBalance;
    }

    public void purchaseVehicles(Factory factory) {
        System.out.println("Purchasing Vehicles...");
        Vehicle[] vehicles = factory.readyToShip;

        for (int i = 0; i < vehicles.length; i++) {
            BigDecimal priceOfCar = vehicles[i].getPrice();
            String model = vehicles[i].getModel();
            System.out.println("\nCar: " + vehicles[i].getDescription());
            System.out.println("Price: " + priceOfCar);
            System.out.println("Remaining balance: " + balance);

            if(checkModel(model)) {
                if (balance.compareTo(priceOfCar) == 1) {
                    System.out.println("Purchased!");
                    balance = balance.subtract(priceOfCar);
                    addToCarLot(vehicles[i]);
                    factory.findAndRemoveCar(vehicles[i], i);
                } else if (balance.compareTo(priceOfCar) == -1) {
                    System.out.println("Purchase Failed! Price exceeds balance.");
                    break;
                }
            } else {
                //Do nothing
            }
        }
    }

    public void purchaseVehicle(int vehicleIndex) {
        vehicleIndex -= 1;
        System.out.println("\n" + Utils.printHzLine(50));
        System.out.println("Purchasing Vehicle...");
        Vehicle[] vehicles = Factory.readyToShip;

        try {
            BigDecimal priceOfCar = vehicles[vehicleIndex].getPrice();
            String model = vehicles[vehicleIndex].getModel();
            System.out.println("\nCar: " + vehicles[vehicleIndex].getDescription());
            System.out.println("\nVIN: " + vehicles[vehicleIndex].getVin());
            System.out.println("\nPrice: " + priceOfCar);

            if(checkModel(model)) {
                if (balance.compareTo(priceOfCar) == 1) {
                    System.out.println("Purchased!");
                    balance = balance.subtract(priceOfCar);
                    addToCarLot(vehicles[vehicleIndex]);
                    Factory.findAndRemoveCar(vehicles[vehicleIndex], vehicleIndex);
                    System.out.println("\nRemaining balance: " + balance + "\n");
                } else if (balance.compareTo(priceOfCar) == -1) {
                    System.out.println("\nPurchase Failed! Price exceeds balance.\n");
                }
            } else {
                // Do nothing
            }
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Please enter a valid car from the factory list.\n");
        }
    }

    public boolean checkInsurance(Vehicle vehicle) {
        final long MAX_INSURED_PERIOD = 30; // Max days that insurance is valid
        LocalDate expireDate = LocalDate.now().minusDays(MAX_INSURED_PERIOD);
        LocalDate vehicleInsuredDate = vehicle.getLastInsured();

//        System.out.println("lastInsured on: " + vehicleInsuredDate);
        if (vehicleInsuredDate.isAfter(expireDate)) {
            System.out.println("This vehicle has valid Insurance.");
            return true;
        } else {
            System.out.println("This vehicle has expired insurance.");
            return false;
        }
    }

    public boolean checkMaintenance(Vehicle vehicle) {
        final long MAX_INSURED_PERIOD = 90; // Max days that insurance is valid
        LocalDate expireDate = LocalDate.now().minusDays(MAX_INSURED_PERIOD);
        LocalDate vehicleServicedDate = vehicle.getLastServiced();

//        System.out.println("lastServiced on: " + vehicleServicedDate);
        if (vehicleServicedDate.isAfter(expireDate)) {
            System.out.println("This vehicle has been serviced in the last 90 days.");
            return true;
        } else {
            System.out.println("This vehicle has not been serviced in the last 90 days.");
            return false;
        }
    }

    public void renewInsurance(Vehicle vehicle) {
        // TODO: add logic for renewing insurance.
        LocalDate currentDate = LocalDate.now();
        BigDecimal randomInsuranceCost = new BigDecimal((Math.random() * ((300 - 100) + 1)) + 100);

        if (checkInsurance(vehicle)) {
            System.out.println("You already have valid insurance.");
        } else {
            System.out.println("Renewing Insurance...");
            vehicle.setLastInsured(currentDate);
            this.balance = balance.subtract(randomInsuranceCost);
            vehicle.setCostToDealership(randomInsuranceCost);
            System.out.println("Insurance renewed on: " + currentDate);
//            System.out.println("new costToDealership: " + vehicle.getCostToDealership());
        }
    }

    public void serviceVehicle(Vehicle vehicle) {
        // TODO: add logic for servicing vehicle.
        LocalDate currentDate = LocalDate.now();
        BigDecimal randomServiceCost = new BigDecimal((Math.random() * ((2000 - 100) + 1)) + 100);

        if (checkMaintenance(vehicle)) {
            System.out.println("You have recently serviced your vehicle already.");
        } else {
            vehicle.setLastServiced(currentDate);
            this.balance = balance.subtract(randomServiceCost);
            vehicle.setCostToDealership(randomServiceCost);
            System.out.println("Serviced!\nNew costToDealership: " + NumberFormat.getCurrencyInstance().format(vehicle.getCostToDealership()) + "\n");
        }
    }

    public void refuelVehicle(Vehicle vehicle) {
        // TODO: add logic for refueling vehicle.
        vehicle.setFuel(vehicle.getFuelCapacity());
        this.balance = balance.subtract(new BigDecimal(10));
    }

    public void washVehicle(Vehicle vehicle) {
        // TODO: add logic for washing vehicle.
        if (vehicle.isClean()) {
            System.out.println("Already Clean...");
        } else {
            System.out.println("Cleaning...");
            vehicle.clean();
            this.balance = balance.subtract(new BigDecimal(10));
            vehicle.setCostToDealership(new BigDecimal(10));
        }
    }

    public boolean testDriveVehicle(Vehicle vehicle) {
        boolean isClean, isFueled, isInsured, isServiced;

        System.out.println(Utils.printHzLine(50) + "\nChecking Vehicle...");
        isClean = vehicle.isClean();
//        System.out.println(isClean);

        isFueled = (vehicle.getFuel() == vehicle.getFuelCapacity());
//        System.out.println(isFueled);

        isInsured = checkInsurance(vehicle);
//        System.out.println(isInsured);

        isServiced = checkMaintenance(vehicle);
//        System.out.println(isServiced);

        if (isClean && isFueled && isInsured && isServiced) {
            System.out.println("\nEnjoy your test drive!\n" + Utils.printHzLine(50) + "\n\n");
            return true;
        } else {
            System.out.println("Sorry, this vehicle is not ready to be test driven");
            return false;
        }
    }

    public BigDecimal sellVehicle(Vehicle vehicle, Customer customer) {
        System.out.println("\n" + Utils.printHzLine(50) + "\nSelling Vehicle...");
        washVehicle(vehicle);
        this.balance = balance.add(vehicle.getPrice());
        Receipt receipt = new Receipt(vehicle, customer);
        salesHistory.add(receipt);
        customer.setVehicle(vehicle);
        findAndRemoveCar(vehicle.getVin());
        System.out.println("Sale Completed!");
        System.out.println("Car sold to " + customer.getName() + "!");
        System.out.println("New balance: " + NumberFormat.getCurrencyInstance().format(this.balance));
        System.out.println(Utils.printHzLine(50) + "\n");

        return this.balance;
    }

    public void addToCarLot(Vehicle vehicle) {
        carLot.add(vehicle);
        models.add(vehicle.getModel());
        System.out.println(Utils.printHzLine(50));
        System.out.println("\n<-- Car purchased and added to Car Lot: " + vehicle.getMake() + vehicle.getModel() + " -->\n");
    }

    public boolean checkModel(String model) {
        boolean allow = false;
        int freq = Collections.frequency(models, model);

        try {
            if (freq < 3) {
//                System.out.println(freq);
                System.out.println("You have less than 3 vehicles of model: " + model);
                System.out.println("Proceeding with purchase attempt.");
                allow = true;
            } else {
                System.out.println("You already have 3 vehicles of model: " + model);
                System.out.println("Cancelling purchase attempt.");
                allow = false;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return allow;
    }

    // Remove car from readyToShip list
    public void findAndRemoveCar(String vin) {
        System.out.println("Searching for car...");
        for (int i = 0; i < carLot.size(); i++) {
            if (carLot.get(i).getVin().equals(vin)) {
                System.out.println("Car found, removing from lot...");
                carLot.remove(i);
            }
        }
    }

    public void dealershipSummary() {
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_RESET = "\u001B[0m";
        String dealershipString =
            ANSI_GREEN +
            Utils.printHzLine(20) + " Dealership Details " + Utils.printHzLine(20) +
            "\n" + Utils.printHzLine(60) +
            "\nDealership Name: " + name +
            "\nCurrent Balance: " + NumberFormat.getCurrencyInstance().format(balance) +
            "\n# of cars in lot: " + carLot.size() +
            "\n# of cars sold: " + salesHistory.size() +
            "\n" + "<- FILL IN SALES HISTORY INFORMATION ->" +
            "\n" +
            ANSI_RESET;

        System.out.println(dealershipString);
    }

    public void carLotSummary() {

        if(carLot.size() == 0) {
            System.out.println("\nYour car lot is currently empty\n");
        } else {
            for (int i = 0; i < carLot.size(); i++) {
                String type = carLot.get(i).getType();
                switch (type) {
                    case "car":
                        Car theCar = (Car) carLot.get(i);
                        System.out.println(Utils.printHzLine(50));
                        System.out.println("Vehicle " + (i+1) + " - Car: " + theCar.getDescription());
                        break;
                    case "truck":
                        Truck theTruck = (Truck) carLot.get(i);
                        System.out.println(Utils.printHzLine(50));
                        System.out.println("Vehicle " + (i+1) + " - Truck: " + theTruck.getDescription());
                        break;
                    case "motorcycle":
                        Motorcycle theMotorcycle = (Motorcycle) carLot.get(i);
                        System.out.println(Utils.printHzLine(50));
                        System.out.println("Vehicle " + (i+1) + " - Motorcycle: " + theMotorcycle.getDescription());
                        break;
                    case "ev":
                        Ev theEv = (Ev) carLot.get(i);
                        System.out.println(Utils.printHzLine(50));
                        System.out.println("Vehicle " + (i+1) + " - EV: " + theEv.getDescription());
                        break;
                }
            }
            System.out.println();
        }
    }

    public void salesSummary() {

        for (Receipt receipt : salesHistory) {
            System.out.println(receipt.receiptDetails());
            Utils.printHzLine(60);
        }

    }

}
