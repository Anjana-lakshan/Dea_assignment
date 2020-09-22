import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "feedback")
public class feedback extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String company_name = request.getParameter("company_name");
        String subject = request.getParameter("subject");

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/test", "root", "root");
            PreparedStatement ps = c.prepareStatement("select userName,pass from student where userName=? and pass=?");
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3,company_name);
            ps.setString(4,subject);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                response.sendRedirect("success.html");
                return;
            }
            response.sendRedirect("error.html");
            return;
        }
        catch (ClassNotFoundException | SQLException e)
        {

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
