package pl.jaro.web;

import pl.jaro.dao.AdminDao;
import pl.jaro.model.Admins;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet( "/app/user/edit/password")
public class UserEditPassword extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");


        AdminDao adminDao = new AdminDao();
        if(password.isEmpty() || password.isEmpty()){

            session.setAttribute("error","Dane nie mogą być puste");
            response.sendRedirect("/app/user/edit/password");
        } else if (!password.equals(repassword)) {

            session.setAttribute("error", "Hasła się nie zgadzają");
            response.sendRedirect("/app/user/edit/password");
        } else {

            Admins admins = (Admins) session.getAttribute("admin");
            admins.setPassword(password);
            adminDao.update(admins);

            response.sendRedirect("/app/dashboard");
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if(session.getAttribute("error")!=null){
            request.setAttribute("error", session.getAttribute("error"));
        }
        session.removeAttribute("error");

        getServletContext().getRequestDispatcher("/userPassword.jsp")
                .forward(request, response);
    }

}
