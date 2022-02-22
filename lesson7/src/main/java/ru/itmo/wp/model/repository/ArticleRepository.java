package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.Article;

import java.util.List;

public interface ArticleRepository {
    void save(Article article);
    Article find(long id);
    List<Article> findAll();
    List<Long> findUserIdByArticles();
    List<Article> findArticleByUserId(long userId);
    List<Article> findNotHidden();
    void setStatus(long id, boolean status);
}
