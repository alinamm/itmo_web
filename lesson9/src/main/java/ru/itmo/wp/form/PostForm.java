package ru.itmo.wp.form;

import ru.itmo.wp.domain.Tag;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

public class PostForm {

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 60)
    private String title;

    @NotBlank
    @Lob
    private String text;

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

    @NotBlank
    @Pattern(regexp = "[a-zA-Z\\s]+")
    private String tags;

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
