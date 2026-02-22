import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Luokka luo pelille GUI:n, sekä sisältää
 * jokaisen napin funktiot
 */

public class GUI extends JFrame implements ActionListener {

    private JLabel p1_gui;
    private JLabel p2_gui;
    private JLabel checkout_gui;

    private ArrayList<JButton> numerot = new ArrayList<>();
    private int nappi_score;
    private int converterState = 0; // 0=ei mitään, 1=tupla, 2=tripla
    private int triplaState = 0; // periaatteena parillinen - pariton luku --> saadaan tietää jos painetaan kaksi
                                 // kertaa peräkkäin
    private int tuplaState = 0;

    private Player player1;
    private Player player2;

    private TurnHandler turnHandler;
    private CheckoutHandler checkoutHandler;
    private IOHandler ioHandler;

    /**
     * Luo uuden ikkunan ja napit
     */
    public GUI(Game game, IOHandler ioHandler) {

        player1 = game.getPlayer1();
        player2 = game.getPlayer2();
        turnHandler = new TurnHandler(game);
        checkoutHandler = new CheckoutHandler(turnHandler);
        this.ioHandler = ioHandler;

        // pelaajaien tulos-labelit
        p1_gui = new JLabel(player1.getName() + ": " + String.valueOf(player1.getPointsLeft()));
        p1_gui.setBackground(Color.lightGray);
        p1_gui.setOpaque(true);
        this.add(p1_gui);

        p2_gui = new JLabel(player2.getName() + ": " + String.valueOf(player2.getPointsLeft()));
        p2_gui.setBackground(Color.white);
        p2_gui.setOpaque(true);
        this.add(p2_gui);

        // nappien järjestys ja luomis looppi
        int[] jarjestys = { 20, 25, 16, 17, 18, 19, 12, 13, 14, 15, 8, 9, 10, 11, 4, 5, 6, 7, 0, 1, 2, 3, -2, -3, -1 };

        for (int i : jarjestys) {
            JButton numero = new JButton();
            if (i >= 0) {
                numero.setText(String.valueOf(i));
            }
            if (i == -2) {
                numero.setText("Double");
            }
            if (i == -3) {
                numero.setText("Triple");
            }
            if (i == -1) {
                numero.setText("Back");
            }
            numero.setBackground(Color.white);
            numero.setFocusable(false);
            numero.addActionListener(this);
            numerot.add(numero);
            this.add(numero);
        }

        /**
         * checkout-labelin luominen
         */
        checkout_gui = new JLabel(turnHandler.getPlayerTurn().getName() + ": ");
        checkout_gui.setBackground(Color.red);
        checkout_gui.setOpaque(true);
        this.add(checkout_gui);

        /**
         * ikkunan asetukset
         */
        this.setTitle("Darts Scorer");
        this.setLayout(new GridLayout(7, 4, 5, 5));
        this.setVisible(true);
        this.setSize(600, 500);

        updateCheckoutHandler();
    }

