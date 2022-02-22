package ru.itmo.web.lesson4.util;

import ru.itmo.web.lesson4.model.Post;
import ru.itmo.web.lesson4.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class DataUtil {
    private static final List<User> USERS = Arrays.asList(
            new User(1, "MikeMirzayanov", "Mike Mirzayanov"),
            new User(6, "pashka", "Pavel Mavrin"),
            new User(9, "geranazarov555", "Georgiy Nazarov"),
            new User(11, "tourist", "Gennady Korotkevich")
    );

    private static final List<Post> POSTS = Arrays.asList(
            new Post(1, "Hello", "hello hello hello hello hello hello", 1),
            new Post(2, "Hey", "hey hey hey hey hey hey", 9),
            new Post(5, "Monday", "monday monday monday monday", 9),
            new Post(6, "Tuesday", "tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday tuesday ", 9)
    );

    public static void addData(HttpServletRequest request, Map<String, Object> data) {
        data.put("users", USERS);

        for (User user : USERS) {
            if (Long.toString(user.getId()).equals(request.getParameter("logged_user_id"))) {
                data.put("user", user);
            }
        }

        data.put("posts", POSTS);

        data.put("currentState", request.getRequestURI());
    }
}
