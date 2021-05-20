package controllers.times;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Times;
import utils.DBUtil;

/**
 * Servlet implementation class TimesUpdateServlet
 */
@WebServlet("/times/update")
public class TimesUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimesUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token!=null && _token.equals(request.getSession().getId())){
            EntityManager em = DBUtil.createEntityManager();
            Times t = em.find(Times.class,(Integer)request.getSession().getAttribute("id"));
            String inTimeLocal = request.getParameter("in_time");
            String outTimeLocal = request.getParameter("out_time");
            inTimeLocal += ":00";
            outTimeLocal += ":00";

            t.setIn_time(Timestamp.valueOf(inTimeLocal.replace("T"," ")));
            t.setOut_time(Timestamp.valueOf(outTimeLocal.replace("T"," ")));
            em.getTransaction().begin();
            em.getTransaction().commit();
            em.close();
            request.getSession().setAttribute("flush", "更新が完了しました。");

            request.getSession().removeAttribute("id");

            response.sendRedirect(request.getContextPath() + "/times/index");
        }
    }

}
