package org.example.validator;

import org.example.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    private final String nameRegex = "^[a-zA-Z\\s]+$";
    private final String eMailRegex = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
    private final Pattern namePattern = Pattern.compile(nameRegex);
    private final Pattern eMailPattern = Pattern.compile(eMailRegex);

    public void validateCreateUser(User user){
        Matcher nameMatcher = namePattern.matcher(user.getName());
        Matcher surnameMatcher = namePattern.matcher(user.getSurname());
        Matcher eMailMatcher = eMailPattern.matcher(user.getEmail());
        if(!nameMatcher.matches() || !surnameMatcher.matches() || !eMailMatcher.matches()){
            throw new IllegalArgumentException();
        }
    }


}
