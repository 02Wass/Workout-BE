package com.workout.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {

  /*
    Inspiration:
    https://www.geeksforgeeks.org/how-to-validate-a-password-using-regular-expressions-in-java/
    */

  //Contains one 0-9, one lowercase a-z, one uppercase A-Z and one @#$%^&+=
  /*  Contains:
    - 0-9 value
    - a-z value
    - A-Z value
    - @#$%^&+= value
    - between 8 and 20 characters
     */
  private static final String REGEX =
    "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";

  private static final Pattern p = Pattern.compile(REGEX);

  public static boolean validatePassword(char[] password) {
    String passwordStr = new String(password);
    Matcher matcher = p.matcher(passwordStr);

    return matcher.matches();
  }
}
