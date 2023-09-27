package Communication_Simulation.src.question;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BillTest {

    /* TOTAL 2 methods of this class Tested*/


    private static Operator operator;

    private static Bill bill;

    @BeforeEach
    void setUp() {
        operator = new Operator(20, 2.5, 5, 1.5, 1);
        bill = new Bill(500);
    }

    /*
     * [I] Testing pay() method
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
        assertEquals("Amount must be > 0", exception.getMessage());
    }

    /*
     * [II] Testing changeTheLimit() method
     *
     * Possible Test Cases:
     * 1. Testing changeTheLimit method where newLimit is higher than debt
     * 2. Testing changeTheLimit method Negative Amount
     * 3. Testing changeTheLimit method where newLimit is lower than debt
     * 4. Testing changeTheLimit method where newLimit is equal to debt
     * */

    @Test
    public void testChangeTheLimitValidAmount() {
        Bill bill = new Bill(100.0); // Instantiate Bill with an initial limiting amount
        double newLimit = 150.0; // Set a new valid limiting amount
        bill.changeTheLimit(newLimit);
        assertEquals(newLimit, bill.getLimitingAmount(), 0.001);
    }

    /*
    * In that test case, we entered negative amount, but it should not be accepted
    * as amount can't be negative. So we should throw error message to show that
    * amount can't be negative.
    * */
    @Test
    public void testChangeTheLimitNegativeAmount() {
        Bill bill = new Bill(100.0);
        double negativeLimit = -50.0; // Attempting to set a negative limiting amount

        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                   bill.changeTheLimit(negativeLimit);
                }
        );
        assertEquals("Amount can't be negative", exception.getMessage());
    }

    @Test
    public void testChangeTheLimitLowerThanCurrentDebt() {
        Bill bill = new Bill(100.0);
        double newLimit = 50.0; // Attempting to set a limit lower than current debt
        bill.add(60.0); // Adding debt to make current debt higher than new limit
        bill.changeTheLimit(newLimit);
        assertEquals(100.0, bill.getLimitingAmount(), 0.001); // Limit should remain unchanged
    }

    @Test
    public void testChangeTheLimitEqualToCurrentDebt() {
        Bill bill = new Bill(100.0);
        double newLimit = 60.0; // Attempting to set a limit equal to current debt

        bill.add(60.0); // Adding debt to make current debt equal to new limit
        bill.changeTheLimit(newLimit);

        assertEquals(newLimit, bill.getLimitingAmount(), 0.001); // Limit should be updated
    }

}