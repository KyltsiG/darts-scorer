import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Luokka mallintaa yhtä peliä pelihistoriassa
 */
public class SingleGameHistory extends JFrame {
    private final int WINDOW_HEIGHT = 200;
    private final int WINDOW_WIDTH = 400;

    /**
     * Konstruktori saa arvokseen pelin ja pelin numeron
     * 
     * @param game
     * @param gameNumber
     */
    public SingleGameHistory(Game game, int gameNumber) {
        this.setLayout(new GridLayout(4, 2));

        JLabel title = new JLabel(game.getPlayer1().getName() + " - " + game.getPlayer2().getName());
        this.add(title);

        JLabel winner = new JLabel("Voittaja: " + game.getWinner().getName());
        this.add(winner);

        JLabel p1 = new JLabel(game.getPlayer1().getName() + ":");
        this.add(p1);

        JLabel p2 = new JLabel(game.getPlayer2().getName() + ":");
        this.add(p2);

        JLabel playerOneAverage = new JLabel("Avg: " + game.getPlayer1().getAverage());
        this.add(playerOneAverage);

        JLabel playerTwoAverage = new JLabel("Avg: " + game.getPlayer2().getAverage());
        this.add(playerTwoAverage);

        JLabel playerOneThrowCount = new JLabel("Heittoja: " + game.getPlayer1().getPlayerThrows().size());
        this.add(playerOneThrowCount);

        JLabel playerTwoThrowCount = new JLabel("Heittoja: " + game.getPlayer2().getPlayerThrows().size());
        this.add(playerTwoThrowCount);

        this.setTitle("Peli: " + gameNumber);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setResizable(false);
        this.setVisible(true);
    }
}
