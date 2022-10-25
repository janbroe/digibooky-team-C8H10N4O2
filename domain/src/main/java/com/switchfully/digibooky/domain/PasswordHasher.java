package com.switchfully.digibooky.domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHasher {

    private final String passwordToHash;
    private String hashedPassword;

    public PasswordHasher(String passwordToHash) {
        this.passwordToHash = passwordToHash;
        this.hashedPassword = hashPassword(passwordToHash);
    }

    public String hashPassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(passwordToHash.getBytes());
            byte[] passwordInBytes = messageDigest.digest();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i< passwordInBytes.length; i++){
                stringBuilder.append(Integer.toString((passwordInBytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            hashedPassword = stringBuilder.toString();
        } catch (NoSuchAlgorithmException exception) {
            exception.printStackTrace();
        }

        return hashedPassword;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }
}
