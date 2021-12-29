package pl.jaro.web;

import pl.jaro.dao.RecipeDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( "/app/recipe/delete")
public class RecipeDelete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if(!id.isEmpty()){
            try {
                RecipeDao recipeDao = new RecipeDao();
                recipeDao.delete(Integer.parseInt(id));
                response.sendRedirect("/app/recipe/list");
            }catch (NumberFormatException e){
                e.printStackTrace();
                request.setAttribute("error","W planie nie ma takiego przepisu");
                getServletContext().getRequestDispatcher("/recipes.jsp")
                        .forward(request,response);
            }
        }
        }


}
