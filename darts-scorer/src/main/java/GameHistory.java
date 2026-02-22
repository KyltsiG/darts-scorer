import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

/**
 * Luokka mallintaa pelihistoria-ikkunaa
 */

public class GameHistory extends JFrame implements ActionListener {
    private final int WINDOW_HEIGHT = 800;
    private final int WINDOW_WIDTH = 400;

    private ArrayList<Game> games;

    /**
     * Konstruktori saa argumentikseen IOHandler tyyppisen olion
     * 
     * @param ioHandler
     */
    public GameHistory(IOHandler ioHandler) {
        games = ioHandler.getGames();

        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));

        for (int i = 0; i < 20; i++) {
            if (i < games.size()) {
                MyButton button = new MyButton(i);
                button.setText(games.get(i).getPlayer1().getName() + " - " + games.get(i).getPlayer2().getName());
                button.setPreferredSize(new Dimension(WINDOW_WIDTH - 20, WINDOW_HEIGHT / 24));
                button.setFocusable(false);
                button.addActionListener(this);
                this.add(button);

            } else {
                MyButton button = new MyButton(i);
                button.setText("Empty slot");
                button.setPreferredSize(new Dimension(WINDOW_WIDTH - 20, WINDOW_HEIGHT / 24));
                button.setFocusable(false);
                button.addActionListener(this);
                this.add(button);
            }
        }

        this.setTitle("Game History");
        this.setResizable(false);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MyButton pressedMyButton = (MyButton) e.getSource();
        if (!pressedMyButton.getText().equals("Empty slot")) {
            new SingleGameHistory(games.get(pressedMyButton.getIndex()), pressedMyButton.getIndex() + 1);
        }
    }
}
