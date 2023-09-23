package org.Mobile_Banking_System_Phase_2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private Account user;

    @BeforeEach
    public void setUp() {
        user = new Account("Afsar Ali", "01766610087", "12345");
    }

    // we might have to do some additional test like (empty phone number and pin, null phone and pin)
    // it depends on the question asked by instructor.
    @DisplayName("Valid Phone Number But same New Pin")
    @Test
    public void testValidPhoneNumberButSameNewPin() {
        String phoneNum = "01766610087";
        String newPin = "12345";

        user.set_new_pin(phoneNum, newPin);

        // check valid phone number
        assertEquals(phoneNum, user.getPhoneNum());

        // check same New pin
        assertEquals(newPin, user.getPin());
    }

    @DisplayName("Test invalid Phone Number")
    @Test
    public void testInvalidPhoneNumber() {
        String phoneNum = "01766610088";
        String newPin = "12345";

        user.set_new_pin(phoneNum, newPin);

        // check invalid phone number
        assertNotEquals(phoneNum, user.getPhoneNum());
    }

    @DisplayName("Test Both valid Phone Number and Pin")
    @Test
    public void testValidPhoneNumberAndPin() {
        String phoneNum = "01766610087";
        String newPin = "4587";

        user.set_new_pin(phoneNum, newPin);

        // check valid phone number and pin
        assertEquals(phoneNum, user.getPhoneNum());
        assertEquals(newPin, user.getPin());
    }



}