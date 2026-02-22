import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Luokka pelien kirjoittamiseen ja lukemiseen tekstitiedostoon --> Pelihistoria
 */
public class IOHandler {

    private ArrayList<Game> games;
    private final String SEPARATOR = ",";
    private final String PLAYER_SEPARATOR = ":";
    private final String WINNER_SEPARATOR = ";";

    private final String FILENAME = "games.txt"; // TIEDOSTON NIMI

    public IOHandler() {
        games = new ArrayList<>();
    }

    /**
     * Lisää pelin IOHandler olion listaan, kaikki listan pelit tallennetaan
     * 
     * @param Game peli joka halutaan myöhemmin tallentaa
     */
    public void pushToArray(Game game) {
        if (games.size() < 20) {
            games.add(game);
        } else {
            games.remove(0);
            games.add(game);
        }
    }

    /**
     * Parsii kaikki listan pelit ja kirjoittaa ne tiedostoon.
     */
    public void pushToFile() {
        try (PrintWriter printWriter = new PrintWriter(FILENAME)) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Game game : games) {
                stringBuilder.append(game.getPlayer1().getName() + SEPARATOR);
                for (int playerThrow : game.getPlayer1().getPlayerThrows()) {
                    stringBuilder.append(playerThrow + SEPARATOR);
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                stringBuilder.append(PLAYER_SEPARATOR);
                stringBuilder.append(game.getPlayer2().getName() + SEPARATOR);
                for (int playerThrow : game.getPlayer2().getPlayerThrows()) {
                    stringBuilder.append(playerThrow + SEPARATOR);
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                stringBuilder.append(WINNER_SEPARATOR + game.getWinner().getName() + "\n");
            }
            printWriter.write(stringBuilder.toString());
        } catch (Exception e) {

        }
    }

    /**
     * Lukee pelit tidostosta ja lisää ne listaan.
     */
    public void fetch() {
        File file = new File(FILENAME);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String lineData = scanner.nextLine();
                String[] gameData = lineData.split(WINNER_SEPARATOR);
                Player winner = new Player(gameData[1]);

                String[] playerData = gameData[0].split(PLAYER_SEPARATOR);

                String[] dataPoints1 = playerData[0].split(SEPARATOR);
                String[] dataPoints2 = playerData[1].split(SEPARATOR);

                Player player1 = new Player(dataPoints1[0]);
                for (int i = 1; i < dataPoints1.length; i++) {
                    player1.addThrow(Integer.valueOf(dataPoints1[i]));
                }
                Player player2 = new Player(dataPoints2[0]);
                for (int i = 1; i < dataPoints2.length; i++) {
                    player2.addThrow(Integer.valueOf(dataPoints2[i]));
                }
                Game game = new Game(player1, player2);
                game.setWinner(winner);
                games.add(game);
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Virhe tiedoston löytämisessä!");
        }
    }

    /**
     * Poistaa pelihistorian
     */
    public void deleteHistory() {
        games.clear();
        try (PrintWriter pw = new PrintWriter(FILENAME)) {
            pw.write("");
        } catch (Exception e) {

        }
    }

    /**
     * Palauttaa pelit listamn
     * 
     * @return games
     */
    public ArrayList<Game> getGames() {
        return games;
    }
}
