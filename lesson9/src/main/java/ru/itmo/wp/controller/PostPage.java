package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.service.PostService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class PostPage extends Page {
    private final PostService postService;

    public PostPage(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post/{id}")
    public String post(@PathVariable String id, Model model) {
        if (id.matches("[0-9]+")) {
            Post post = postService.findById(Long.parseLong(id));
            model.addAttribute("post", post);
        } else {
            model.addAttribute("post", null);
        }
        model.addAttribute("newComment", new Comment());

        return "PostPage";
    }

    @PostMapping("/post/{id}")
    public String publish(@PathVariable String id, @Valid @ModelAttribute("newComment") Comment comment, BindingResult bindingResult, HttpSession httpSession,Model model) {
        Post post = new Post();
        if (id.matches("[0-9]+")) {
            post = postService.findById(Long.parseLong(id));;
        } else {
            post = null;
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("post", post);
            return "PostPage";
        }

        if (post == null) {
            return "PostPage";
        }

        postService.publish(getUser(httpSession), comment, id);
        setMessage(httpSession, "Success");

        return "redirect:/post/" + id;
    }
}
