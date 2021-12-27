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

@WebServlet( "/app/plan/add")
public class PlanAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        if(name.isEmpty() || description.isEmpty()){

            request.setAttribute("error", "Uzupełnij wszystkie pola");
            getServletContext().getRequestDispatcher("/planAdd.jsp").forward(request, response);
        } else {

            HttpSession session = request.getSession();

            Admins admins = (Admins) session.getAttribute("admin");
            PlanDao planDao = new PlanDao();
            Plan plan = new Plan();
            plan.setName(name);
            plan.setDescription(description);
            plan.setAdminId(admins.getId());
            plan.setCreated(LocalDateTime.now().toString());
            planDao.create(plan);

            response.sendRedirect("/app/plan/list");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        getServletContext().getRequestDispatcher("/planAdd.jsp")
                .forward(request,response);
    }


}
