/**
 * Peli luokka, joka pitää sisällään pelaajat
 */

public class Game {
    private Player player1;
    private Player player2;
    private Player winner;

    /**
     * Konstruktori pelille, kun pelaajat tiedossa
     * 
     * @param player1
     * @param player2
     */
    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    /**
     * Konstruktori pelille, kun pelaajat eivät vielä tiedossa
     */
    public Game() {

    }

    /**
     * Palauttaa ensimmäisen pelaajan
     * 
     * @return Player 1
     */
    public Player getPlayer1() {
        return player1;
    }

    /**
     * Palauttaa toisen pelaajan
     * 
     * @return Player 2
     */
    public Player getPlayer2() {
        return player2;
    }

    /**
     * Asettaa ensimmäisen pelaajan
     * 
     * @param player1
     */
    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    /**
     * Asettaa toisen pelaajan
     * 
     * @param player2
     */
    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    /**
     * Asettaa pelin voittajaksi pelaajan
     * 
     * @param player
     */
    public void setWinner(Player player) {
        winner = player;
    }

    /**
     * Palauttaa pelin voittajan
     * 
     * @return Player
     */
    public Player getWinner() {
        return winner;
    }
}
