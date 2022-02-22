package ru.itmo.wp.lesson8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itmo.wp.lesson8.domain.User;
import ru.itmo.wp.lesson8.service.UserService;

import java.util.Date;

@Controller
public class UsersPage extends Page {
    private final UserService userService;

    public UsersPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/all")
    public String users(Model model) {
        model.addAttribute("users", userService.findAll());
        return "UsersPage";
    }

    @GetMapping("/users/{id}")
    public String userInfo(@PathVariable String id, Model model) {
        if (id.matches("[0-9]+")) {
            User user = userService.findById(Long.parseLong(id));
            model.addAttribute("user", user);
        } else {
            model.addAttribute("user", null);
        }
        return "UserPage";
    }

}
