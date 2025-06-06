package pl.edu.agh.kis.pz1.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class TextUtils {
    private TextUtils() {}

    public static String sha512Hash(String str) {
        return DigestUtils.sha512Hex(str);
    }

    public static String getMd5Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return null;
    }
    public static String generateRandomHash() {
        return UUID.randomUUID().toString();
    }

    public static String generateHash(String str) {
        return DigestUtils.sha512Hex(str);
    }



}
