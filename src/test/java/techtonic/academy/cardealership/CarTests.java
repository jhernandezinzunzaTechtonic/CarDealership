package techtonic.academy.cardealership;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import techtonic.academy.cardealership.vehicles.Car;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileNotFoundException;
import java.io.FileReader;


@DisplayName("Car Tests")
public class CarTests {
    private JsonParser parser = new JsonParser();
    private JsonObject jsonCar = null;

    void generateJsonCar() {
        try {
            jsonCar = (JsonObject) parser.parse(new FileReader("/Users/josehernandez-inunza/Projects/Java-Class/cardealership/target/classes/oneVehicle.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        "oneVehicle.json"
//        {
//            "type": "car",
//                "vin": "WAUBVAFB4BN838575",
//                "make": "Acura",
//                "model": "TL",
//                "color": "Turquoise",
//                "year": 2000,
//                "wheels": 0,
//                "mileage": 77622,
//                "price": 73791.16,
//                "description": "hey this car rules",
//                "costToDealership": 0,
//                "clean": false,
//                "maintenanceAndInsurance": {
//            "lastServiced": "2018-04-04T08:43:30Z",
//                    "lastInsured": "2018-06-07T12:35:52Z"
//        },
//            "fuel": 15
//        }
    }

//    @BeforeAll
//    void createCar() {
//        generateJsonCar();
//    }

    @Test
    @DisplayName("Testing Car class constructor with JsonObject input")
    void testGoodCarConstructor() {
        generateJsonCar();
        Car testCar = new Car(jsonCar, 100);

        assertEquals(4, testCar.getWheels());
        assertEquals(100, testCar.getMpg());
//        testCar.
    }

    @Test()
    @DisplayName("IllegalArgumentException test || (Passing in mpg as -99)")
    void testIllegalArgumentConstructorNegative() {
        generateJsonCar();

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Car testCar1 = new Car(jsonCar, -99);
        });
        assertEquals("MPG must be greater than zero", exception.getMessage());
    }

    @Test
    @DisplayName("IllegalArgumentException test || (Passing in mpg as 0)")
    void testIllegalArgumentConstructorZero() {
        generateJsonCar();

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Car testCar1 = new Car(jsonCar, 0);
        });
        assertEquals("MPG must be greater than zero", exception.getMessage());
    }

}
