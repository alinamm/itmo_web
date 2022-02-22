package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.repository.PostRepository;

import java.util.List;
import java.util.Objects;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post findById(Long id) {
        return id == null ? null : postRepository.findById(id).orElse(null);
    }

    public List<Post> findAll() {
        return postRepository.findAllByOrderByCreationTimeDesc();
    }

    public void publish(User user, Comment comment, String id) {
        Post post = postRepository.findById(Long.parseLong(id)).orElse(null);
        comment.setUser(user);
        comment.setPost(post);
        Objects.requireNonNull(post).getComments().add(comment);
        postRepository.save(post);
    }
}
