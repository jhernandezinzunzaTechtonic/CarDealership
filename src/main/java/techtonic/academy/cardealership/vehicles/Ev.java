package techtonic.academy.cardealership.vehicles;
import com.google.gson.JsonObject;

public class Ev extends Vehicle {

    private int range; // The range (in miles) of the EV

    public Ev(JsonObject ev, int range) {
        super(ev);

        super.wheels = 4;
        super.fuelCapacity = 100;
        this.range = range;
    }

    public int getRange() {
        return range;
    }
}
