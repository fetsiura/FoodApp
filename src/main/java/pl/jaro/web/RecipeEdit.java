package pl.jaro.web;

import pl.jaro.dao.PlanDao;
import pl.jaro.dao.RecipeDao;
import pl.jaro.model.Admins;
import pl.jaro.model.Plan;
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
import java.util.IllegalFormatException;

@WebServlet( "/app/recipe/edit")
public class RecipeEdit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String ingredients = request.getParameter("ingredients");
        String description = request.getParameter("description");
        String created = request.getParameter("created");
        String preparationTime = request.getParameter("preparationTime");
        String preparation = request.getParameter("preparation");
        String adminId = request.getParameter("adminId");

        if(id.isEmpty() || name.isEmpty() || ingredients.isEmpty() || description.isEmpty() || created.isEmpty() ||
                preparationTime.isEmpty() || preparation.isEmpty() || adminId.isEmpty()){

            try {
                Integer.parseInt(preparationTime);
                Integer.parseInt(id);
                Integer.parseInt(adminId);
                Integer.parseInt(preparationTime);
            }catch (NumberFormatException e){
                request.setAttribute("error", "Niepoprawne dane");
                getServletContext().getRequestDispatcher("/recipeAdd.jsp").forward(request, response);
            }

            request.setAttribute("error", "Uzupełnij wszystkie pola");
            getServletContext().getRequestDispatcher("/recipeAdd.jsp").forward(request, response);
        } else {

            RecipeDao recipeDao = new RecipeDao();
            Recipe recipe = new Recipe();

            recipe.setId(Integer.parseInt(id));
            recipe.setName(name);
            recipe.setIngredients(ingredients);
            recipe.setDescription(description);
            recipe.setCreated(created);
            recipe.setUpdated(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toString());
            recipe.setPreparationTime(Integer.parseInt(preparationTime));
            recipe.setPreparation(preparation);
            recipe.setAdminId(Integer.parseInt(adminId));


            recipeDao.update(recipe);
            response.sendRedirect("/app/recipe/list");

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (!id.isEmpty()) {
            try {
                RecipeDao recipeDAO = new RecipeDao();
                Recipe recipe = recipeDAO.read(Integer.parseInt(id));
                request.setAttribute("recipe", recipe);
                if(recipe.getName()==null){
                    request.setAttribute("error","Nie ma takiego przepisu");
                }
            } catch (NumberFormatException e ) {
                e.printStackTrace();
                request.setAttribute("error","Nie ma takiego przepisu");
            }
        }

        getServletContext().getRequestDispatcher("/recipeEdit.jsp")
                .forward(request, response);
    }

}
