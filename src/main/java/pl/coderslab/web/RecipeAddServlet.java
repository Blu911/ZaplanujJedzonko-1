package pl.coderslab.web;


import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@WebServlet("/app/recipe/add")
public class RecipeAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int preparationTime = Integer.parseInt(request.getParameter("preparationTime"));
        String preparation = request.getParameter("preparation");
        String ingredients = request.getParameter("ingredients");

        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("user_id");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        RecipeDao.create(new Recipe(name, ingredients, description, timestamp, timestamp, preparationTime, userId));
        response.sendRedirect(request.getContextPath() + "/app/recipes");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/appAddRecipe.jsp").forward(request, response);
    }
}
