package pl.jaro.web;

import pl.jaro.dao.PlanDao;
import pl.jaro.model.Admins;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet( "/app/plan/list")
public class Plans extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String planNotEmpty = "";
        if(session.getAttribute("ifPlanDetailsExist")!=null){
            planNotEmpty = (String) session.getAttribute("ifPlanDetailsExist");
        }

        session.removeAttribute("ifPlanDetailsExist");

        Admins admins = (Admins) session.getAttribute("admin");
        request.setAttribute("admin",admins); //// imię
        PlanDao planDao = new PlanDao();
        if(planNotEmpty.length()!=0){
            request.setAttribute("ifPlanDetailsExist","Najpierw musisz wykasować wszystkie zapisy w planie");
        }
        request.setAttribute("plans",planDao.findAllSortedByCreated(admins.getId()));
        getServletContext().getRequestDispatcher("/plans.jsp")
                .forward(request,response);
    }


}
