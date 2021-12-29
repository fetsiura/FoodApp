package pl.jaro.web;

import pl.jaro.dao.PlanDao;
import pl.jaro.dao.RecipeDao;
import pl.jaro.model.Admins;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet( "/recipe/list")
public class RecipesNotLogin extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RecipeDao recipeDao = new RecipeDao();
        String name = request.getParameter("name");

        if(name==null){
            name="";
        }

        if(!name.isEmpty() ){
            request.setAttribute("recipes", recipeDao.findAllByName(name));
        } else {
            request.setAttribute("recipes",recipeDao.findAllSortedByDate());
        }


        getServletContext().getRequestDispatcher("/recipesNotLogin.jsp")
                .forward(request,response);
    }


}