    /**
     * mitä napin painamisesta tapahtuu
     * 
     * @param e tapahtuma, joka tunnistetaan napin painamiseksi
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton painettu = (JButton) e.getSource();

        /**
         * numero-nappien toiminta painaessa
         */
        try {
            nappi_score = Integer.parseInt(painettu.getText());

            // eli jos painetaan jotain nappia 0-50
            if (converterState == 0) {
                turnHandler.handleThrow(nappi_score, converterState, this);
            } else if (converterState == 1 && nappi_score != 50) {// kun halutaan tuplata
                turnHandler.handleThrow(nappi_score * 2, converterState, this);
                converterState = 0;
            } else if (converterState == 2 && nappi_score != 50 && nappi_score != 25) { // kun halutaan triplata
                turnHandler.handleThrow(nappi_score * 3, converterState, this);
                converterState = 0;
            }

            /**
             * päivittää pelaajatekstit kun nappia painetaan
             */
            updateScore();
            updatePlayerColor();
            updateCheckoutHandler();
            setDoubleDefault();
            setTripleDefault();

            /**
             * ei-numero-nappien toiminta painalluksella
             */
        } catch (NumberFormatException nfe) {
            if (painettu.getText().equals("Back")) { // Back
                turnHandler.goBackTurn();
                updateScore();
                updatePlayerColor();
                updateCheckoutHandler();
                setDoubleDefault();
                setTripleDefault();

                /**
                 * kun painetaan tuplaus-nappia, napin väri vaihtuu.
                 * Jos tuplastate on pariton, (käyttäjä on painanut valkoista nappia) väri
                 * tummaksi
                 * jos tuplastate parillinen, (käyttäjä on painanut tummaa nappia) väri
                 * valkoiseksi
                 */
            } else if (painettu.getText().equals("Double")) { // Double
                tuplaState++;
                if (tuplaState % 2 != 0) {
                    painettu.setBackground(Color.darkGray);
                    converterState = 1;
                    setTripleDefault();
                } else {
                    painettu.setBackground(Color.white);
                    converterState = 0;
                }
                /**
                 * kun painetaan triplaus-nappia, napin väri vaihtuu.
                 * Jos tuplastate on pariton, (käyttäjä on painanut valkoista nappia) väri
                 * tummaksi
                 * jos tuplastate parillinen, (käyttäjä on painanut tummaa nappia) väri
                 * valkoiseksi
                 */
            } else if (painettu.getText().equals("Triple")) { // Triple
                triplaState++;
                if (triplaState % 2 != 0) {// kun triplaus on "käynnissä" väri vaihtuu
                    painettu.setBackground(Color.darkGray);
                    converterState = 2;
                    setDoubleDefault();
                } else {
                    painettu.setBackground(Color.white);
                    converterState = 0;
                }

            }
        }

    }

    /**
     * hakee päivitetyt tulokset pelaajien tulos-Labeleihin
     */

    public void updateScore() {
        p1_gui.setText(player1.getName() + ": " + String.valueOf(player1.getPointsLeft()));
        p2_gui.setText(player2.getName() + ": " + String.valueOf(player2.getPointsLeft()));
    }

    /**
     * asettaa pelivuorossa olevan pelaajan labelin värin tummaksi ja toisen
     * valkoiseksi
     */
    public void updatePlayerColor() {
        p1_gui.setBackground(Color.white);
        p2_gui.setBackground(Color.white);
        if (turnHandler.getPlayerTurn() == player1) {
            p1_gui.setBackground(Color.lightGray);
        } else {
            p2_gui.setBackground(Color.lightGray);
        }
    }

    /**
     * tarkistaa, onko checkout mahdollista ja muokkaa checkout labelia
     */
    public void updateCheckoutHandler() {
        checkout_gui.setText(turnHandler.getPlayerTurn().getName());
        if (checkoutHandler.getCheckout(turnHandler.getPlayerTurn()) != null) {
            checkout_gui.setText(turnHandler.getPlayerTurn().getName() + ": "
                    + checkoutHandler.getCheckout(turnHandler.getPlayerTurn()));
            checkout_gui.setBackground(Color.green);
        } else {
            checkout_gui.setBackground(Color.red);
            checkout_gui.setText(turnHandler.getPlayerTurn().getName() + ": ");
        }
    }

    /**
     * muuttaa doublen-napin default tilaan (alkuasetelma)
     */
    public void setDoubleDefault() {
        if (tuplaState % 2 != 0) {
            numerot.get(22).setBackground(Color.white);
            tuplaState++;
        }
    }

    /**
     * muuttaa triple-napin default tilaan (alkuasetelma)
     */
    public void setTripleDefault() {
        if (triplaState % 2 != 0) {
            numerot.get(23).setBackground(Color.white);
            triplaState++;
        }
    }

    public void CloseFrame(Game game) {
        super.dispose();
        ioHandler.pushToArray(game);
        if (Config.isAutoSave()) {
            ioHandler.pushToFile();
        }
        if (Config.isDebugAverages()) {
            System.out.println("Pelaajan " + player1.getName() + " keskiarvo:" + player1.getAverage());
            System.out.println("Pelaajan " + player2.getName() + " keskiarvo:" + player2.getAverage());
        }
    }
}