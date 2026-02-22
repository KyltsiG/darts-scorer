import java.util.ArrayList;

/**
 * Pelaaja luokka
 */
public class Player {
    private String name;
    private ArrayList<Integer> playerThrows;
    private int pointsLeft;
    private double average;

    /**
     * Konstruktori pelaajalle
     * 
     * @param String pelaajan nimi
     */
    public Player(String name) {
        this.name = name;
        playerThrows = new ArrayList<>();
        pointsLeft = Config.getGameSize();
    }

    public Player() {
        pointsLeft = Config.getGameSize();
    }

    /**
     * Lisää pelaajalle heiton
     * 
     * @param score int
     */
    public void addThrow(int score) {
        playerThrows.add(score);
        deductPoints(score);
    }

    /**
     * Poistaa pelaajan viimmeisimmän heiton
     */
    public void removeLastThrow() {
        if (playerThrows.size() > 0) {
            addPoints(playerThrows.get(playerThrows.size() - 1));
            playerThrows.remove(playerThrows.size() - 1);
        }
    }

    /**
     * Laskee pelaajan heittojen keskiarvon
     * 
     * @return heittojen keskiarvo double
     */
    public double getAverage() {
        double x = 0.00;
        for (int i = 0; i < playerThrows.size(); i++) {
            x += playerThrows.get(i);
        }
        average = x / playerThrows.size() * 3;
        return average;
    }

    /**
     * Palauttaa listan pelaajan heitoista
     * 
     * @return ArrayList<Integer> Pelaajan heitot
     */
    public ArrayList<Integer> getPlayerThrows() {
        return playerThrows;
    }

    /**
     * Palauttaa pelaajan nimen
     * 
     * @return pelaajan nimi String
     */
    public String getName() {
        return name;
    }

    /**
     * Asettaa pelaajan nimen
     * 
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Palauttaa kuinka paljon pelaajalla on pisteitä jäljellä
     * 
     * @return pelaajan pisteet int
     */
    public int getPointsLeft() {
        return pointsLeft;
    }

    /**
     * Asettaa pelaajalle pisteet
     * 
     * @param pointsLeft int
     */
    public void setPointsLeft(int pointsLeft) {
        this.pointsLeft = pointsLeft;
    }

    /**
     * Vähentää pelaajalta argumentin verran pisteitä
     * 
     * @param amount int
     */
    public void deductPoints(int amount) {
        pointsLeft -= amount;
    }

    /**
     * Lisää pelaajalle argumentin verran pisteitä
     * 
     * @param amount int
     */
    public void addPoints(int amount) {
        pointsLeft += amount;
    }
}
