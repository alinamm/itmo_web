package ru.itmo.wp.form;

import ru.itmo.wp.domain.User;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PostForm {

    @NotEmpty
    @Size(min = 1, max = 100)
    private String title;


    @NotEmpty
    @Size(min = 1, max = 10000)
    @Lob
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
