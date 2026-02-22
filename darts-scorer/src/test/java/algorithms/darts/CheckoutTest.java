package algorithms.darts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class CheckoutTest {

    @Test
    void isCheckoutPossible() {
        assertEquals(true, Checkout.isCheckoutPossible(40));
    }

    @Test
    void getCheckout() {
        assertEquals("D20",Checkout.getCheckout(40));
    }

    @Test
    void isDoubleableWithOne() {
        assertEquals(true, Checkout.isDoubleableWithOne(40));
    }

    @Test
    void isDoubleableWithTwo() {
        assertEquals(true, Checkout.isDoubleableWithTwo(80));
    }

    @Test
    void isDoubleableWithThree() {
        assertEquals(true, Checkout.isDoubleableWithThree(120));
    }

    @Test
    void testToString() {
        assertEquals("T20 + D10", Checkout.toString(80));
    }
}