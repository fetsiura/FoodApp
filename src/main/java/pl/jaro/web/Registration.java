package pl.jaro.web;

import pl.jaro.dao.AdminDao;
import pl.jaro.model.Admins;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( "/registration")
public class Registration extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AdminDao adminDao = new AdminDao();
        String name = request.getParameter("name");
        String lastname = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");


        System.out.println(name);
        System.out.println(lastname);
        System.out.println(email);
        System.out.println(password);
        System.out.println(repassword);


        if ((name == null || name.isEmpty()) ||
                (lastname == null || lastname.isEmpty()) ||
                (email == null || email.isEmpty()) ||
                (password == null || password.isEmpty()) ||
                (repassword == null || repassword.isEmpty())) {
            request.setAttribute("error", "Uzupełnij wszystkie pola");
            getServletContext().getRequestDispatcher("/registration.jsp").forward(request, response);
        }else if(!password.equals(repassword)){
            request.setAttribute("error","Podane hasła się różnią");
            getServletContext().getRequestDispatcher("/registration.jsp").forward(request, response);
        } else if(!adminDao.isEmail(email)){
            request.setAttribute("error","Niewłaściwy adres email");
            getServletContext().getRequestDispatcher("/registration.jsp").forward(request, response);
        } else if(adminDao.ifExists(email)){
            request.setAttribute("error","Podany email jest już zajęty");
            getServletContext().getRequestDispatcher("/registration.jsp").forward(request, response);
        }else {
            Admins admins = new Admins();
            admins.setFirstName(name);
            admins.setLastName(lastname);
            admins.setEmail(email);
            admins.setPassword(password);
            admins.setSuperadmin(1);
            admins.setEnable(1);

            request.setAttribute("error",null);
            adminDao.create(admins);
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/registration.jsp")
                .forward(request,response);
    }


}
