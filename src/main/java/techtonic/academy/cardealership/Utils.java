package techtonic.academy.cardealership;


public class Utils {

    public static void displayMainMenu() {
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_RESET = "\u001B[0m";
        String menu =
            printHzLine(25) + " Main Menu " + printHzLine(25) + "\n" +
            printHzLine(61) + "\n" +
            ANSI_BLUE +
            "Enter \"exit\" at any point to quit the application\n" +
            "Enter \"home\" at any point to return this this main menu\n" +
            "Enter \"stats\" at any point to see your dealerships stats" +
            ANSI_RESET +
            "\n\nDealership Options: \n" +
            "0 - Create a new dealership\n" +
            "1 - See current car lot inventory\n" +
            "2 - See factory inventory\n" +
            "3 - Purchase a vehicle\n";

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
