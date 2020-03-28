package Utility;

import com.github.javafaker.Faker;

public class UserUtility {

    // ---------- Singleton ----------- //
    private static UserUtility singleInstance;

    public static UserUtility getInstance(){
        if(singleInstance == null){
            singleInstance = new UserUtility();
        }
        return singleInstance;
    }
    // ---------- Singleton ----------- //

    // ---------- Faker --------------- //
    private Faker faker;

    private boolean isFakerStarted = false;

    public void startANewFaker() {

        if (!isFakerStarted) {
            // create a new instance of the Faker library
            Faker faker = new Faker();

            this.faker = faker;
            isFakerStarted = true;
        }
    }

    public Faker getFaker() {
        return faker;
    }

    // ---------- Faker --------------- //


    public String generateRandomEmail() {
        String email = faker.name().firstName() + "@gmail.com";
        return email;
    }

    public String generateRandomName() {
        String firstName = faker.name().firstName();
        return firstName;
    }

    public String generateRandomCPF() {
        int digit1 = (int) (Math.random() * 9);
        int digit2 = (int) (Math.random() * 9);
        int digit3 = (int) (Math.random() * 9);
        int digit4 = (int) (Math.random() * 9);
        int digit5 = (int) (Math.random() * 9);
        int digit6 = (int) (Math.random() * 9);
        int digit7 = (int) (Math.random() * 9);
        int digit8 = (int) (Math.random() * 9);
        int digit9 = (int) (Math.random() * 9);

        int validationDigit1 = digit9 * 2 + digit8 * 3 + digit7 * 4 + digit6 * 5 + digit5 * 6 + digit4 * 7 + digit3 * 8 + digit2 * 9 + digit1 * 10;
        int modValidationDigit1 = validationDigit1 % 11;
        if (modValidationDigit1 < 2) {
            validationDigit1 = 0;
        }
        else {
            validationDigit1 = 11 - modValidationDigit1;
        }

        int validationDigit2 = validationDigit1 * 2 + digit9 * 3 + digit8 * 4 + digit7 * 5 + digit6 * 6 + digit5 * 7 + digit4 * 8 + digit3 * 9 + digit2 * 10 + digit1 * 11;
        int modValidationDigit2 = validationDigit2 % 11;
        if (modValidationDigit2 < 2) {
            validationDigit2 = 0;
        }
        else {
            validationDigit2 = 11 - modValidationDigit2;
        }

        String cpf = "" + digit1 + digit2 + digit3 + digit4 + digit5 + digit6 + digit7 + digit8 + digit9 + validationDigit1 + validationDigit2;
        return cpf;

    }
}
