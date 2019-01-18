package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/app/plan/details")
public class PlanDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int planId = Integer.parseInt(request.getParameter("plan_id"));
        Plan plan = PlanDao.read(planId);

        request.setAttribute("plan", plan);

        HttpSession session = request.getSession();
        int userId = ((Admin) session.getAttribute("user")).getId();
        request.setAttribute("planWithDetails", PlanDao.getPlanWithDetails(userId, planId));

        getServletContext().getRequestDispatcher("/appPlanDetails.jsp").forward(request, response);
    }
}
