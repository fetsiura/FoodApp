package pl.jaro.web;


import pl.jaro.dao.DayNameDao;
import pl.jaro.dao.PlanDao;
import pl.jaro.dao.RecipeDao;
import pl.jaro.model.Admins;
import pl.jaro.model.DayName;
import pl.jaro.model.RecipePlan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet( "/app/recipe/plan/add")
public class RecipePlanAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        httpSession.removeAttribute("error");

        String recipeId = request.getParameter("recipeId");
        String mealName = request.getParameter("mealName");
        String number = request.getParameter("number");
        String dayId = request.getParameter("dayId");
        String planId = request.getParameter("planId");

        if(recipeId.isEmpty() || mealName.isEmpty() || number.isEmpty() || dayId.isEmpty() || planId.isEmpty()){

            httpSession.setAttribute("error","Uzupełnij wszystkie pola");
            response.sendRedirect("/app/recipe/plan/add");
        } else {
            RecipeDao recipeDao = new RecipeDao();
            RecipePlan recipePlan = new RecipePlan();

            try {

                recipePlan.setRecipeId(Integer.parseInt(recipeId));
                recipePlan.setMealName(mealName);
                recipePlan.setDisplayOrder(Integer.parseInt(number));
                recipePlan.setDayNameId(Integer.parseInt(dayId));
                recipePlan.setPlanId(Integer.parseInt(planId));

                recipeDao.createRecipePlan(recipePlan);
                doGet(request,response);

            }catch (NumberFormatException e){
                request.setAttribute("error", "Niepoprawne dane");
                getServletContext().getRequestDispatcher("/recipePlanAdd.jsp").forward(request, response);
            }

        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("error") !=null){
            request.setAttribute("error",session.getAttribute("error"));
        }

        Admins admins = (Admins) session.getAttribute("admin");
        request.setAttribute("admin",admins); //// imię

        PlanDao planDao = new PlanDao();
        RecipeDao recipeDao = new RecipeDao();
        DayNameDao dayNameDao = new DayNameDao();

        request.setAttribute("plans",planDao.findAllSortedByCreated(admins.getId()));
        request.setAttribute("recipes",recipeDao.findAll(admins.getId()));
        request.setAttribute("days",dayNameDao.findAll());


        getServletContext().getRequestDispatcher("/recipePlanAdd.jsp")
                .forward(request,response);
    }


}
