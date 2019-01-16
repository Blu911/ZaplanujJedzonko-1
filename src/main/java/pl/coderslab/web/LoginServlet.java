package pl.coderslab.web;


import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

/** Checking if inserted email and password is in database
 *  If yes opening session and redirecting to HomePage
 *  If not showing LoginPage**/
        List<Admin> adminList = AdminDao.findAll();
        for (Admin admin : adminList) {
            if (Objects.equals(admin.getEmail(), email) && BCrypt.checkpw(password, admin.getPassword())) {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
                return;
            }
        }
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
