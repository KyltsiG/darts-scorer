import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Menuikkuna, jossa voidaan valita uusi peli, pelihistoriaa ja pelaajien nimet
 */

public class Menuikkuna extends JFrame implements ActionListener {

    JTextField nimi1;
    JTextField nimi2;
    JButton start;
    JButton gamehistory;
    JButton wipe;
    JButton quit;
    ArrayList<JButton> buttons = new ArrayList<>();

    private IOHandler ioHandler;

    /**
     * luodaan uusi menuikkuna
     */
    public Menuikkuna() {

        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 45));

        /**
         * luodaan tekstikentät ja vihjetekstit
         */
        nimi1 = new JTextField();
        nimi1.setPreferredSize(new Dimension(180, 70));
        nimi1.setBackground(Color.white);
        nimi1.setCaretColor(Color.BLACK);
        nimi1.setFont(new Font("Arial", Font.PLAIN, 20));
        nimi1.setToolTipText("Syötä Pelaajan 1 nimi.");

        nimi2 = new JTextField();
        nimi2.setPreferredSize(new Dimension(180, 70));
        nimi2.setBackground(Color.white);
        nimi2.setCaretColor(Color.BLACK);
        nimi2.setFont(new Font("Arial", Font.PLAIN, 20));
        nimi2.setToolTipText("Syötä Pelaajan 2 nimi.");

        JLabel nimi1_teksti = new JLabel("Pelaaja 1:");
        nimi1_teksti.setPreferredSize(new Dimension(100, 100));
        nimi1_teksti.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));

        JLabel nimi2_teksti = new JLabel("Pelaaja 2:");
        nimi2_teksti.setPreferredSize(new Dimension(100, 100));
        nimi2_teksti.setFont(new Font("Arial", Font.CENTER_BASELINE, 20));

        this.add(nimi1_teksti);
        this.add(nimi1);
        this.add(nimi2_teksti);
        this.add(nimi2);

        /**
         * Lisätään napit
         */
        Dimension buttonsize = new Dimension(150, 50);
        JButton start = new JButton("Start");
        start.setPreferredSize(new Dimension(100, 50));
        start.addActionListener(this);
        this.add(start);
        JButton gamehistory = new JButton("Game History");
        buttons.add(gamehistory);
        JButton wipe = new JButton("Delete History");
        buttons.add(wipe);
        JButton save = new JButton("Save");
        buttons.add(save);        
        JButton quit = new JButton("Quit");
        buttons.add(quit);
        
        for (JButton button : buttons) {
            button.setPreferredSize(buttonsize);
            button.addActionListener(this);
            this.add(button);
        }

        /**
         * ikkunan asetukset
         */
        this.setTitle("Darts Scorer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(750, 350);
        this.setVisible(true);

        ioHandler = new IOHandler();
        ioHandler.fetch();
    }

    /**
     * napin painalluksen funktiot
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton painettu = (JButton) e.getSource();

        if (painettu.getText().equals("Quit")) {
            CloseFrame();
        }

        if (painettu.getText().equals("Save")) {
            ioHandler.pushToFile();
        }

        if (painettu.getText().equals("Delete History")) {
            new Sure(ioHandler);
        }

        if (painettu.getText().equals("Start")) {
            Config.readConfigFile();
            Player player1 = new Player(nimi1.getText());
            Player player2 = new Player(nimi2.getText());
            Game game = new Game(player1, player2);
            new GUI(game, ioHandler);
        }

        if (painettu.getText().equals("Game History")) {
            new GameHistory(ioHandler);
        }
    }

    /**
     * metodi sulkee ikkunan
     */
    public void CloseFrame() {
        if (Config.isAutoSave()) {
            ioHandler.pushToFile();
        }
        super.dispose();
    }

}