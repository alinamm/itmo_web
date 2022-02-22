package ru.itmo.wp.model.service;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.ArticleRepository;
import ru.itmo.wp.model.repository.impl.ArticleRepositoryImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ArticleService {
    private final ArticleRepository articleRepository = new ArticleRepositoryImpl();

    public void validatePublishing(Article article) throws ValidationException {
        if (Strings.isNullOrEmpty(article.getTitle())) {
            throw new ValidationException("Title is required");
        }
        if (article.getTitle().isBlank()) {
            throw new ValidationException("Title is required");
        }


        if (Strings.isNullOrEmpty(article.getText())) {
            throw new ValidationException("Text is required");
        }
        if (article.getText().isBlank()) {
            throw new ValidationException("Text is required");
        }
    }

    public void save(Article article) {
        articleRepository.save(article);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public List<Long> findUserIdByArticles() {
        return articleRepository.findUserIdByArticles();
    }

    public List<Article> findNotHidden() {
        return articleRepository.findNotHidden();
    }

    public List<Article> findArticleByUserId(long userId) {
        return articleRepository.findArticleByUserId(userId);
    }

    public void setStatus(long id, boolean status) {
        articleRepository.setStatus(id, status);
    }

    public boolean isAuthor(User user, Long articleId) {
        List<Article> articles = findArticleByUserId(user.getId());
        for (Article article : articles) {
            if (article.getId() == articleId) {
                return true;
            }
        }
        return false;
    }

    public void validateChange(HttpServletRequest request, User user) throws ValidationException {
        String status = request.getParameter("status");

        if (Strings.isNullOrEmpty(status) || (!status.equals("true") && !status.equals("false"))) {
            throw new ValidationException("Invalid status");
        }

        String articleId = request.getParameter("articleId");
        if (Strings.isNullOrEmpty(articleId) || !articleId.matches("[0-9]+")) {
            throw new ValidationException("Invalid artId");
        }

        Long articleIdNumber = Long.parseLong(articleId);

        if (!isAuthor(user, articleIdNumber)) {
            throw new ValidationException("Only author can change access to the article");
        }
    }
}
