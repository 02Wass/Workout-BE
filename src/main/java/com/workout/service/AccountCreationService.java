package com.workout.service;

import com.workout.controller.SensitiveUser;
import com.workout.validator.PasswordValidator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.time.Instant;
import java.util.UUID;

public class AccountCreationService {

    private static final int MAX_FAILED_ATTEMPTS = 3;
    private static final long TIMEOUT_DURATION = 15 * 60; // 15 minutes in seconds

    private Map<String, Integer> failedAttempts = new HashMap<>();
    private Map<String, Instant> timeoutExpiry = new HashMap<>();

    public boolean createAccount(String ipAddress, String firstName, String lastName, char[] password) {
        if (isIpInTimeout(ipAddress)) {
            System.out.println("IP address is temporarily blocked. Please try again later.");
            return false;
        }

        int retryCount = 0;
        while (retryCount < MAX_FAILED_ATTEMPTS) {
            try {
                if (!PasswordValidator.validatePassword(password)) {
                    throw new IllegalArgumentException("Invalid password. Password must contain 0-9, a-z, A-Z, @#$%^&+=, and be between 8 and 20 characters long.");
                }
                SensitiveUser newUser = new SensitiveUser(UUID.randomUUID(), password);
                resetFailedAttempts(ipAddress);
                // Account Created
                // Implement more here
                return true;
            } catch (IllegalArgumentException e) {
                retryCount++;
                System.out.println(e.getMessage());
                if (retryCount < MAX_FAILED_ATTEMPTS) {
                    password = promptForNewPassword();
                } else {
                    setIpTimeout(ipAddress);
                    System.out.println("Maximum retry attempts reached. Please try again later.");
                }
            }
        }
        return false;
    }

    private char[] promptForNewPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a new password: ");
        return scanner.nextLine().toCharArray();
    }

    private boolean isIpInTimeout(String ipAddress) {
        Instant expiry = timeoutExpiry.get(ipAddress);
        return expiry != null && Instant.now().isBefore(expiry);
    }

    private void registerFailedAttempt(String ipAddress) {
        failedAttempts.put(ipAddress, failedAttempts.getOrDefault(ipAddress, 0) + 1);
    }

    private void setIpTimeout(String ipAddress) {
        timeoutExpiry.put(ipAddress, Instant.now().plusSeconds(TIMEOUT_DURATION));
    }

    private void resetFailedAttempts(String ipAddress) {
        failedAttempts.remove(ipAddress);
        timeoutExpiry.remove(ipAddress);
    }
}
