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

@WebServlet( "/app/recipe/plan/delete")
public class RecipeDeleteFromPlan extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idToDeleteStr = request.getParameter("id");

        if(!idToDeleteStr.isEmpty()){
            try {
                PlanDao planDao = new PlanDao();

                planDao.deleteRecipeFromPlan(Integer.parseInt(idToDeleteStr));
                HttpSession session = request.getSession();
                String idToRet = (String) session.getAttribute("idToRet");
                response.sendRedirect("/app/plan/details?id="+idToRet);
            }catch (NumberFormatException e){
                e.printStackTrace();
                request.setAttribute("error","W planie nie ma takiego przepisu");
                getServletContext().getRequestDispatcher("/plans.jsp")
                        .forward(request,response);
            }
        }
        }


}
