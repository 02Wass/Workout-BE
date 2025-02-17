package com.workout.controller;

import java.util.Arrays;
import java.util.UUID;

public class SensitiveUser {

    private final UUID personalId;
    private char[] password;

    public SensitiveUser(UUID personalId, char[] password) {
        this.personalId = personalId;
        this.password = password.clone();
    }

    public UUID getPersonalId() {
        return personalId;
    }

    private char[] getPassword() {
        return password.clone();
    }

    public void setPassword(char[] newPassword) {
        clearPassword();
        this.password = newPassword.clone();
    }

    private void clearPassword() {
        if (password != null) {
            Arrays.fill(password, '\0');
        }
    }
}
