package algorithms.darts;

/**
 * Apuluokka Checkout-luokkaan
 */

public class ThrowObject {
    private ThrowType throwType;
    private int value;

    /**
     * Konstruktori saa arvokseen enumina heiton tyypin ja arvon
     * 
     * @param throwType
     * @param value
     */
    public ThrowObject(ThrowType throwType, int value) {
        this.throwType = throwType;
        this.value = value;
    }

    /**
     * Palauttaa heiton tyypin
     * 
     * @return
     */
    public ThrowType getThrowType() {
        return throwType;
    }

    /**
     * Palauttaa heiton arvon
     * 
     * @return
     */
    public int getValue() {
        return value;
    }

    /**
     * Palauttaa heiton tyypin lyhenteen ja arvon
     */
    @Override
    public String toString() {
        if (throwType == ThrowType.TRIPLE) {
            return "T" + value;
        } else if (throwType == ThrowType.DOUBLE) {
            return "D" + value;
        } else {
            return "S" + value;
        }
    }
}
