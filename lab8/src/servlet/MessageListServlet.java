package servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entity.ChatMessage;

@WebServlet(name = "MessageListServlet")
public class MessageListServlet extends ChatServlet {
    private static final long serialVersionUID = 1L;
    Calendar calendar = new GregorianCalendar();
    private long count;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Установить кодировку HTTP-ответа UTF-8
        response.setCharacterEncoding("utf8");
        // Получить доступ к потоку вывода HTTP-ответа
        PrintWriter pw = response.getWriter();
        // Записть в поток HTML-разметку страницы
        pw.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'/><meta http-equiv='refresh' content='1'></head>");
        pw.println("<body>");
        // В обратном порядке записать в поток HTML-разметку для каждого сообщения
        for (int i=messages.size()-1; i>=messages.size()-messages.get(1).getQuantity(); i--) {
            ChatMessage aMessage = messages.get(i);
            long k = aMessage.getAuthor().getNumber();

            if ((Calendar.getInstance().getTimeInMillis()-messages.get(i).getTimestamp())/1000 <= messages.get(i).getSec()) {
                if (i != messages.size() - 1 && aMessage.getAuthor().equals(messages.get(i+1).getAuthor())) {
                    pw.println("<div><strong>" + aMessage.getAuthor().getName() + "</strong> - (" + i + ") :  "+aMessage.getMessage() + "</div>");
                }
                else {
                    pw.println("<div><strong>" + aMessage.getAuthor().getName() + "</strong> - (" + k + ") (" + calendar.getTime() + ")     :  " + aMessage.getMessage() + "</div>");
                }
            }
        }
        pw.println("</body></html>");
    }
}
