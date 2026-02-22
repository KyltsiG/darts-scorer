import javax.swing.JButton;

/**
 * Luokka mallintaa JButton luokkaa, lisäyksenä indeksi
 */
public class MyButton extends JButton {
    private int index;

    /**
     * Konstruktori lisää indeksin arvon
     * 
     * @param index
     */
    public MyButton(int index) {
        super();
        this.index = index;
    }

    /**
     * Palauttaa indeksin
     * 
     * @return indeksin arvo
     */
    public int getIndex() {
        return index;
    }
}
