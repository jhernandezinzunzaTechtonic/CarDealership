package techtonic.academy.cardealership.vehicles;
import com.google.gson.JsonObject;

public class Truck extends Vehicle {

    private int mpg; // The gas mileage of the Truck int towingCapacity - the towing capacity (in pounds) of the Truck
    private int towingCapacity;
    int randomFC = (int) (Math.random() * (18 - 15) + 1) + 15;

    public Truck(JsonObject truck, int mpg, int towingCapacity) {
        super(truck);

        super.wheels = 4;
        super.fuelCapacity = randomFC;
        this.mpg = mpg;
        this.towingCapacity = towingCapacity;
    }

    public int getMpg() {
        return mpg;
    }

    public int getTowingCapacity() {
        return towingCapacity;
    }
}
