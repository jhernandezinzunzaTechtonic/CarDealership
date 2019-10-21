package techtonic.academy.cardealership;


public class Utils {

    static String[] mainMenuOptions = {
    "0 - Create a new dealership",
    "1 - See current car lot inventory",
    "2 - See factory inventory",
    "3 - Purchase a vehicle",
    "4 - Sell a vehicle",
    "5 - Test drive a vehicle"
    };

    public static void displayMainMenu(boolean hasDealership) {
        String options = "";

        if(hasDealership == false) {
            for (String option : mainMenuOptions) {
                options += option + "\n";
            }
        } else {
            for (int i = 1; i < mainMenuOptions.length; i++) {
                options += mainMenuOptions[i] + "\n";
            }
        }

        String ANSI_BLUE = "\u001B[34m";
        String ANSI_RESET = "\u001B[0m";
        String menu =
            printHzLine(25) + " Main Menu " + printHzLine(25) + "\n" +
            printHzLine(61) + "\n" +
            ANSI_BLUE +
            "Enter \"exit\" to quit the application\n" +
            "Enter \"stats\" to view your dealerships stats" + "\n" +
            "Enter \"sales\" to view your sales history\n" +
            ANSI_RESET +
            "\n\nDealership Options:\n\n" +
            options;

        System.out.println(menu);
    }

    public static String printHzLine(int length) {
        String line = "";
        for(int n = 0; n < length; n++)  line += "-";

        return line;
    }

    public static void clearSorta() {
        for (int i = 0; i < 20; ++i) System.out.println();
    }

}
