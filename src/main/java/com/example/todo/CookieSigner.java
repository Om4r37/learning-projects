package com.example.todo;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class CookieSigner {
    private static final String SECRET_KEY = "123";
    private static final String HMAC_ALGO = "HmacSHA256";

    public static String sign(String value) {
        try {
            Mac mac = Mac.getInstance(HMAC_ALGO);
            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), HMAC_ALGO);
            mac.init(secretKeySpec);
            byte[] hmac = mac.doFinal(value.getBytes());
            return Base64.getUrlEncoder().withoutPadding().encodeToString(hmac);
        } catch (Exception e) {
            throw new RuntimeException("Error signing cookie", e);
        }
    }

    public static boolean verify(String value, String signature) {
        String expectedSig = sign(value);
        return expectedSig.equals(signature);
    }

    public static String createIdCookie(HttpServletResponse response, String userId) {
        String signature = sign(userId);
        String signedValue = userId + ":" + signature;

        Cookie userCookie = new Cookie("id", signedValue);
        userCookie.setHttpOnly(true);
        userCookie.setSecure(true);
        response.addCookie(userCookie);
        return "redirect:/tasks";
    }
}