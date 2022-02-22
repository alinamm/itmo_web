package ru.itmo.wp.servlet;

import com.google.gson.Gson;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;


public class MessagesServlet extends HttpServlet {
    private final ArrayList<Message> messages = new ArrayList<>();

    static class Message {
        final String user;
        final String text;

        public Message(String user, String text) {
            this.user = user;
            this.text = text;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getRequestURI().equals("/message/auth")) {
            HttpSession session = request.getSession();
            String user = request.getParameter("user");
            response.setContentType("application/json");
            if (user == null) {
                user = (String) session.getAttribute("user");
            }
            if (user == null) {
                user = "";
            }
            session.setAttribute("user", user);
            response.getWriter().print(new Gson().toJson(user));
            response.getWriter().flush();
        }
        if (request.getRequestURI().equals("/message/findAll")) {
            response.setContentType("application/json");
            response.getWriter().print(new Gson().toJson(messages));
            response.getWriter().flush();
        }
        if (request.getRequestURI().equals("/message/add")) {
            HttpSession session = request.getSession();
            String user = (String) session.getAttribute("user");
            Message message = new Message(user, request.getParameter("text"));
            messages.add(message);
        }
    }
}
