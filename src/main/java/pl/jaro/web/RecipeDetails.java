package pl.jaro.web;

import pl.jaro.dao.RecipeDao;
import pl.jaro.model.Admins;
import pl.jaro.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet( "/app/recipe/details")
public class RecipeDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if(!id.isEmpty()){
            try {

                RecipeDao recipeDao = new RecipeDao();
                request.setAttribute("recipe",recipeDao.read(Integer.parseInt(id)));
                if(recipeDao.read(Integer.parseInt(id)).getName()==null){
                    request.setAttribute("error","Nie ma takiego przepisu");
                }

            } catch (NumberFormatException e){
                request.setAttribute("error", "Nie ma takiego przepisu");
            }
        }

        getServletContext().getRequestDispatcher("/recipeDetails.jsp")
                .forward(request,response);
    }


}
