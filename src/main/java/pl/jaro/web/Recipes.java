package pl.jaro.web;

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

@WebServlet( "/app/recipe/list")
public class Recipes extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Admins admins = (Admins) session.getAttribute("admin");
        request.setAttribute("admin",admins); //// imię

        PlanDao planDao = new PlanDao();
        RecipeDao recipeDao = new RecipeDao();



        request.setAttribute("recipes",recipeDao.findAll(admins.getId())); /// wszystkie recepty


        getServletContext().getRequestDispatcher("/recipes.jsp")
                .forward(request,response);
    }


}
