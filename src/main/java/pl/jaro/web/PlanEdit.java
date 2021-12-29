package pl.jaro.web;

import pl.jaro.dao.PlanDao;
import pl.jaro.dao.RecipeDao;
import pl.jaro.model.Plan;
import pl.jaro.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet( "/app/plan/edit")
public class PlanEdit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String created = request.getParameter("created");
        String adminId = request.getParameter("adminId");

        if(id.isEmpty() || name.isEmpty() || description.isEmpty() || created.isEmpty() || adminId.isEmpty()){

            try {
                Integer.parseInt(id);
                Integer.parseInt(adminId);
            }catch (NumberFormatException e){
                request.setAttribute("error", "Niepoprawne dane");
                getServletContext().getRequestDispatcher("/planEdit.jsp").forward(request, response);
            }

            request.setAttribute("error", "Uzupełnij wszystkie pola");
            getServletContext().getRequestDispatcher("/planEdit.jsp").forward(request, response);
        } else {

            PlanDao planDao = new PlanDao();
            Plan plan = new Plan();

            plan.setId(Integer.parseInt(id));
            plan.setName(name);
            plan.setDescription(description);
            plan.setCreated(created);
            plan.setAdminId(Integer.parseInt(adminId));


            planDao.update(plan);

            response.sendRedirect("/app/plan/list");

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (!id.isEmpty()) {
            try {
                PlanDao planDao = new PlanDao();
                request.setAttribute("plan", planDao.read(Integer.parseInt(id)));
                if(planDao.read(Integer.parseInt(id)).getName()==null){
                    request.setAttribute("error","Nie ma takiego planu");
                }
            } catch (NumberFormatException e ) {
                e.printStackTrace();
                request.setAttribute("error","Nie ma takiego planu");
            }
        }

        getServletContext().getRequestDispatcher("/planEdit.jsp")
                .forward(request, response);
    }

}
