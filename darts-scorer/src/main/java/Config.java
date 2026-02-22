import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Luokka joka lukee setting.cfg tiedoston ja asettaa oletusasetuksia peliin
 */
public class Config {
    private static int gameSize = 501;
    private static boolean autoSave = true;
    private static boolean debugAverages = false;
    private static boolean singleOut = false;

    /**
     * Lukee tiedoston
     */
    public static void readConfigFile() {
        File file = new File("settings.cfg");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" = ");

                if (parts[0].equals("Aloituspisteet")) {
                    try {
                        gameSize = Integer.valueOf(parts[1]);
                    } catch (Exception e) {

                    }
                }

                if (parts[0].equals("AutoSave")) {
                    if (parts[1].equals("true")) {
                        autoSave = true;
                    } else if (parts[1].equals("false")) {
                        autoSave = false;
                    }
                }

                if (parts[0].equals("DebugAverages")) {
                    if (parts[1].equals("true")) {
                        debugAverages = true;
                    } else if (parts[1].equals("false")) {
                        debugAverages = false;
                    }
                }

                if (parts[0].equals("SingleOut")) {
                    if (parts[1].equals("true")) {
                        singleOut = true;
                    } else if (parts[1].equals("false")) {
                        singleOut = false;
                    }
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Virhe: " + fnfe);
        }
    }

    /**
     * Palauttaa aloituspisteiden määrän
     * 
     * @return Aloituspisteet
     */
    public static int getGameSize() {
        return gameSize;
    }

    /**
     * Palaluttaa true/false onko automaattinen tallennus käytössä
     * 
     * @return true/false
     */
    public static boolean isAutoSave() {
        return autoSave;
    }

    /**
     * Palauttaa true/false onko keskiarvojen debuggaus käytössä
     * 
     * @return true/false
     */
    public static boolean isDebugAverages() {
        return debugAverages;
    }

    public static boolean getSingleOut() {
        return singleOut;
    }
}
