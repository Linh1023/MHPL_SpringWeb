package Springweb.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PasswordResetTokenManager {
    public String generateToken() throws NoSuchAlgorithmException {
        String token = generateRandomToken();
        return token;
    }
    private String generateRandomToken() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int randomNumber = random.nextInt(10);
            stringBuilder.append(randomNumber);
        }
        return stringBuilder.toString();
    }
}
