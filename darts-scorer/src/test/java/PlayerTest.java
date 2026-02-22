import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @org.junit.jupiter.api.Test
    void addThrow() {
        Player pelaaja = new Player("pelaaja");
        pelaaja.addThrow(1);
        assertEquals(1, pelaaja.getPlayerThrows().get(0));
    }

    @org.junit.jupiter.api.Test
    void removeLastThrow() {
        Player pelaaja = new Player("pelaaja");
        pelaaja.addThrow(1);
        pelaaja.addThrow(2);
        pelaaja.removeLastThrow();
        assertEquals(1, pelaaja.getPlayerThrows().size());
    }

    @org.junit.jupiter.api.Test
    void getAverage() {
        Player pelaaja = new Player("pelaaja");
        pelaaja.addThrow(1);
        pelaaja.addThrow(2);
        pelaaja.addThrow(3);
        assertEquals(6, pelaaja.getAverage());
    }

    @org.junit.jupiter.api.Test
    void getPlayerThrows() {
        Player pelaaja = new Player("pelaaja");
        pelaaja.addThrow(1);
        pelaaja.addThrow(2);
        pelaaja.addThrow(3);
        ArrayList<Integer> lista = new ArrayList<>();
        lista.add(1);
        lista.add(2);
        lista.add(3);
        assertEquals(lista, pelaaja.getPlayerThrows());
    }

    @org.junit.jupiter.api.Test
    void getName() {
        Player pelaaja = new Player("pelaaja");
        assertEquals("pelaaja", pelaaja.getName());
    }

    @org.junit.jupiter.api.Test
    void setName() {
        Player pelaaja = new Player("lepääjä");
        pelaaja.setName("pelaaja");
        assertEquals("pelaaja", pelaaja.getName());
    }

    @org.junit.jupiter.api.Test
    void getPointsLeft() {
        Player pelaaja = new Player("pelaaja");
        assertEquals(Config.getGameSize(), pelaaja.getPointsLeft());
    }

    @org.junit.jupiter.api.Test
    void setPointsLeft() {
        Player pelaaja = new Player("pelaaja");
        pelaaja.setPointsLeft(200);
        assertEquals(200, pelaaja.getPointsLeft());
    }

    @org.junit.jupiter.api.Test
    void deductPoints() {
        Player pelaaja = new Player("pelaaja");
        pelaaja.setPointsLeft(200);
        pelaaja.deductPoints(10);
        assertEquals(190, pelaaja.getPointsLeft());
    }

    @org.junit.jupiter.api.Test
    void addPoints() {
        Player pelaaja = new Player("pelaaja");
        pelaaja.setPointsLeft(200);
        pelaaja.addPoints(10);
        assertEquals(210, pelaaja.getPointsLeft());
    }
}