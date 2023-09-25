package qurbani.market;

import java.io.Serializable;


public class Password implements Serializable {

    private InputHandler inputHandler;
    private String password = null;
    private String favouriteWord;

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

    // TODO: To be Tested 7
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
                    Main.main(null);
                    returnToMainProgram = true;
                    this.password = null;
                    break;
                }
            }
        }
        if(returnToMainProgram) {
            System.exit(0);
        }
    }


    public String getPassword() {
        return password;
    }


    // TODO: To be Tested 8
    public void resetPassword() {
        for (int i = 0; true; i++) {
            String fav = Input.inputLine("\nEnter Your Favourite Word : ");
            if (fav.equals(getFavouriteWord())) {
                this.setPassword();
                break;
            } else if (i == 3) {
                Main.main(null);
                System.exit(0);
            } else {
                System.out.println("\nKINDLY ENTER IN CONSCIOUSNESS");
                System.out.printf("You Have %d more Tries Left\n\n", 3 - i);
            }
        }
    }
}
