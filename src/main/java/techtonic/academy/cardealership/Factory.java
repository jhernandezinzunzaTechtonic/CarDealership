package techtonic.academy.cardealership;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import techtonic.academy.cardealership.vehicles.*;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;


class Factory {

    static String name;
    static Vehicle[] readyToShip;

    public Factory(String name) {
        this.name = name;
        this.readyToShip = Factory.manufacture(getJsonString());
    }

    // Parse a JSON file from the `resources` folder and return the file contents as a JSON String
    public String getJsonString() {
        // Initialize jsonString as `null` so we can access it later and return it after
        // giving it a value inside the try/catch block
        String jsonString = null;

        // Get the ClassLoader so that we can access the `resources` folder without
        // knowing the exact URL of the json
        // For more info on ClassLoader, read here: https://www.baeldung.com/java-classloaders
        ClassLoader loader = Factory.class.getClassLoader();

        // Use the ClassLoader to get the URL of the JSON file
//        URL url = loader.getResource("vehicleData.json");
//        URL url = loader.getResource("oneVehicle.json");
        URL url = loader.getResource("lotsOfCars.json");

        // Use the URL to get the path to the file as a String
        // Use Objects.requireNonNull to ensure that the resource was actually found
        // before attempting to get its path.
        String pathString = Objects.requireNonNull(url).getPath();
//        System.out.println(pathString);

        // Convert the path String to a Path
        // For more info on java.nio.file.Path, read here: http://tutorials.jenkov.com/java-nio/path.html
        Path path = Paths.get(pathString);
//        System.out.println("PATH: " + path);
        // Try to set the value of jsonString to a JSON String parsed from the file
        try {
            // Parse the contents of the file into a byte array,
            // and create a new String from that byte[] to set the value of jsonString
            jsonString = new String(Files.readAllBytes(path));
//            System.out.println(jsonString);
            // If the operation fails, an IOException will be thrown and we will catch it here
            // For more info on error handling in Java, read here:
            // https://www.geeksforgeeks.org/exceptions-in-java/
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Finally, return the JSON as a String
        return jsonString;
    }

    // Return a Vehicle array after processing orders from a JSON string
    public static Vehicle[] manufacture(String json) {
        // Initialize these variables because we'll have to give them
        // values inside a try/catch block
        JsonArray orderArray = null;
        JsonObject orderObject = null;

        // Get a JsonArray or a JsonObject depending on whether the JSON
        // string contains an array or just a single object
        try {
            orderArray = JsonParser.parseString(json).getAsJsonArray();

            // If we didn't catch this Exception here, it would stop our program,
            // but if we catch it here we can take an action that allows us to
            // keep moving forward in our program
        } catch (IllegalStateException e) {
            orderObject = JsonParser.parseString(json).getAsJsonObject();
        }

        // If we have a JsonArray, process the list of orders
        if (orderArray != null) {
            return processOrders(orderArray);
            // If we have a JsonObject, then we just have one Vehicle to build
        } else if (orderObject != null) {
            // We put the resulting Vehicle into an array before returning it because
            // the `manufacture` method returns an array
            return new Vehicle[]{buildVehicle(orderObject)};
            // If orderArray and orderObject are both null,
            // then we don't have any orders to process
        } else {
            System.out.println("No orders found.");
            return null;
        }
    }

    // Build a Vehicle
    private static Vehicle buildVehicle(JsonObject order) {
        // Get the `type` key from the JSON object and get its value as a String
        String type = order.get("type").getAsString();

        // Capitalize the first letter of the type string so we can match it
        // to the appropriate class name for our constructor
        String capitalizedType = type.substring(0, 1).toUpperCase() + type.substring(1);

        // Prepend the package name where our Vehicle classes are located so that our
        // JVM knows where to find it
        String className = "techtonic.academy.cardealership.vehicles." + capitalizedType;

        // Declare a Class with unknown type before the try/catch block
        Class<?> c;

        // Initialize our Vehicle before the try/catch block
        Vehicle vehicle = null;

        try {
            // Get the Class with the same name as our className string
            c = Class.forName(className);

            if (capitalizedType.equals("Car")) {
                int randomMPG = (int) (Math.random() * (90 - 30) + 1) + 90;

                // Get the Constructor of that Class
                Constructor constructor = c.getConstructor(JsonObject.class, int.class);
                vehicle = (Vehicle) constructor.newInstance(order, randomMPG);
            } else if (capitalizedType.equals("Motorcycle")) {
                int randomMPG = (int) (Math.random() * (60 - 30) + 1) + 60;

                // Get the Constructor of that Class
                Constructor constructor = c.getConstructor(JsonObject.class, int.class);
                vehicle = (Vehicle) constructor.newInstance(order, randomMPG);
            } else if (capitalizedType.equals("Truck")) {
                int randomMPG = (int) (Math.random() * (40 - 25) + 1) + 25;
                int randomTC = (int) (Math.random() * (15000 - 5000) + 1) + 5000;

                // Get the Constructor of that Class
                Constructor constructor = c.getConstructor(JsonObject.class, int.class, int.class);
                vehicle = (Vehicle) constructor.newInstance(order, randomMPG, randomTC);
            } else if (capitalizedType.equals("Ev")) {
                int randomRange = (int) (Math.random() * (400 - 250) + 1) + 250;

                // Get the Constructor of that Class
                Constructor constructor = c.getConstructor(JsonObject.class, int.class);
                vehicle = (Vehicle) constructor.newInstance(order, randomRange);
            }

            // Print the stack trace to the console if any Exceptions are caught
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        // Return the Vehicle
        return vehicle;
    }

    // Process a list of orders
    private static Vehicle[] processOrders(JsonArray orderArray) {
        // Create a new ArrayList to put our Vehicles into as we build them
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        // Iterate through the array of orders, getting each individual order as a JsonObject
        // Pass each order to the buildVehicle method to get a Vehicle
        // Add each Vehicle to the array we created
        for (int i = 0; i < orderArray.size(); i++) {
            JsonObject order = orderArray.get(i).getAsJsonObject();
            Vehicle vehicle = buildVehicle(order);
            vehicles.add(vehicle);
        }

        // Finally, return the array of Vehicles
        return vehicles.toArray(Vehicle[]::new);
    }

    // Remove car from readyToShip list
    public static void findAndRemoveCar(Vehicle vehicle, int index) {

        if (readyToShip == null || index < 0 || index >= readyToShip.length) {
            // Do nothing, readyToShip stays the same.
        } else {
            // Create another array of one less
            Vehicle[] anotherArray = new Vehicle[readyToShip.length - 1];

            // Copy the elements except the index from original array to the other array
            for (int i = 0, k = 0; i < readyToShip.length; i++) {
                if (i != index) { // if the index is not the removal index passed in
                    anotherArray[k++] = readyToShip[i];
                } else { // if the index is the removal index passed in
                    continue;
                }
            }

            // set the new array with this element removed.
            readyToShip = anotherArray;
        }

    }

    // Display the factory inventory
    public static void displayInventory() {

        for (int i = 0; i < readyToShip.length; i++) {
            String type = readyToShip[i].getType();
            switch (type) {
                case "car":
                    Car theCar = (Car) readyToShip[i];
                    System.out.println(Utils.printHzLine(50));
                    System.out.println("Vehicle " + (i+1) + " - Car: " + theCar.getDescription());
                    break;
                case "truck":
                    Truck theTruck = (Truck) readyToShip[i];
                    System.out.println(Utils.printHzLine(50));
                    System.out.println("Vehicle " + (i+1) + " - Truck: " + theTruck.getDescription());
                    break;
                case "motorcycle":
                    Motorcycle theMotorcycle = (Motorcycle) readyToShip[i];
                    System.out.println(Utils.printHzLine(50));
                    System.out.println("Vehicle " + (i+1) + " - Motorcycle: " + theMotorcycle.getDescription());
                    break;
                case "ev":
                    Ev theEv = (Ev) readyToShip[i];
                    System.out.println(Utils.printHzLine(50));
                    System.out.println("Vehicle " + (i+1) + " - EV: " + theEv.getDescription());
                    break;
            }
        }
        System.out.println();
    }

}
