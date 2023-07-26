package com.secretengine.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class PasswordStrength {
	private static final String PASSWORD_STRENGTH_PATTERN ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
	public String checkPasswordStrength(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_STRENGTH_PATTERN);
        Matcher matcher = pattern.matcher(password);

        if (!matcher.matches()) {
            StringBuilder errorMessage = new StringBuilder("Password does not meet the strength requirements. ");
            if (!password.matches(".*[A-Z].*")) {
                errorMessage.append("Capital letter is missing. ");
            }
            if (!password.matches(".*[a-z].*")) {
                errorMessage.append("Lowercase letter is missing. ");
            }
            if (!password.matches(".*[0-9].*")) {
                errorMessage.append("Digit is missing. ");
            }
            if (!password.matches(".*[@#$%^&+=].*")) {
                errorMessage.append("Special character is missing. ");
            }
            if (password.length() < 8) {
                errorMessage.append("Password should be at least 8 characters long. ");
            }
            return errorMessage.toString();
        }
        return null;
    }
}
