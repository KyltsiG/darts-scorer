import algorithms.darts.Checkout;

/**
 * Luokka joka käyttää Checkout luokan laskuja
 */

public class CheckoutHandler {
    private TurnHandler turnHandler;

    /**
     * Vaatii TurnHandler luokan olion
     * 
     * @param turnHandler
     */
    public CheckoutHandler(TurnHandler turnHandler) {
        this.turnHandler = turnHandler;
    }

    /**
     * Käyttää Checkout luokkaa ja palauttaa String muodossa Checkoutin jos
     * mahdollista
     * 
     * @param player
     * @return String checkout tai null
     */
    public String getCheckout(Player player) {
        if (!Checkout.isCheckoutPossible(turnHandler.getPlayerTurn().getPointsLeft())) {
            return null;
        } else {
            if (playerThrowsLeft(player) == 3) {
                return Checkout.getCheckout(turnHandler.getPlayerTurn().getPointsLeft());
            } else if (playerThrowsLeft(player) == 2
                    && Checkout.isDoubleableWithTwo(turnHandler.getPlayerTurn().getPointsLeft())) {
                return Checkout.getCheckout(turnHandler.getPlayerTurn().getPointsLeft());
            } else if (playerThrowsLeft(player) == 1
                    && Checkout.isDoubleableWithOne(turnHandler.getPlayerTurn().getPointsLeft())) {
                return Checkout.getCheckout(turnHandler.getPlayerTurn().getPointsLeft());
            } else {
                return null;
            }
        }

    }

    /**
     * Laskee kuinka monta heittoa pelaajalla on vuoronsa aikana jäljellä
     * 
     * @param player
     * @return int 0-3
     */
    public int playerThrowsLeft(Player player) {
        int left = 0;
        int[] TURNROTATION = turnHandler.getTurnRotation();
        int turnRotationIndex = turnHandler.getTurnRotationIndex();
        int current = TURNROTATION[turnRotationIndex];
        while (current == TURNROTATION[turnRotationIndex]) {
            if (!(turnRotationIndex + 1 > TURNROTATION.length - 1)) {
                turnRotationIndex++;
            } else {
                turnRotationIndex = 0;
            }
            left++;
        }
        return left;
    }
}
