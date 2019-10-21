package techtonic.academy.cardealership;

import techtonic.academy.cardealership.sales.Customer;
import techtonic.academy.cardealership.vehicles.*;
import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    private static String input;
    private static boolean dealership = false;
    private static Scanner scanner = new Scanner(System.in);
    private static Dealership userDealership;
    private static Factory userFactory = new Factory("Jose's Factory");

    public static void main(String[] args) {
        theDealershipApp();
    }

    public static void theDealershipApp() {
        // Start the app at the main menu
        while (!"exit".equalsIgnoreCase(input)) {
            Utils.displayMainMenu(dealership);
            System.out.println("Choose an option: ");

            try {
                input = scanner.nextLine();
            } catch(NoSuchElementException e) {
                boolean testing = scanner.hasNextLine();
                System.out.println(testing);
            }

            switch (input) {
                case "0":
                    if(dealership == true) {
                        System.out.println("\nYou can only have 1 dealership, type \"stats\" to view your dealership details.\n");
                    } else {
                        System.out.println("Create a new dealership");
                        Utils.clearSorta();
                        createDealership();
                        input = null;
                    }
                    break;
                case "1":
                    System.out.println("Loading car lot inventory...");
                    if(userDealership == null) {
                        System.out.println("\nYou don't currently have a dealership, please create one.\n");
                    } else {
                        userDealership.carLotSummary();
                        input = null;
                    }
                    break;
                case "2":
                    System.out.println("Loading factory inventory...");
                    Utils.clearSorta();
                    userFactory.displayInventory();
                    input = null;
                    break;
                case "3":
                    if(userDealership == null) {
                        System.out.println("\nYou don't currently have a dealership, please create one.\n");
                    } else {
                        System.out.println("Enter the vehicle number you wish to purchase: ");
                        try {
                            int num = Integer.parseInt(scanner.nextLine());
                            if (num == 0) {
                                System.out.println("Error: Please enter a valid car from the factory list.\n");
                            } else {
                                userDealership.purchaseVehicle(num);
                                input = null;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("\nError: Please enter a number.\n");
                        }
                    }
                    break;
                case "4":
                    if(userDealership == null) {
                        System.out.println("\nYou don't currently have a dealership, please create one.\n");
                    } else if(userDealership.carLot.size() == 0) {
                        System.out.println("\nYour car lot is currently empty.\n");
                    } else {
                        System.out.println("Enter the vehicle number you wish to sell");
                        try {
                            int num = Integer.parseInt(scanner.nextLine());
                            if (num == 0) {
                                System.out.println("Error: Please enter a valid car from the car lot list.\n");
                            } else {
                                Customer customer = new Customer("jose", "123 Fake St", "123-456-7899");
                                Vehicle vehicle = userDealership.carLot.get(num - 1);
                                userDealership.sellVehicle(vehicle, customer);

                                input = null;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("\nError: Please enter a number.\n");
                        }
                    }
                    break;
                case "5":
                    if(userDealership == null) {
                        System.out.println("\nYou don't currently have a dealership, please create one.\n");
                    } else if(userDealership.carLot.size() == 0) {
                        System.out.println("\nYour car lot is currently empty.\n");
                    } else {
                        System.out.println("Enter the vehicle number you wish to test drive");
                        try {
                            int num = Integer.parseInt(scanner.nextLine());
                            if (num == 0) {
                                System.out.println("Error: Please enter a valid car from the car lot list.\n");
                            } else {
                                Vehicle vehicle = userDealership.carLot.get(num - 1);
                                if(userDealership.testDriveVehicle(vehicle) == true) {
                                    // Do nothing :)
                                } else {
                                    testDrive(vehicle);
                                }

                                input = null;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("\nError: Please enter a number.\n");
                        }
                    }
                    break;
                case "stats":
                    if(userDealership == null) {
                        System.out.println("\nYou don't currently have a dealership, please create one.\n");
                    } else {
                        userDealership.dealershipSummary();
                        input = null;
                    }
                    break;
                case "sales":
                    System.out.println("Loading sales history...");
                    if(userDealership == null) {
                        System.out.println("\nYou don't currently have a dealership, please create one.\n");
                    } else {
                        if(userDealership.salesHistory.size() == 0) {
                            System.out.println("\nYour dealership has not sold any cars yet.\n");
                        } else {
                            Utils.clearSorta();
                            userDealership.salesSummary();
                            input = null;
                        }
                    }
                    break;
            }
        }
        System.out.println("Goodbye!");
        scanner.close();

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

    public static void createDealership() {
        BigDecimal startingBalance = new BigDecimal(5000000);
        System.out.println("\nLet's create a dealership, please enter a name for your dealership:  ");
        String name = "";

        do {
            name = scanner.nextLine();

            if (name.isBlank() || name.isEmpty()) {
                System.out.println("Please enter a valid name for your dealership");
            } else {
                Utils.clearSorta();
                userDealership = new Dealership(name, startingBalance);
                userDealership.dealershipSummary();
                dealership = true;
            }
        } while (name.isBlank() || name.isEmpty());
    }

    public static void testDrive(Vehicle vehicle) {
        Scanner testDriveScanner = new Scanner(System.in);
        String testInput = null;
        System.out.println("\nWould you like to prepare this vehicle for a test drive?\nPlease enter: Y/N ");

        while (!"N".equalsIgnoreCase(testInput)) {
            testInput = testDriveScanner.nextLine();

            if("Y".equalsIgnoreCase(testInput)) {
                userDealership.washVehicle(vehicle);
                userDealership.renewInsurance(vehicle);
                userDealership.refuelVehicle(vehicle);
                userDealership.serviceVehicle(vehicle);
                break;
            }

            if("N".equalsIgnoreCase(testInput)) {
                System.out.println("You opted out of preparing this vehicle for a test drive.");
                break;
            }
        }
//        testDriveScanner.close();
    }

}
