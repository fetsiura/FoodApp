package pl.jaro.web;

import pl.jaro.dao.PlanDao;
import pl.jaro.dao.RecipeDao;
import pl.jaro.model.Admins;
import pl.jaro.model.Plan;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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

        request.setAttribute("lastPlans",planDao.last(admins.getId())); /// ostatni plan dla zadanego użytkownika
        session.setAttribute("lastPlans",planDao.last(admins.getId())); //// dodaję żeby potem wyciągnąć szczegóły

        request.setAttribute("plans",planDao.findAllSortedByCreated(admins.getId()).size()); /// ilość planów
        request.setAttribute("quantityOfRecipes",recipeDao.findAll(admins.getId()).size()); /// ilość recepty

        Plan plan = planDao.readName(admins.getId());
        request.setAttribute("lastPlan",plan);

        if(session.getAttribute("error")!=null){
            request.setAttribute("error",session.getAttribute("error"));
        }

        session.removeAttribute("error");

        getServletContext().getRequestDispatcher("/dashboard.jsp")
                .forward(request,response);
    }


}
