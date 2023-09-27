package Communication_Simulation.src.question;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BillTest {

    private static Customer customer;
    private static Operator operator;

    private static Bill bill;

    @BeforeAll
    static void setUp() {
        operator = new Operator(20, 2.5, 5, 1.5, 1);
        customer = new Customer(23, "Wasim", 75, operator, 1000);
        bill = new Bill(500);
    }

    /*
     * [V] Testing pay() method
     *
     * Possible Test Cases:
     * 1. Testing pay method with Partial Payment Amount
     * 2. Testing pay method with Full Payment Amount
     * 3. Testing pay method with 0 Payment Amount
     * 4. Testing pay method with Negative Payment Amount
     * */

    @Test
    public void testPayPartialAmount() {
        double initialDebt = 100.0;
        double payment = 50.0;

        bill.add(initialDebt);
        bill.pay(payment);

        assertEquals(initialDebt - payment, bill.getCurrentDebt(), 0.001); // Assuming you have a getCurrentDebt method
        assertEquals(payment, bill.getTotalMoneySpent(), 0.001); // Assuming you have a getTotalMoneySpent method
    }

    @Test
    public void testPayMoreThanDebtAmount() {
        double initialDebt = 100.0;
        double payment = 150.0; // Attempting to pay more than the debt

        bill.add(initialDebt);
        bill.pay(payment);

        assertEquals(0.0, bill.getCurrentDebt(), 0.001);
        assertEquals(initialDebt, bill.getTotalMoneySpent(), 0.001);
    }

    @Test
    public void testPayZeroAmount() {
        double initialDebt = 100.0;
        double payment = 0.0;

        bill.add(initialDebt);
        bill.pay(payment);

        assertEquals(initialDebt, bill.getCurrentDebt(), 0.001); // Debt should remain unchanged
        assertEquals(0.0, bill.getTotalMoneySpent(), 0.001); // No money spent
    }

    /*
    * In Actual Code, they didn't perform any checking for negative Amount
    * So, this test helped us to detect this validation criteria for this method
    * */
    @Test
    public void testPayNegativeAmount() {
        double initialDebt = 100.0;
        double payment = -10.0;

        bill.add(initialDebt);

        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    bill.pay(payment);
                }
        );
        assertEquals("Amount must be Greater than 0", exception.getMessage());
    }

}