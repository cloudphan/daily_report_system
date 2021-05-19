package controllers.times;

import java.io.IOException;

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
 * Servlet implementation class TimesEditServlet
 */
@WebServlet("/times/edit")
public class TimesEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimesEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();
        Times t = em.find(Times.class, Integer.parseInt(request.getParameter("id")));
        em.close();

        request.setAttribute("times",t);
        request.setAttribute("_token", request.getSession().getId());
        request.getSession().setAttribute("id", t.getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/times/edit.jsp");
        rd.forward(request, response);

    }

}
