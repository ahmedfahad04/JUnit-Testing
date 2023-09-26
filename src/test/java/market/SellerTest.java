package market;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.exceptions.base.MockitoException;


import static org.junit.jupiter.api.Assertions.*;

class SellerTest {

    @Mock
    private InputHandler inputHandler;
    static Seller seller;

    @BeforeEach
    public void setUp() {
        seller = new Seller(inputHandler);
    }

    /* Possible test Cases for [isValidContactNumber(String contactNo) -> boolean]
     * 1. Testing if contact number is equal to 12
     * 2. Testing if all the characters are digits
     * 3. Testing if we have - after initial 4 digits
     * */
    @Test
    @DisplayName("Testing contact number is not equal to 12 including hyphen after 4 digits")
    public void testContactNumberNotEqualToTwelveIncludingHyphen() {

        // TODO: WHY ITS GETTING STUCK!!!!!!!!!!!!!!!!!!!!!!!!!
        Mockito.when(inputHandler.inputLine("Enter Contact No : ")).thenReturn("123456");

        seller.setContactNo();

        assertNotNull(seller.getContactNo());
    }


}