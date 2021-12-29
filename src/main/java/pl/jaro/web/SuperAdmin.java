package pl.jaro.web;

import pl.jaro.dao.AdminDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( "/app/super/user/list")
public class SuperAdmin extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AdminDao adminDao = new AdminDao();
        request.setAttribute("users", adminDao.findAllForSuper());
        getServletContext().getRequestDispatcher("/superadmin.jsp")
                .forward(request, response);
    }

}
