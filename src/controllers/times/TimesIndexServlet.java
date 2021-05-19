package controllers.times;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Times;
import utils.DBUtil;

/**
 * Servlet implementation class TimesIndexServlet
 */
@WebServlet("/times/index")
public class TimesIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimesIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        int page = 1;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(NumberFormatException e) { }
        List<Times> times = em.createNamedQuery("getAllTimes", Times.class)
                                     .setFirstResult(10 * (page - 1))
                                     .setMaxResults(10)
                                     .getResultList();

        long times_count = (long)em.createNamedQuery("getCount", Long.class)
                                       .getSingleResult();

        em.close();

        request.setAttribute("times", times);
        request.setAttribute("times_count", times_count);
        request.setAttribute("page", page);
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/times/index.jsp");
        rd.forward(request, response);	}

}
