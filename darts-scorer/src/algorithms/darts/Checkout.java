package algorithms.darts;

/**
 * Mahdollisten checkouttien laskemiseen käytettävä luokka.
 * HUOM! Tärkeimmät metodit ovat staattisia, eli luokasta ei tarvitse muodostaa
 * oliota!
 */
public class Checkout {
    private static final int[] SINGLES = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 25 };
    private static int[] DOUBLES = { 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 50 };
    private static int[] TRIPLES = { 3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36, 39, 42, 45, 48, 51, 54, 57, 60 };

    public Checkout() {

    }

    /**
     * Palauttaa arvon true/false, riippuen siitä onko argumenttina annettava
     * kokonaisluku mahdollista jakaa dartstaulun tekijöihin niin, että viimmeinen
     * tekijä on tupla.
     * 
     * @param score int
     * @return true/false
     */
    public static boolean isCheckoutPossible(int score) {
        if (isDoubleableWithOne(score)) {
            return true;
        } else if (isDoubleableWithTwo(score)) {
            return true;
        } else if (isDoubleableWithThree(score)) {
            return true;
        }
        return false;
    }

    /**
     * Palauttaa checkoutin String muodossa
     * 
     * @param score int
     * @return checkout String
     */
    public static String getCheckout(int score) {
        return toString(score);
    }

    /**
     * Palauttaa true/false, riippuen siitä onko argumenttina annettu kokonaisluku
     * yksi pelin tuplista
     * 
     * @param score int
     * @return true/false
     */
    public static boolean isDoubleableWithOne(int score) {
        for (int sscore : DOUBLES) {
            if (sscore == score) {
                return true;
            }
        }
        return false;
    }

    /**
     * Palauttaa true/false, riippuen siitä onko argumenttina annettu kokonaisluku
     * mahdollista jakaa kahteen tekijään niin, että ensimmäinen on mikä tahansa
     * pelin tuloksista ja jälkimmäinen jokin tupla
     * 
     * @param score int
     * @return true/false
     */
    public static boolean isDoubleableWithTwo(int score) {
        for (int i = TRIPLES.length - 1; i >= 0; i--) {
            if (isDoubleableWithOne(score - TRIPLES[i])) {
                return true;
            }
        }
        for (int i = DOUBLES.length - 1; i >= 0; i--) {
            if (isDoubleableWithOne(score - DOUBLES[i])) {
                return true;
            }
        }
        for (int i = SINGLES.length - 1; i >= 0; i--) {
            if (isDoubleableWithOne(score - SINGLES[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Palauttaa true/false, riippuen siitä onko argumenttina annettu kokonaisluku
     * mahdollista jakaa kolmeen tekijään niin, että ensimmäinen ja toinen on mikä
     * tahansa pelin tuloksista ja viimmeinen jokin tupla
     * 
     * @param score int
     * @return true/false
     */
    public static boolean isDoubleableWithThree(int score) {
        for (int i = TRIPLES.length - 1; i >= 0; i--) {
            if (isDoubleableWithTwo(score - TRIPLES[i])) {
                return true;
            }
        }
        for (int i = DOUBLES.length - 1; i >= 0; i--) {
            if (isDoubleableWithTwo(score - DOUBLES[i])) {
                return true;
            }
        }
        for (int i = SINGLES.length - 1; i >= 0; i--) {
            if (isDoubleableWithTwo(score - SINGLES[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Palauttaa sen tuplan, mikä täytyy heittää
     * 
     * @param score int
     * @return ThrowObject
     */
    private static ThrowObject getDoubleWithOne(int score) {
        for (int sscore : DOUBLES) {
            if (sscore == score) {
                return new ThrowObject(ThrowType.DOUBLE, sscore / 2);
            }
        }
        return null;
    }

    /**
     * Palauttaa kahden alkion ThrowObject[], joista jälkimmäinen on tupla
     * 
     * @param score int
     * @return ThrowObject[] heitettävät tulokset
     */
    private static ThrowObject[] getDoubleWithTwo(int score) {
        ThrowObject[] throwObjects = new ThrowObject[2];
        for (int i = TRIPLES.length - 1; i >= 0; i--) {
            if (isDoubleableWithOne(score - TRIPLES[i])) {
                throwObjects[0] = new ThrowObject(ThrowType.TRIPLE, TRIPLES[i] / 3);
                throwObjects[1] = getDoubleWithOne(score - TRIPLES[i]);
                return throwObjects;
            }
        }
        for (int i = DOUBLES.length - 1; i >= 0; i--) {
            if (isDoubleableWithOne(score - DOUBLES[i])) {
                throwObjects[0] = new ThrowObject(ThrowType.DOUBLE, DOUBLES[i] / 2);
                throwObjects[1] = getDoubleWithOne(score - DOUBLES[i]);
                return throwObjects;
            }
        }
        for (int i = SINGLES.length - 1; i >= 0; i--) {
            if (isDoubleableWithOne(score - SINGLES[i])) {
                throwObjects[0] = new ThrowObject(ThrowType.SINGLE, SINGLES[i]);
                throwObjects[1] = getDoubleWithOne(score - SINGLES[i]);
                return throwObjects;
            }
        }
        return null;
    }

    /**
     * Palauttaa kolmen alkion ThrowObject[], joista viimmeinen on tupla
     * 
     * @param score int
     * @return ThrowObject[] heitettävät tulokset
     */
    private static ThrowObject[] getDoubleWithThree(int score) {
        ThrowObject[] throwObjects = new ThrowObject[3];
        for (int i = TRIPLES.length - 1; i >= 0; i--) {
            if (isDoubleableWithTwo(score - TRIPLES[i])) {
                throwObjects[0] = new ThrowObject(ThrowType.TRIPLE, TRIPLES[i] / 3);
                throwObjects[1] = getDoubleWithTwo(score - TRIPLES[i])[0];
                throwObjects[2] = getDoubleWithTwo(score - TRIPLES[i])[1];
                return throwObjects;
            }
        }
        for (int i = DOUBLES.length - 1; i >= 0; i--) {
            if (isDoubleableWithTwo(score - DOUBLES[i])) {
                throwObjects[0] = new ThrowObject(ThrowType.DOUBLE, DOUBLES[i] / 2);
                throwObjects[1] = getDoubleWithTwo(score - DOUBLES[i])[0];
                throwObjects[2] = getDoubleWithTwo(score - DOUBLES[i])[1];
                return throwObjects;
            }
        }
        for (int i = SINGLES.length - 1; i >= 0; i--) {
            if (isDoubleableWithTwo(score - SINGLES[i])) {
                throwObjects[0] = new ThrowObject(ThrowType.SINGLE, SINGLES[i]);
                throwObjects[1] = getDoubleWithTwo(score - SINGLES[i])[0];
                throwObjects[2] = getDoubleWithTwo(score - SINGLES[i])[1];
                return throwObjects;
            }
        }
        return null;
    }

    /**
     * Parsii tulokset Stringiksi
     * 
     * @param score int
     * @return String heitettävät tulokset
     */
    public static String toString(int score) {
        if (isCheckoutPossible(score)) {
            if (isDoubleableWithOne(score)) {
                return "D" + getDoubleWithOne(score).getValue();
            } else if (isDoubleableWithTwo(score)) {
                String s = "";
                for (ThrowObject throwObject : getDoubleWithTwo(score)) {
                    s += throwObject.toString() + " + ";
                }
                s = s.substring(0, s.length() - 3);
                return s;
            } else if (isDoubleableWithThree(score)) {
                String s = "";
                for (ThrowObject throwObject : getDoubleWithThree(score)) {
                    s += throwObject.toString() + " + ";
                }
                s = s.substring(0, s.length() - 3);
                return s;
            }
        }
        return "";
    }
}
