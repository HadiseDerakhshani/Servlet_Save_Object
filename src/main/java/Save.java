import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Save extends HttpServlet {
//localhost:8080/register.html

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter result = resp.getWriter();
        resp.setContentType("text/html");
        try {

            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Visitor visitor = new Visitor();
            visitor.setNationalCode(Integer.parseInt(req.getParameter("code")));
            visitor.setName(req.getParameter("name"));
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.persist(visitor);
            session.getTransaction().commit();
            session.close();


            result.println("<html><body><h1 style=\"text-align:center;\"> Successfully Inserted"
                    + "</h1></body></html>");
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.close();

    }
}