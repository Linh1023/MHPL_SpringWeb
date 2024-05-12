package Springweb.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PasswordResetTokenManager {
    private static final Map<String, Long> tokenStore = new HashMap<>();

    public static String generateToken(String email) throws NoSuchAlgorithmException {
        String token = generateRandomToken();
        long expirationTime = System.currentTimeMillis() + 3600000;
//        String hashedToken = hashToken(token);
        tokenStore.put(token, expirationTime);
        return token;
    }
    //kiểm tra mã người dùng nhập
    public static boolean isTokenValid(String token) {
        Long expirationTime = tokenStore.get(token);
        return expirationTime != null && expirationTime > System.currentTimeMillis();
    }
    //tạo mã ngẫu nhiên
    private static String generateRandomToken() {
       Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int randomNumber = random.nextInt(10);
            stringBuilder.append(randomNumber);
        }
//        return stringBuilder.toString();
        return "1111";
    }

    private static String hashToken(String token) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = digest.digest(token.getBytes());
        return Base64.getEncoder().encodeToString(hashedBytes);
    }
}
