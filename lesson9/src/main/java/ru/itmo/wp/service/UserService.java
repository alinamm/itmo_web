package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.Role;
import ru.itmo.wp.domain.Tag;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.form.PostForm;
import ru.itmo.wp.form.UserCredentials;
import ru.itmo.wp.repository.RoleRepository;
import ru.itmo.wp.repository.TagRepository;
import ru.itmo.wp.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final TagRepository tagRepository;

    /** @noinspection FieldCanBeLocal, unused */
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, TagRepository tagRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;

        this.roleRepository = roleRepository;
        for (Role.Name name : Role.Name.values()) {
            if (roleRepository.countByName(name) == 0) {
                roleRepository.save(new Role(name));
            }
        }
    }

    public User register(UserCredentials userCredentials) {
        User user = new User();
        user.setLogin(userCredentials.getLogin());
        userRepository.save(user);
        userRepository.updatePasswordSha(user.getId(), userCredentials.getLogin(), userCredentials.getPassword());
        return user;
    }

    public boolean isLoginVacant(String login) {
        return userRepository.countByLogin(login) == 0;
    }

    public User findByLoginAndPassword(String login, String password) {
        return login == null || password == null ? null : userRepository.findByLoginAndPassword(login, password);
    }

    public User findById(Long id) {
        return id == null ? null : userRepository.findById(id).orElse(null);
    }

    public List<User> findAll() {
        return userRepository.findAllByOrderByIdDesc();
    }

    public void writePost(User user, PostForm postForm) {
        Post post = new Post();
        post.setTitle(postForm.getTitle());
        post.setText(postForm.getText());
        Set<Tag> tags = new HashSet<>();
        for (String tag : postForm.getTags().split("\\s+")) {
            Tag tag2 = tagRepository.findByName(tag);
            if (tag2 == null) {
                tag2 = new Tag();
                tag2.setName(tag);
                tag2 = tagRepository.save(tag2);
            }
            tags.add(tag2);
        }
        post.setTags(new ArrayList<>(tags));
        user.addPost(post);
        userRepository.save(user);
    }
}
