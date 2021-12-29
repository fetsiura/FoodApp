package pl.jaro.web;

import pl.jaro.dao.AdminDao;
import pl.jaro.dao.PlanDao;
import pl.jaro.model.Admins;
import pl.jaro.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet( "/app/user/edit")
public class UserEdit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");


        AdminDao adminDao = new AdminDao();
        if(name.isEmpty() || surname.isEmpty() || email.isEmpty()){

            session.setAttribute("error","Dane nie mogą być puste");
            response.sendRedirect("/app/user/edit");
        } else if (!adminDao.isEmail(email)) {

            session.setAttribute("error", "Niewłaściwy adres email");
            response.sendRedirect("/app/user/edit");
        } else {

            Admins admins = (Admins) session.getAttribute("admin");
            admins.setFirstName(name);
            admins.setLastName(surname);
            admins.setEmail(email);

            adminDao.update(admins);

            response.sendRedirect("/app/dashboard");
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Admins admins = (Admins) session.getAttribute("admin");
        request.setAttribute("admin",admins);

        if(session.getAttribute("error")!=null){
            request.setAttribute("error", session.getAttribute("error"));
        }
        session.removeAttribute("error");


        getServletContext().getRequestDispatcher("/userEdit.jsp")
                .forward(request, response);
    }

}
