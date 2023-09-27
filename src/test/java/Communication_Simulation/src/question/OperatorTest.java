package Communication_Simulation.src.question;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class OperatorTest {

    /*
    * Testing calculateTalkingCost() method
    *
    * Possible Test Cases:
    * 1. Testing Talking Cost without discount
    * 2. Testing Talking Cost with discount when age > 65
    * 3. Testing Talking Cost with discount when age < 18
    * 4. Testing Talking Cost with discount when age > 18 and age < 65
    * */

    private static Customer customer;
    private static Operator operator;

    @BeforeAll
    static void setUp() {
        operator = new Operator(20, 2.5, 5, 1.5, 1);
    }

    @Test
    public void testCalculateTalkingCostWithoutDiscount() {
        customer = new Customer(25, "Rushed", 30, operator, 1000);
        double talkingCost = operator.calculateTalkingCost(10, customer); // Assuming 10 minutes of talking
        assertEquals(10.0 * operator.getTalkingCharge(), talkingCost, 0.001); // Adjust delta as needed
    }

    @Test
    public void testCalculateTalkingCostWithUnder18Discount() {
        customer = new Customer(15, "Hashem", 15, operator, 1000);
        double talkingCost = operator.calculateTalkingCost(10, customer); // Assuming 10 minutes of talking
        double talkingCostWithDiscount = 10.0 * operator.getTalkingCharge() * (double)(100 - operator.getDiscountRate());
        assertEquals(talkingCostWithDiscount / 100.0, talkingCost, 0.001); // Adjust delta as needed
    }

    @Test
    public void testCalculateTalkingCostWithOver65Discount() {
        customer = new Customer(23, "Wasim", 75, operator, 1000);
        double talkingCost = operator.calculateTalkingCost(10, customer); // Assuming 10 minutes of talking
        double talkingCostWithDiscount = 10.0 * operator.getTalkingCharge() * (double)(100 - operator.getDiscountRate());
        assertEquals(talkingCostWithDiscount / 100.0, talkingCost, 0.001); // Adjust delta as needed
    }

    @Test
    public void testCalculateTalkingCostWithBothDiscounts() {
        customer = new Customer(33, "Abrar", 45, operator, 1000);
        double talkingCost = operator.calculateTalkingCost(10, customer); // Assuming 10 minutes of talking
        assertNotEquals(10.0 * operator.getTalkingCharge() * (double)(100 - operator.getDiscountRate()) / 100.0, talkingCost, 0.001); // Adjust delta as needed
    }

    /*
     * Testing calculateMessageCost() method
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
}