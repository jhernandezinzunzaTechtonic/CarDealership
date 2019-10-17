package techtonic.academy.cardealership;

import techtonic.academy.cardealership.sales.Customer;
import techtonic.academy.cardealership.vehicles.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static String input;
    private static Scanner scanner = new Scanner(System.in);
    private static String commandsArray[] = { "/help", "/home", "/exit" };
    private static Dealership userDealership;
    private static Factory userFactory;

    public static void main(String[] args) {
        theDealershipApp();
    }

    public static void theDealershipApp() {
        // Start the app at the main menu
        Utils.displayMainMenu();
        // Begin runtime loop
        while (!"exit".equalsIgnoreCase(input)) {
            input = scanner.nextLine();

            switch (input) {
                case "0":
                    System.out.println("Create a new dealership");
                    Utils.clearSorta();
                    createDealership();
                    input = null;
                    break;
                case "1":
                    System.out.println("Loading car lot inventory...");
                    input = null;
                    break;
                case "2":
                    System.out.println("Loading factory inventory...");
                    input = null;
                    break;
                case "3":
                    System.out.println("Loading purchase screen...");
                    input = null;
                    break;
                case "home":
                    Utils.clearSorta();
                    Utils.displayMainMenu();
                    input = null;
                    break;
            }
        }
        scanner.close();



//        Utils.mainMenu();
//        do {
//            input = scanner.next();
//            if (input.equalsIgnoreCase("/exit")) {
//                System.out.println("Goodbye!");
//                System.exit(0);
//            } else if (Arrays.asList(commandsArray).contains(input)) {
//                // Perform dealership actions
//                switch (input) {
//                    case "/help":
//                        printHelpString(commandsArray);
//                        break;
//                    default:
//                        System.out.println("The default case ran in theDealershipApp method...wait, what!?");
//                }
//            } else {
//                System.out.println("Please enter a valid option (Enter \"/help\" at any point to see a list of commands)");
//            }
//        } while (!Arrays.asList(commandsArray).contains(input));



//        Factory myfactory = new Factory("Jose's Factory");
//
//        String carsOrder = myfactory.getJsonString();
//
//        Dealership myDealership = new Dealership("The Dealz", new BigDecimal(10000000));
//
//        Vehicle myVehicles[] = myfactory.manufacture(carsOrder);
//
//        Car myCar = (Car) myVehicles[0];
//
//        Customer jose = new Customer("Jose", "123 Fake St.", "(123) 234-5678");

//        for(int i = 0; i< myVehicles.length; i++){
//            String type = myVehicles[i].getType();
//            switch (type) {
//                case "car":
//                    Car theCar = (Car) myVehicles[i];
//                    System.out.println("Car");
//                    System.out.println("MPG: " + theCar.getMpg());
//                    break;
//                case "truck":
//                    Truck theTruck = (Truck) myVehicles[i];
//                    System.out.println("New Truck");
//                    System.out.println("MPG: " + theTruck.getMpg());
//                    System.out.println("Towing Capacity: " + theTruck.getTowingCapacity());
//                    break;
//                case "motorcycle":
//                    Motorcycle theMotorcycle = (Motorcycle) myVehicles[i];
//                    System.out.println("New Motorcycle");
//                    System.out.println("MPG: " + theMotorcycle.getMpg());
//                    break;
//                case "ev":
//                    Ev theEv = (Ev) myVehicles[i];
//                    System.out.println("New EV");
//                    System.out.println("Range: " + theEv.getRange());
//                    break;
//            }
//
//            System.out.println("Vehicle type: " + myVehicles[i].getType());
//            System.out.println("Vin: " + myVehicles[i].getVin());
//            System.out.println("Make: " + myVehicles[i].getMake());
//            System.out.println("Model: " + myVehicles[i].getModel());
//            System.out.println("Color: " + myVehicles[i].getColor());
//            System.out.println("Year: " + myVehicles[i].getYear());
//            System.out.println("Wheels: " + myVehicles[i].getWheels());
//            System.out.println("Mileage: " + myVehicles[i].getMileage());
//            System.out.println("Price: " + myVehicles[i].getPrice());
//            System.out.println("Last Serviced: " + myVehicles[i].getLastServiced());
//            System.out.println("Last Insured: " + myVehicles[i].getLastInsured());
//            System.out.println("Description: " + myVehicles[i].getDescription());
//            System.out.println("Cost To Dealership: " + myVehicles[i].getCostToDealership());
//            System.out.println("Clean: " + myVehicles[i].isClean());
//            System.out.println("Fuel: " + myVehicles[i].getFuel());
//            System.out.println("Fuel Capacity: " + myVehicles[i].getFuelCapacity());
//            System.out.println();
//        }

//        System.out.println(myDealership.name);
//        System.out.println(myDealership.balance);
//        System.out.println(myDealership.carLot);
//        System.out.println(myDealership.salesHistory);

//        myDealership.purchaseVehicles(myfactory, new BigDecimal(99.00));
//        System.out.println(myDealership.checkInsurance(myVehicles[0]));

//        myDealership.checkInsurance(myVehicles[0]);
//        myDealership.checkMaintenance(myVehicles[0]);

//        myDealership.renewInsurance(myVehicles[0]);
//        myDealership.serviceVehicle(myVehicles[0]);

//        myDealership.testDriveVehicle(myCar);
//        System.out.println();
//
//        myDealership.refuelVehicle(myCar);
//        myDealership.washVehicle(myCar);
//        myDealership.serviceVehicle(myCar);
//        myDealership.renewInsurance(myCar);
//
//        myDealership.testDriveVehicle(myCar);

//        myDealership.purchaseVehicles(myfactory);
//        myfactory.findAndRemoveCar(myVehicles[0]);
//        myDealership.sellVehicle(myVehicles[0], jose);
    }

    public static void printHelpString(String[] commands) {
        String helpString = "Here is a list of possible commands:\n";

        for (int i = 0; i < commands.length; i++) {
            helpString += "\n" + commands[i];
        }
        Utils.printHzLine(50);
        System.out.println(helpString);
        Utils.printHzLine(50);
    }

    public static void createDealership() {
        BigDecimal startingBalance = new BigDecimal(5000000);
        System.out.println("\nLet's create a dealership, please enter a name for your dealership:  ");
        input = scanner.nextLine();
        Utils.clearSorta();
        userDealership = new Dealership(input, startingBalance);
        userDealership.dealershipSummary();

//        do {
//            input = scanner.next();
//            if (input.equalsIgnoreCase("/exit")) {
//                System.out.println("Goodbye!");
//                System.exit(0);
//            } else if (Arrays.asList(commandsArray).contains(input)) {
//                // Perform dealership actions
//                switch (input) {
//                    case "/help":
//                        printHelpString(commandsArray);
//                        break;
//                    default:
//                        System.out.println("The default case ran in theDealershipApp method...wait, what!?");
//                }
//            } else {
//                System.out.println("\nPlease enter a valid option (Enter \"/help\" at any point to see a list of commands)");
//            }
//        } while (!Arrays.asList(commandsArray).contains(input));
    }
}
