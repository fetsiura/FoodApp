package pl.jaro.web;

import pl.jaro.dao.AdminDao;
import pl.jaro.dao.PlanDao;
import pl.jaro.dao.RecipeDao;
import pl.jaro.model.Admins;
import pl.jaro.model.LastPlan;
import pl.jaro.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet( "/app/dashboard")
public class Dashboard extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Admins admins = (Admins) session.getAttribute("admin");
        request.setAttribute("admin",admins); //// imię

        PlanDao planDao = new PlanDao();
        RecipeDao recipeDao = new RecipeDao();

        List<LastPlan> last = planDao.last(admins.getId());
        request.setAttribute("lastPlans",last); /// ostatni plan dla zadanego użytkownika
        session.setAttribute("lastPlans",last); //// dodaję żeby potem wyciągnąć szczegóły

        request.setAttribute("quantityOfPlans",planDao.findAll(admins.getId()).size()); /// ilość planów
        request.setAttribute("quantityOfRecipes",recipeDao.findAll(admins.getId()).size()); /// ilość recepty

        Plan plan = planDao.readName(admins.getId());
        request.setAttribute("lastPlan",plan);

        getServletContext().getRequestDispatcher("/dashboard.jsp")
                .forward(request,response);
    }


}
