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

@WebServlet( "/app/super/user/lock")
public class UserBlock extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        String enable = request.getParameter("enable");

        AdminDao adminDao = new AdminDao();
        try {
            adminDao.lockUser(Integer.parseInt(id),Integer.parseInt(enable));
            response.sendRedirect("/app/super/user/list");


        }catch (NumberFormatException e){
            e.printStackTrace();
        }
    }

}
