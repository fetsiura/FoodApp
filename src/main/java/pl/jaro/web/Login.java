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

@WebServlet( "/login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        AdminDao adminDao = new AdminDao();

        if(email==null || email.isEmpty() || password==null || password.isEmpty()){
            request.setAttribute("error", "Uzupełnij wszystkie pola");
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }

        if(adminDao.loginValidate(email,password)){
            Admins currentAdmin = adminDao.getUserInfo(email);
            HttpSession session = request.getSession();
            session.setAttribute("adminId",currentAdmin.getId());
            session.setAttribute("adminName",currentAdmin.getFirstName());
            session.setAttribute("admin",currentAdmin);
            response.sendRedirect("/app/dashboard");

        } else {

            request.setAttribute("error","Podano niewłaściwe dane");
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp")
                .forward(request,response);
    }


}
