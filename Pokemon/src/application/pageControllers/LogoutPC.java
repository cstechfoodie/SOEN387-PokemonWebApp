package application.pageControllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.rdg.UserRDG;

/**
 * Servlet implementation class LogoutPC
 */
@WebServlet("/Logout")
public class LogoutPC extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutPC() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		long id = (Long) req.getSession(true).getAttribute("userid");
		UserRDG u = null;
		try {
			u = UserRDG.find(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.getSession(true).invalidate();
		if(id == u.getId()) {
			req.setAttribute("message", "User '" + u.getUsername() + "' has been successfully logged out.");
			req.setAttribute("status", "success");
			req.getRequestDispatcher("WEB-INF/jsp/success.jsp").forward(req, res);
		} else {
			req.setAttribute("message", "You have not successfully logged in.");
			req.setAttribute("status", "fail");
			req.getRequestDispatcher("WEB-INF/jsp/failure.jsp").forward(req, res);
		}
	}

}
