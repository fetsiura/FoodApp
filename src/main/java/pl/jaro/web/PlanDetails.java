package pl.jaro.web;

import pl.jaro.dao.PlanDao;
import pl.jaro.dao.RecipeDao;
import pl.jaro.model.LastPlan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet( "/app/plan/details")
public class PlanDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if(!id.isEmpty()){
            try {

                PlanDao planDao = new PlanDao();
                request.setAttribute("plan",planDao.read(Integer.parseInt(id)));
                request.setAttribute("planDetails",planDao.detailsOfLastPlan(Integer.parseInt(id)));

            } catch (NumberFormatException e){
                request.setAttribute("error", "Nie istnieje takiego planu");
                getServletContext().getRequestDispatcher("/plans.jsp")
                        .forward(request, response);

            }

        }

        getServletContext().getRequestDispatcher("/planDetails.jsp")
                .forward(request,response);
    }


}
