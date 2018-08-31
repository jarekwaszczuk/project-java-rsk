package pl.r80.rsk.Utilities;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utilities {

    public static String passwordToSha256AndToHex(String passToSha256) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(
                passToSha256.getBytes(StandardCharsets.UTF_8));
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < encodedhash.length; i++) {
            String hex = Integer.toHexString(0xff & encodedhash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static boolean validatePesel(String pesel) {

        int ostatniaCyfra = Integer.parseInt(pesel.substring(10, 11));
        int a = Integer.parseInt(pesel.substring(0, 1));
        int b = Integer.parseInt(pesel.substring(1, 2));
        int c = Integer.parseInt(pesel.substring(2, 3));
        int d = Integer.parseInt(pesel.substring(3, 4));
        int e = Integer.parseInt(pesel.substring(4, 5));
        int f = Integer.parseInt(pesel.substring(5, 6));
        int g = Integer.parseInt(pesel.substring(6, 7));
        int h = Integer.parseInt(pesel.substring(7, 8));
        int i = Integer.parseInt(pesel.substring(8, 9));
        int j = Integer.parseInt(pesel.substring(9, 10));

        int suma = 9 * a + 7 * b + 3 * c + 1 * d + 9 * e + 7 * f + 3 * g + 1 * h + 9 * i + 7 * j;

        if (suma % 10 == ostatniaCyfra) {
            return true;
        } else {
            return false;
        }
    }
}
