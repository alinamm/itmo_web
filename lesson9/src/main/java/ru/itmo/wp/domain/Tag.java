package ru.itmo.wp.domain;

import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

/** @noinspection unused*/
@Entity
@Table
public class Tag {

    @Id
    @GeneratedValue
    private long id;

    @NotBlank
    @Pattern(regexp = "[a-zA-Z]+")
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /** @noinspection unused*/
    public Tag() {
    }

}