package market;

import java.io.Serializable;


public class Password implements Serializable {
    private String password;
    private String favouriteWord;
    private InputHandler inputHandler;

    public Password() {
        this.setPassword();
    }

    public Password(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    public void setFavouriteWord(String favouriteWord) {
        this.favouriteWord = favouriteWord;
    }

    public String getFavouriteWord() {
        return favouriteWord;
    }

    // TODO: Test it (DONE)
    void setPassword() {
        int maxAttempts = 3;
        boolean returnToMainProgram = false;
        for (int i = 0; i < maxAttempts; i++) {
            String userPass1 = inputHandler.inputLine("Enter Password: ");
            String userPass2 = inputHandler.inputLine("Confirm Password: ");
            if (userPass1.equals(userPass2)) {
                this.password = userPass1;
                System.out.println("\n(It Will Help You In Changing Your Password)");
                setFavouriteWord(inputHandler.inputLine("Enter Your Favourite Word : "));
                break;
            } else {
                System.out.println("\nKINDLY ENTER IN CONSCIOUSNESS");
                int remainingAttempts = maxAttempts - i - 1;
                if (remainingAttempts > 0) {
                    System.out.printf("You Have %d more Tries Left\n\n", remainingAttempts);
                } else {
                    System.out.println("You've reached the maximum number of attempts.");
                    System.exit(0); // this should be placed after Main.main(null)
                    Main.main(null);
                    System.out.println("Calling System Exit...");
                }
            }
        }
    }

    public String getPassword() {
        return password;
    }

    public void resetPassword() {
        int maxAttempts = 3;

        for (int i = 1; i <= maxAttempts; i++) {
            String fav = inputHandler.inputLine("\nEnter Your Favorite Word : ");
            if (fav.equals(getFavouriteWord())) {
                this.setPassword();
                break; // Exit the loop if the favorite word is correct
            } else if (i == maxAttempts) {
                System.exit(0); // this should be placed after Main.main(null)
                Main.main(null);
            } else {
                System.out.println("\nKINDLY ENTER IN CONSCIOUSNESS");
                System.out.printf("You Have %d more Tries Left\n\n", maxAttempts - i);
            }
        }
    }
}
