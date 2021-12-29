package pl.jaro.web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/app/super/*")
public class FilterForSuper implements javax.servlet.Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        HttpSession session = servletRequest.getSession();
        if (session.getAttribute("superadmin")==null){
            session.setAttribute("error","Nie posiadasz praw administratora");
            servletResponse.sendRedirect("/app/dashboard");
        }else {

            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
