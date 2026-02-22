import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Luokka luo varmistusikkunan kun käyttäjä poistaa pelihistoriaa
 */

public class Sure extends JFrame implements ActionListener {

    JButton yes;
    IOHandler ioHandler;

    public Sure(IOHandler ioHandler) {
        this.ioHandler = ioHandler;

        this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        /**
         * JLabelin asetukset
         */

        JLabel ru_sure = new JLabel("Are you sure you want to DELETE HISTORY?");
        ru_sure.setPreferredSize(new Dimension(430, 80));
        ru_sure.setBackground(Color.white);
        ru_sure.setHorizontalAlignment(JLabel.CENTER);
        ru_sure.setFont(new Font("Arial", Font.PLAIN, 15));
        this.add(ru_sure);

        /**
         * kyllä- ja ei-napit
         */
        JButton yes = new JButton("YES");
        yes.setPreferredSize(new Dimension(140, 40));
        yes.addActionListener(this);
        this.add(yes);

        JButton no = new JButton("NO");
        no.setPreferredSize(new Dimension(140, 40));
        no.addActionListener(this);
        this.add(no);

        /**
         * ikkunan asetukset
         */

        this.setTitle("Darts Scorer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(450, 200);
        this.setVisible(true);

    }

    /**
     * mitä tapahtuu kun nappia painetaan
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton painettu = (JButton) e.getSource();
        if (painettu.getText().equals("YES")) {
            ioHandler.deleteHistory();
            CloseFrame();
        }

        if (painettu.getText().equals("NO")) {
            CloseFrame();
        }
    }

    /**
     * metodi sulkee ikkunan
     */
    public void CloseFrame() {
        super.dispose();
    }
}
