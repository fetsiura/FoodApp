package pl.jaro.web;

import pl.jaro.dao.PlanDao;
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
import java.time.format.DateTimeFormatter;

@WebServlet( "/app/recipe/add")
public class RecipeAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String preparationTime = request.getParameter("preparationTime");
        String preparation = request.getParameter("preparation");
        String ingredients = request.getParameter("ingredients");

        if(name.isEmpty() || description.isEmpty() || preparationTime.isEmpty() || preparation.isEmpty() || ingredients.isEmpty()){

            try {
                int i = Integer.parseInt(preparationTime);
            }catch (NumberFormatException e){
                request.setAttribute("error", "Niepoprawny czas");
                getServletContext().getRequestDispatcher("/recipeAdd.jsp").forward(request, response);
            }

            request.setAttribute("error", "Uzupełnij wszystkie pola");
            getServletContext().getRequestDispatcher("/recipeAdd.jsp").forward(request, response);
        } else {

            HttpSession session = request.getSession();

            Admins admins = (Admins) session.getAttribute("admin");
            RecipeDao recipeDao = new RecipeDao();
            Recipe recipe = new Recipe();

            recipe.setName(name);
            recipe.setIngredients(ingredients);
            recipe.setDescription(description);
            recipe.setCreated(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toString());
            recipe.setUpdated(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toString());
            recipe.setPreparationTime(Integer.parseInt(preparationTime));
            recipe.setPreparation(preparation);
            recipe.setAdminId(admins.getId());
            recipeDao.create(recipe,admins.getId());
            response.sendRedirect("/app/recipe/list");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        getServletContext().getRequestDispatcher("/recipeAdd.jsp")
                .forward(request,response);
    }


}
