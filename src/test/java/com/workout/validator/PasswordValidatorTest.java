package com.workout.validator;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PasswordValidatorTest {

    @Test
    public void testValidPassword() {
        char[] validPassword = "Valid1@password".toCharArray();
        assertTrue(PasswordValidator.validatePassword(validPassword));
    }

    @Test
    public void testPasswordWithoutNumber() {
        char[] password = "Valid@password".toCharArray();
        assertFalse(PasswordValidator.validatePassword(password));
    }

    @Test
    public void testPasswordWithoutLowercase() {
        char[] password = "VALID1@PASSWORD".toCharArray();
        assertFalse(PasswordValidator.validatePassword(password));
    }

    @Test
    public void testPasswordWithoutUppercase() {
        char[] password = "valid1@password".toCharArray();
        assertFalse(PasswordValidator.validatePassword(password));
    }

    @Test
    public void testPasswordWithoutSpecialCharacter() {
        char[] password = "Valid1password".toCharArray();
        assertFalse(PasswordValidator.validatePassword(password));
    }

    @Test
    public void testPasswordWithSpaces() {
        char[] password = "Valid1 @password".toCharArray();
        assertFalse(PasswordValidator.validatePassword(password));
    }

    @Test
    public void testPasswordTooShort() {
        char[] password = "V@1a".toCharArray();
        assertFalse(PasswordValidator.validatePassword(password));
    }

    @Test
    public void testPasswordTooLong() {
        char[] password = "Valid1@passwordThatIsWayTooLong".toCharArray();
        assertFalse(PasswordValidator.validatePassword(password));
    }
}
