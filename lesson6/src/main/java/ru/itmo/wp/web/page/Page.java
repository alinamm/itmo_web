package ru.itmo.wp.web.page;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class Page {
    private final UserService userService = new UserService();
    private HttpServletRequest httpRequest;

    private void action(HttpServletRequest request, Map<String, Object> view) {

    }
    protected void before(HttpServletRequest request, Map<String, Object> view){
        httpRequest = request;
        view.put("userCount", userService.findCount());
        putUser(request, view);
        putMessage(request, view);
    }

    protected void after(HttpServletRequest request, Map<String, Object> view){}

    private void putUser(HttpServletRequest request, Map<String, Object> view) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            view.put("user", user);
        }
    }

    void setMessage(String message) {
        httpRequest.getSession().setAttribute("message", message);
    }

    void setUser(User user) {
        httpRequest.getSession().setAttribute("user", user);
    }

    User getUser() {
        return (User) httpRequest.getSession().getAttribute("user");
    }

    private void putMessage(HttpServletRequest request, Map<String, Object> view) {
        String message = (String) request.getSession().getAttribute("message");
        if (!Strings.isNullOrEmpty(message)) {
            view.put("message", message);

            request.getSession().removeAttribute("message");
        }
    }
}
