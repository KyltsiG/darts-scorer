/**
 * Luokka vuorojen käsittelyyn
 */
public class TurnHandler {
    private Game game;
    private Player player;
    private int playerScoreBeforeTurn;

    private final int[] TURNROTATION = { 1, 1, 1, 2, 2, 2 };
    private int turnRotationIndex = 0;
    private int turnIndex = 0;
    private CheckoutHandler checkoutHandler;

    /**
     * Konstruktori luokalle, luo samalla checkout handlerin oliolle
     * 
     * @param game
     */
    public TurnHandler(Game game) {
        this.game = game;
        this.checkoutHandler = new CheckoutHandler(this);
    }

    /**
     * Tekee tarvittavat toimenpiteet vuorolla
     * 
     * @param button_score int
     */
    public void handleThrow(int button_score, int wasDouble, GUI gui) {
        if (button_score != -1) {
            player = getPlayerTurn();
            if (checkoutHandler.playerThrowsLeft(player) == 3) {
                playerScoreBeforeTurn = player.getPointsLeft();
            }
            if (player.getPointsLeft() - button_score > 1 || player.getPointsLeft() - button_score == 0) {
                if ((player.getPointsLeft() - button_score == 0 && wasDouble == 1 && !Config.getSingleOut())
                        || (player.getPointsLeft() - button_score == 0 && Config.getSingleOut())) {
                    player.addThrow(button_score);
                    game.setWinner(player);
                    gui.CloseFrame(game);
                } else if (player.getPointsLeft() - button_score == 0 && wasDouble != 1) {
                    player.setPointsLeft(playerScoreBeforeTurn);
                    if (checkoutHandler.playerThrowsLeft(player) > 1) {
                        for (int i = 0; i < checkoutHandler.playerThrowsLeft(player); i++) {
                            nextPlayerInTurn();
                        }
                    }
                } else {
                    player.addThrow(button_score);
                }
            } else {
                player.setPointsLeft(playerScoreBeforeTurn);
                if (checkoutHandler.playerThrowsLeft(player) > 1) {
                    for (int i = 0; i < checkoutHandler.playerThrowsLeft(player); i++) {
                        nextPlayerInTurn();
                    }
                }
            }
            nextPlayerInTurn();
        } else {
            goBackTurn();
        }
    }

    /**
     * Palauttaa pelaajan, joka on vuorossa
     * 
     * @return Player
     */
    public Player getPlayerTurn() {
        if (TURNROTATION[turnRotationIndex] == 1) {
            return game.getPlayer1();
        } else {
            return game.getPlayer2();
        }
    }

    /**
     * Seuraava vuoro
     */
    public void nextPlayerInTurn() {
        if (!(turnRotationIndex + 1 > TURNROTATION.length - 1)) {
            turnRotationIndex++;
            turnIndex++;
        } else {
            turnRotationIndex = 0;
            turnIndex++;
        }
    }

    /**
     * Edellinen vuoro
     */
    public void goBackTurn() {
        if (turnRotationIndex != 0 && turnIndex > 0) {
            turnRotationIndex--;
            turnIndex--;
            player = getPlayerTurn();
            player.removeLastThrow();
        } else if (turnRotationIndex == 0 && turnIndex > 0) {
            turnRotationIndex = TURNROTATION.length - 1;
            turnIndex--;
            player = getPlayerTurn();
            player.removeLastThrow();
        }
    }

    /**
     * Palauttaa pelin
     * 
     * @return Game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Palauttaa vuororotaation CheckoutHandleria varten
     * 
     * @return TURNROTATION
     */
    public int[] getTurnRotation() {
        return TURNROTATION;
    }

    /**
     * Palauttaa vuororotaation indeksin CheckoutHandleria varten
     * 
     * @return turnRoationIndex
     */
    public int getTurnRotationIndex() {
        return turnRotationIndex;
    }
}
