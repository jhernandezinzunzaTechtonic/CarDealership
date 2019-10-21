package techtonic.academy.cardealership.vehicles;
import com.google.gson.JsonObject;

public class Car extends Vehicle {

    private int mpg; // The gas mileage of the Car
    int randomFC = (int) (Math.random() * (16 - 14) + 1) + 14;

    public Car(JsonObject car, int mpg) {
        super(car);

        super.wheels = 4;
        super.fuelCapacity = randomFC;
        if(mpg <= 0){
            throw new IllegalArgumentException("MPG must be greater than zero");
        } else {
            this.mpg = mpg;
        }
    }

    public int getMpg() {
        return mpg;
    }

}
