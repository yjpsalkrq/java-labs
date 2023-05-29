package servlet;
import java.io.IOException;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.ChatMessage;
import entity.ChatUser;

@WebServlet(name = "LogoutServlet")
public class LogoutServlet extends ChatServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = (String) request.getSession().getAttribute("name");
        if (name!=null) {
            ChatUser aUser = activeUsers.get(name);
            // Если идентификатор сессии пользователя, вошедшего под
// этим именем, совпадает с идентификатором сессии
// пользователя, пытающегося выйти из чата
// (т.е. выходит тот же, кто и входил)
            if (aUser.getSessionId().equals((String)request.getSession().getId())) {
                // Удалить пользователя из списка активных
// Т.к. запросы обрабатываются одновременно,
// нужна синхронизация
                synchronized (activeUsers) {
                    activeUsers.remove(name);
                }
                request.getSession().setAttribute("name", null);
                response.addCookie(new Cookie("sessionId", null));
                response.sendRedirect(response.encodeRedirectURL("/lab8/"));
            } else {
                response.sendRedirect(response.encodeRedirectURL("/lab8/view.html"));
            }
            synchronized (messages) {
                messages.add(new ChatMessage("Пользователь -> " +aUser.getName() + " -> Покинул чат", Ssystem, Calendar.getInstance().getTimeInMillis()));
            }
        } else {
            response.sendRedirect(response.encodeRedirectURL("/lab8/view.html"));

        }
    }
}
