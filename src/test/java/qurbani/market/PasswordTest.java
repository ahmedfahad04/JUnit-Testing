package qurbani.market;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {

    static Password password;

    /*
    * setPassword() Method testing
    */

    @DisplayName("Test Entered password and Confirm password are the same")
    @Test
    public void testSetPasswordCorrectness() {
        // Create an instance of your class that contains the setPassword method
        InputHandler mockInputHandler = new MockInputHandler("test", "test", "Mango");
        password = new Password(mockInputHandler);

        // Call the setPassword method
        password.setPassword();

        // Check that the password is correctly set
        assertEquals("test", password.getPassword());
    }

    @DisplayName("Test if the maxAttempt actually works or not")
    @Test
    public void testWrongPasswordAttemptWorkingOrNot() {
        // Create an instance of your class that contains the setPassword method
        InputHandler mockInputHandler = new MockInputHandler("test", "test2", "test", "test22", "test", "test", "Mango");
        password = new Password(mockInputHandler);

        // Call the setPassword method
        password.setPassword();

        // Check that the password is correctly set
        assertEquals("test", password.getPassword());
    }

    @DisplayName("Test when the maxAttempt expire, the password resetting option disappears and redirect to Home page")
    @Test
    public void testMaxAttemptExpiry() {
        // Create an instance of your class that contains the setPassword method
        InputHandler mockInputHandler = new MockInputHandler("test", "test2", "test", "test22", "test", "test44");
        password = new Password(mockInputHandler);

        // Call the setPassword method
        password.setPassword();

        // Check that the password is correctly set
        assertEquals("test", password.getPassword());
        assertNull(password.getPassword());
    }

}