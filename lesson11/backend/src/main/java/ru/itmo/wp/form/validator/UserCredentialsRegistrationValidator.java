package ru.itmo.wp.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.form.UserCredentials;
import ru.itmo.wp.form.UserCredentialsRegistration;
import ru.itmo.wp.service.UserService;

@Component
public class UserCredentialsRegistrationValidator implements Validator {
    private final UserService userService;

    public UserCredentialsRegistrationValidator(UserService userService) {
        this.userService = userService;
    }

    public boolean supports(Class<?> clazz) {
        return UserCredentialsRegistration.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            UserCredentialsRegistration registerForm = (UserCredentialsRegistration) target;
            if (userService.findByLogin(registerForm.getLogin()) != null) {
                errors.reject("invalid-login", "Login is already in use");
            }
        }
    }
}
