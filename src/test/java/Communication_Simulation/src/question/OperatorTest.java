package Communication_Simulation.src.question;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperatorTest {

    /* TOTAL 6 methods of this class Tested*/
    
    private static Customer customer;
    private static Operator operator;

    @BeforeAll
    static void setUp() {
        operator = new Operator(20, 2.5, 5, 1.5, 1);
        customer = new Customer(33, "Akib", 25, operator, 1000);
    }

    /*
     * [I] Testing calculateTalkingCost() method
     *
     * Possible Test Cases:
     * 1. Testing Talking Cost without discount
     * 2. Testing Talking Cost with discount when age > 65
     * 3. Testing Talking Cost with discount when age < 18
     * 4. Testing Talking Cost with discount when age > 18 and age < 65
     * */

    @Test
    public void testCalculateTalkingCostWithoutDiscount() {
        customer = new Customer(25, "Rushed", 30, operator, 1000);
        double talkingCost = operator.calculateTalkingCost(10, customer);
        assertEquals(10.0 * operator.getTalkingCharge(), talkingCost, 0.001); // Adjust delta as needed
    }

    @Test
    public void testCalculateTalkingCostWithUnder18Discount() {
        customer = new Customer(15, "Hashem", 15, operator, 1000);
        double talkingCost = operator.calculateTalkingCost(10, customer);
        double talkingCostWithDiscount = 10.0 * operator.getTalkingCharge() * (double)(100 - operator.getDiscountRate());
        assertEquals(talkingCostWithDiscount / 100.0, talkingCost, 0.001); // Adjust delta as needed
    }

    @Test
    public void testCalculateTalkingCostWithOver65Discount() {
        customer = new Customer(23, "Wasim", 75, operator, 1000);
        double talkingCost = operator.calculateTalkingCost(10, customer);
        double talkingCostWithDiscount = 10.0 * operator.getTalkingCharge() * (double)(100 - operator.getDiscountRate());
        assertEquals(talkingCostWithDiscount / 100.0, talkingCost, 0.001); // Adjust delta as needed
    }

    @Test
    public void testCalculateTalkingCostWithBothDiscounts() {
        customer = new Customer(33, "Abrar", 45, operator, 1000);
        double talkingCost = operator.calculateTalkingCost(10, customer);
        double talkingCostWithDiscount = 10.0 * operator.getTalkingCharge() * (double)(100 - operator.getDiscountRate()) / 100.0;
        assertNotEquals(talkingCostWithDiscount, talkingCost, 0.001); // Adjust delta as needed
    }

    /*
     * [II] Testing calculateMessageCost() method
     *
     * Possible Test Cases:
     * 1. Testing Messaging Cost without discount
     * 2. Testing Messaging Cost with discount when age > 65
     * */

    @Test
    public void testCalculateMessageCostWithoutDiscount() {
        Operator operatorA = new Operator(25, 3.5, 0.1, 1.5, 3);
        Operator operatorB = new Operator(22, 4, 0.1, 2.5, 2);
        Customer customerA = new Customer(28, "Akram", 35, operatorA, 1500);
        Customer customerB = new Customer(38, "Anwar", 42, operatorB, 2080);

        double messageCost = 0.1;

        double cost = operatorA.calculateMessageCost(10, customerA, customerB);
        assertEquals(10.0 * messageCost, cost, 0.001);
    }

    @Test
    public void testCalculateMessageCostWithDiscount() {
        Operator operatorA = new Operator(25, 3.5, 0.1, 1.5, 3);
        Operator operatorB = new Operator(25, 4, 0.1, 2.5, 2);
        Customer customerA = new Customer(28, "Akram", 35, operatorA, 1500);
        Customer customerB = new Customer(38, "Anwar", 42, operatorB, 2080);

        double messageCost = 0.1;
        double discountRate = 10;

        double cost = operatorA.calculateMessageCost(10, customerA, customerB);
        double costWithDiscount = 10.0 * messageCost * (100 - discountRate) / 100.0;
        assertEquals(costWithDiscount, cost, 0.1);
    }

    /*
     * [III] Testing setTalkingCharge() method
     *
     * Possible Test Cases:
     * 1. Testing Talking Charge with +ve value
     * 2. Testing Talking Charge when charge = 0
     * 3. Testing Talking Charge with -ve value
     * */

    @Test
    public void testSetTalkingChargePositiveValue() {

        double expectedCharge = 0.5; // Set expected talking charge
        operator.setTalkingCharge(expectedCharge);
        assertEquals(expectedCharge, operator.getTalkingCharge(), 0.001); // Assuming there's a getTalkingCharge method
    }

    @Test
    public void testSetTalkingChargeZeroValue() {
        double expectedCharge = 0.0; // Set expected talking charge
        operator.setTalkingCharge(expectedCharge);
        assertEquals(expectedCharge, operator.getTalkingCharge(), 0.001);
    }

    @Test
    public void testSetTalkingChargeNegativeValue() {
        double invalidCharge = -0.5; // Set invalid negative charge

        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    operator.setTalkingCharge(invalidCharge);
                }
        );

        assertEquals("Talking charge must be non-negative.", exception.getMessage());
    }

    /*
     * [IV] Testing setDiscountRate() method
     *
     * Possible Test Cases:
     * 1. Testing Discount Rate with Valid Input
     * 2. Testing Discount Rate when discount = 0
     * 3. Testing Discount Rate when discount = 100
     * 4. Testing Discount Rate when discount < 0
     * 5. Testing Discount Rate when discount > 100
     * */

    @Test
    public void testSetDiscountRateValidValue() {
        int expectedDiscount = 20;
        operator.setDiscountRate(expectedDiscount);
        assertEquals(expectedDiscount, operator.getDiscountRate());
    }

    @Test
    public void testSetDiscountRateEqualToZero() {
        int expectedDiscount = 0;
        operator.setDiscountRate(expectedDiscount);
        assertEquals(expectedDiscount, operator.getDiscountRate());
    }

    @Test
    public void testSetDiscountRateEqualToHundred() {
        int expectedDiscount = 100;
        operator.setDiscountRate(expectedDiscount);
        assertEquals(expectedDiscount, operator.getDiscountRate());
    }

    @Test
    public void testSetDiscountRateLessThanZero() {
        int invalidDiscount = -10;

        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    operator.setDiscountRate(invalidDiscount);
                }
        );
        assertEquals("Discount rate must be between 0-100.", exception.getMessage());
    }

    @Test
    public void testSetDiscountRateGreaterThanHundred() {
        int invalidDiscount = 200;

        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    operator.setDiscountRate(invalidDiscount);
                }
        );
        assertEquals("Discount rate must be between 0-100.", exception.getMessage());
    }


    /*
     * [V] Testing addInternetUsage() method
     *
     * Possible Test Cases:
     * 1. Testing addInternetUsage for valid amount
     * 2. Testing addInternetUsage Rate when discount = 0
     * 3. Testing Discount Rate when discount = 100
     * 4. Testing Discount Rate when discount < 0
     * 5. Testing Discount Rate when discount > 100
     * */

    @Test
    public void testAddInternetUsageValidAmount() {
        double initialInternetUsage = customer.getTotalInternetUsage();
        double amount = 50.0; // Set a valid amount

        customer.connection(amount); // connect and use internet
        operator.addInternetUsage(amount);

        assertEquals(initialInternetUsage + amount, customer.getTotalInternetUsage(), 0.001);
    }

    @Test
    public void testAddInternetUsageNegativeAmount() {
        double initialInternetUsage = customer.getTotalInternetUsage();
        double negativeAmount = -50.0; // Attempting to add a negative amount

        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    operator.addInternetUsage(negativeAmount);
                    customer.connection(negativeAmount);
                }
        );
        assertEquals("Number of data must be non-negative.", exception.getMessage());
        assertEquals(initialInternetUsage, customer.getTotalInternetUsage(), 0.001); // Internet usage should remain unchanged
    }


}