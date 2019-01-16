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

@WebServlet("/app/recipe/add")
public class RecipeAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String preparationTime = request.getParameter("preparationTime");
        String preparation = request.getParameter("preparation");
        String ingredients = request.getParameter("ingredients");

        /** Below is for TEST**/
        System.out.println(name);
        System.out.println(description);
        System.out.println(preparationTime);
        System.out.println(preparation);
        System.out.println(ingredients);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/appAddRecipe.jsp").forward(request, response);
    }
}
