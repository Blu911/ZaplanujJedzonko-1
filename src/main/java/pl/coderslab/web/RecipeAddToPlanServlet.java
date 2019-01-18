package pl.coderslab.web;

import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/app/recipe/plan/add")
public class RecipeAddToPlanServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String number = request.getParameter("number");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Admin user = (Admin) session.getAttribute("user");
        request.setAttribute("recipe", RecipeDao.findAllByAdminId(user.getId()));
        request.setAttribute("plan", PlanDao.findAllByAdminId(user.getId()));
        request.setAttribute("day", DayNameDao.findAll());
        getServletContext().getRequestDispatcher("/appSchedulesMealRecipe.jsp").forward(request, response);
    }
}
