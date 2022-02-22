package ru.itmo.wp.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.exception.ValidationException;
import ru.itmo.wp.form.UserCredentialsRegistration;
import ru.itmo.wp.form.validator.UserCredentialsRegistrationValidator;
import ru.itmo.wp.service.JwtService;
import ru.itmo.wp.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/1")
public class UserController {
    private final JwtService jwtService;
    private final UserService userService;
    private final UserCredentialsRegistrationValidator userCredentialsRegistrationValidator;

    public UserController(JwtService jwtService, UserService userService, UserCredentialsRegistrationValidator userCredentialsRegistrationValidator) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.userCredentialsRegistrationValidator = userCredentialsRegistrationValidator;
    }

    @GetMapping("users/auth")
    public User findUserByJwt(@RequestParam String jwt) {
        return jwtService.find(jwt);
    }

    @GetMapping("users")
    public List<User> findUsers() {
        return userService.findAll();
    }

    @InitBinder("userCredentialsRegistration")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCredentialsRegistrationValidator);
    }


    @PostMapping("users/registration")
    public String registerUser(@Valid @RequestBody UserCredentialsRegistration userCredentialsRegistration, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
       return jwtService.create(userService.register(userCredentialsRegistration));

    }
}
