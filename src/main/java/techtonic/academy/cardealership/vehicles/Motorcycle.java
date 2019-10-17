package techtonic.academy.cardealership.vehicles;
import com.google.gson.JsonObject;

public class Motorcycle extends Vehicle {

    private int mpg; // The gas mileage of the Motorcycle
    int randomFC = (int) (Math.random() * (10 - 6) + 1) + 6;

    public Motorcycle(JsonObject bike, int mpg) {
        super(bike);

        super.wheels = 2;
        super.fuelCapacity = randomFC;
        this.mpg = mpg;
    }

    public int getMpg() {
        return mpg;
    }
}
