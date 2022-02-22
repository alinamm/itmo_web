package ru.itmo.wp.web.page;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.ArticleService;
import ru.itmo.wp.web.exception.RedirectException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class MyArticlesPage {
    private final ArticleService articleService = new ArticleService();

    private void action(HttpServletRequest request, Map<String, Object> view) {
        avaliable(request);
        User user = (User) request.getSession().getAttribute("user");
        view.put("articles", articleService.findArticleByUserId(user.getId()));
        putMessage(request, view);
    }


    private void changeStatus(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        avaliable(request);

        User user = (User) request.getSession().getAttribute("user");
        articleService.validateChange(request, user);

        long id = Long.parseLong(request.getParameter("articleId"));
        boolean status = Boolean.parseBoolean(request.getParameter("status"));

        articleService.setStatus(id, status);
    }

    private void putMessage(HttpServletRequest request, Map<String, Object> view) {
        avaliable(request);
        String message = (String) request.getSession().getAttribute("message");
        if (!Strings.isNullOrEmpty(message)) {
            view.put("message", message);
            request.getSession().removeAttribute("message");
        }
    }

    private void avaliable(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") == null) {
            request.getSession().setAttribute("message", "Publish articles available only for auth users");
            throw new RedirectException("/index");
        }
    }

}
