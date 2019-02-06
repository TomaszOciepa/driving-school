package authentication;

import javax.ejb.Stateless;
import java.util.Random;

@Stateless
public class GenerateRandomPassword {

    public String generatePassword() {
        char[] alphabetChar = new char[]{'A', 'a', 'B', 'b', 'C', 'c', 'D', 'd', 'E', 'e', 'F', 'f', 'G', 'g', 'H', 'h', 'I', 'i', 'J', 'j', 'K', 'k', 'L', 'l', 'M', 'm', 'O', 'o', 'P', 'p', 'R', 'r', 'S', 's', 'T', 't', 'U', 'u', 'W', 'w', 'Y', 'y', 'Z', 'z'};
        char[] specialChar = new char[]{'!', '@', '$', '*', '#', '&'};
        char[] digitsChar = new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

        Random random = new Random();

        char[] passwordGeneratedTab = new char[6];

        for (int i = 0; i < 6; i++) {
            if (i == 2) {
                passwordGeneratedTab[i] = specialChar[random.nextInt(6)];
            } else if (i == 4) {
                passwordGeneratedTab[i] = digitsChar[random.nextInt(10)];
            } else {
                passwordGeneratedTab[i] = alphabetChar[random.nextInt(44)];
            }

        }

        String myPassword = new String(passwordGeneratedTab);

        return myPassword;
    }
}
