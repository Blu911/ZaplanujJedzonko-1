package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

@WebServlet("/app/plan/list")
public class PlanList extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /** Otworzenie sessji i ściągnięcie aktualnego usera**/
        HttpSession session = request.getSession();
        Admin user = (Admin) session.getAttribute("user");

        /** Wyciągnięcie z bazy wszystkich planów danego Usera i wrzucenie ich do Listy która zawiera objekty Plan**/
        List<Plan> plans = PlanDao.findAllByAdminId(user.getId());

        /** Posortowanie listy po dacie od najmłodszej**/

        plans.sort(Comparator.comparing(Plan::getCreated).reversed());
        /** Przypisanie posortowanej listy do Atrybtu do wyświetlania*/
        request.setAttribute("plan", plans);

        getServletContext().getRequestDispatcher("/appPlanList.jsp").forward(request, response);

    }
}
