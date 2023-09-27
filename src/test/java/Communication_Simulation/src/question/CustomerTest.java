package Communication_Simulation.src.question;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import Communication_Simulation.src.question.Customer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerTest {

    /* TOTAL 4 methods of this class Tested*/

    private static Customer customer1;
    private static Customer customer2;
    private static Operator operator;

    @BeforeEach
    void setUp() {
        operator = new Operator(20, 2.5, 5, 1.5, 1);
        Bill bill = new Bill(100.0); // Create a mock Bill (replace with actual implementation)

        customer1 = new Customer(1, "John Doe", 30, operator, 100.0);
        customer1.setBill(bill);

        customer2 = new Customer(2, "Kary Mane", 25, operator, 100.0);
        customer2.setBill(bill);
    }

    /*
     * [I] Testing talk() method
     *
     * Possible Test Cases:
     * 1. Testing talk method with valid duration
     * 2. Testing talk method with invalid (negative) duration
     * 3. Testing talk method when customer calls himself
     * */

    @Test
    public void testTalkValidCall() {
        double initialBalance = customer1.getBill().getLimitingAmount();
        int minute = 10; // 10 minutes of talking

        customer1.talk(minute, customer2);
        customer1.getBill().pay(initialBalance);

        assertEquals(operator.calculateTalkingCost(minute, customer1), customer1.getBill().getTotalMoneySpent());
        assertEquals(minute, customer1.getTotalSpentTalkingTime());
        assertEquals(minute, customer2.getTotalSpentTalkingTime());
    }

    @Test
    public void testTalkNegativeDuration() {
        double initialBalance = customer1.getBill().getLimitingAmount();

        int negativeMinute = -10; // Negative duration, invalid
        customer1.talk(negativeMinute, customer2);
        customer1.getBill().pay(initialBalance);

        assertEquals(operator.calculateTalkingCost(negativeMinute, customer1), customer1.getBill().getTotalMoneySpent());
        assertEquals(0, customer1.getTotalSpentTalkingTime()); // No time should be recorded
        assertEquals(0, customer2.getTotalSpentTalkingTime()); // No time should be recorded
    }

    @Test
    public void testTalkWithSelf() {
        customer1.talk(10, customer1); // Attempting to talk with self
        assertEquals(0, customer1.getTotalSpentTalkingTime()); // No time should be recorded
    }

    /*
     * [II] Testing message() method
     *
     * Possible Test Cases:
     * 1. Testing message method with valid quantity
     * 2. Testing message method with invalid (negative) quantity
     * 3. Testing message method when customer messages himself
     * */

    @Test
    public void testMessageValidQuantity() {
        double initialBalance = customer1.getBill().getLimitingAmount();
        int quantity = 10; // Assuming 10 messages to be sent

        customer1.message(quantity, customer2);
        customer1.getBill().pay(initialBalance);

        assertEquals(operator.calculateMessageCost(quantity, customer1, customer2), customer1.getBill().getTotalMoneySpent(), 0.001);
        assertEquals(quantity, customer1.getTotalSentMessages());
    }

    @Test
    public void testMessageNegativeQuantity() {
        double initialBalance = customer1.getBill().getLimitingAmount();
        int negativeQuantity = -10; // Negative quantity, invalid

        customer1.message(negativeQuantity, customer2);

        assertEquals(initialBalance, customer1.getBill().getLimitingAmount(), 0.001); // Balance should remain unchanged
        assertEquals(0, customer1.getTotalSentMessages()); // No messages should be recorded
        assertEquals(0, customer2.getTotalSentMessages()); // No messages should be recorded
    }

    @Test
    public void testMessageWithSelf() {
        double initialBalance = customer1.getBill().getLimitingAmount();
        customer1.message(10, customer1); // Attempting to message self

        assertEquals(initialBalance, customer1.getBill().getLimitingAmount(), 0.001); // Balance should remain unchanged
        assertEquals(0, customer1.getTotalSentMessages()); // No messages should be recorded
    }

    /*
     * [III] Testing connection() method
     *
     * Possible Test Cases:
     * 1. Testing connection method with valid data amount
     * 2. Testing connection method with invalid (negative) amount
     * */

    @Test
    public void testConnectionValidAmount() {
        double amount = 50.0; // Assuming 50 MB of data connection
        customer1.connection(amount);
        assertEquals(amount, customer1.getTotalInternetUsage(), 0.001);
    }

    @Test
    public void testConnectionNegativeAmount() {
        double initialBalance = customer1.getBill().getLimitingAmount();
        double negativeAmount = -50.0; // Negative amount, invalid

        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    customer1.connection(negativeAmount);
                }
        );
        assertEquals("Number of data must be non-negative.", exception.getMessage());
        assertEquals(initialBalance, customer1.getBill().getLimitingAmount(), 0.001); // Balance should remain unchanged
        assertEquals(0.0, customer1.getTotalInternetUsage(), 0.001); // No internet usage should be recorded
        assertEquals(0.0, customer1.getOperator().getTotalInternetUsage(), 0.001); // No internet usage should be recorded
    }

    /*
     * [IV] Testing setAge() method
     *
     * Possible Test Cases:
     * 1. Testing connection method with valid data amount
     * 2. Testing connection method with invalid (negative) amount
     * */

    @Test
    public void testSetAgeValid() {
        int validAge = 25; // Set a valid age
        customer1.setAge(validAge);
        assertEquals(validAge, customer1.getAge());
    }

    @Test
    public void testSetAgeNegativeValue() {
        int invalidAge = -10; // Set an invalid negative age
        int initialAge = customer1.getAge();
        customer1.setAge(invalidAge);
        assertEquals(initialAge, customer1.getAge()); // Age should remain unchanged
    }


}
