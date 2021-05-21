package controllers.times;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Times;
import utils.DBUtil;

/**
 * Servlet implementation class TimesCreateServlet
 */
@WebServlet("/times/create")
public class TimesCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimesCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        this.doPost(request, response);
    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Times t = new Times();

            t.setEmployee((Employee)request.getSession().getAttribute("login_employee"));

            String inTimeLocal = request.getParameter("in_time");
            String outTimeLocal = request.getParameter("out_time");
            inTimeLocal += ":00";
            outTimeLocal += ":00";

            t.setIn_time(Timestamp.valueOf(inTimeLocal.replace("T"," ")));
            t.setOut_time(Timestamp.valueOf(outTimeLocal.replace("T"," ")));
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
            em.close();
            request.getSession().setAttribute("flush", "出勤時間を登録しました。");

            response.sendRedirect(request.getContextPath() + "/times/index");
        }
    }

}
