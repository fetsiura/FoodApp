package pl.jaro.web;

import pl.jaro.dao.PlanDao;
import pl.jaro.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet( "/app/plan/delete")
public class PlanDelete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if(!id.isEmpty()){
            try {
                PlanDao planDao = new PlanDao();

                List<Plan> planDetails = planDao.checkIfPlanIsEmpty(Integer.parseInt(id));

                if(planDetails.size()==0){
                    planDao.delete(Integer.parseInt(id));

                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute("ifPlanDetailsExist","1");
                }

                response.sendRedirect("/app/plan/list");

            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }
        }


}
