package techtonic.academy.cardealership;

import techtonic.academy.cardealership.sales.Customer;
import techtonic.academy.cardealership.sales.Receipt;
import techtonic.academy.cardealership.vehicles.Vehicle;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
            checkModel(model);

            if (balance.compareTo(priceOfCar) == 1) {
                System.out.println("Purchased!");
                balance = balance.subtract(priceOfCar);
                addToCarLot(vehicles[i]);
                factory.findAndRemoveCar(vehicles[i], i);
            } else if (balance.compareTo(priceOfCar) == -1) {
                System.out.println("Purchase Failed! Price exceeds balance.");
                break;
            }
        }
    }

    public boolean checkInsurance(Vehicle vehicle) {
        final long MAX_INSURED_PERIOD = 30; // Max days that insurance is valid
        LocalDate expireDate = LocalDate.now().minusDays(MAX_INSURED_PERIOD);
        LocalDate vehicleInsuredDate = vehicle.getLastInsured();

//        System.out.println("lastInsured on: " + vehicleInsuredDate);
        if (vehicleInsuredDate.isAfter(expireDate)) {
            System.out.println("Valid Insurance");
            return true;
        } else {
            System.out.println("Expired insurance");
            return false;
        }
    }

    public boolean checkMaintenance(Vehicle vehicle) {
        // TODO: add logic for checking maintenance.
        final long MAX_INSURED_PERIOD = 90; // Max days that insurance is valid
        LocalDate expireDate = LocalDate.now().minusDays(MAX_INSURED_PERIOD);
        LocalDate vehicleServicedDate = vehicle.getLastServiced();

//        System.out.println("lastServiced on: " + vehicleServicedDate);
        if (expireDate.isAfter(vehicleServicedDate)) {
            System.out.println("It has been less than 90 days since your last service, you are good to go!");
            return true;
        } else {
            System.out.println("It has been more than 90 days since your last service.");
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
            System.out.println("new costToDealership: " + vehicle.getCostToDealership());
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

    public void testDriveVehicle(Vehicle vehicle) {
        // TODO: add logic for test driving a vehicle.
        boolean isClean, isFueled, isInsured, isServiced;

        isClean = vehicle.isClean();
//        System.out.println(isClean);

        isFueled = (vehicle.getFuel() == vehicle.getFuelCapacity());
//        System.out.println(isFueled);

        isInsured = checkInsurance(vehicle);
//        System.out.println(isInsured);

        isServiced = checkMaintenance(vehicle);
//        System.out.println(isServiced);

        if (isClean && isFueled && isInsured && isServiced) {
            System.out.println("Enjoy your test drive!");
        } else {
            System.out.println("Sorry, this vehicle is not ready to be test driven");
        }
    }

    public BigDecimal sellVehicle(Vehicle vehicle, Customer customer) {
        // TODO: add logic for selling vehicle.
        washVehicle(vehicle);
        balance = balance.add(vehicle.getPrice());
        Receipt receipt = new Receipt(vehicle, customer);
        salesHistory.add(receipt);
        customer.setVehicle(vehicle);
        findAndRemoveCar(vehicle.getVin());
        return this.balance;
    }

    public void addToCarLot(Vehicle vehicle) {
        carLot.add(vehicle);
        models.add(vehicle.getModel());
        System.out.println("\n<-- Car purchased and added to Car Lot: " + vehicle.getMake() + vehicle.getModel() + " -->");
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
        for (int i = 0; i < carLot.size(); i++) {
            if (carLot.get(i).getVin().equals(vin)) {
                System.out.println("Match found, removing car...");
                carLot.remove(i);
            }
        }
    }

    public void dealershipSummary() {
        String ANSI_GREEN = "\u001B[32m";
        String ANSI_RESET = "\u001B[0m";
        String dealershipString =
            ANSI_GREEN +
            Utils.printHzLine(20) + " Dealership Details " + Utils.printHzLine(20) + "\n" +
            Utils.printHzLine(60) + "\n" +
            "Dealership Name: " + name + "\n" +
            "Starting Balance: " + balance.setScale(2, RoundingMode.HALF_EVEN) + "\n" +
            "# of cars in lot: " + carLot.size() + "\n" +
            ANSI_RESET;

        System.out.println(dealershipString);
    }
}
