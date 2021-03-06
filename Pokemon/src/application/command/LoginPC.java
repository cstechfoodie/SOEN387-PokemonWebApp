package application.command;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.rdg.UserRDG;

/**
 * Servlet implementation class LoginPC
 */
@WebServlet("/Login")
public class LoginPC extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginPC() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
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
		String user = req.getParameter("user");
		String pass = req.getParameter("pass");
		UserRDG u = null;
		try {
			u = UserRDG.find(user, pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (u == null) {
			req.setAttribute("message", "I do not recognize that username and password combination.");
			req.setAttribute("status", "fail");
			req.getRequestDispatcher("/WEB-INF/jsp/failure.jsp").forward(req, res);
			return;
		} else {
			int id = u.getId();
			req.getSession(true).setAttribute("userid", id);
			req.setAttribute("message", "User " + u.getUsername() + " has been successfully logged in.");
			req.setAttribute("status", "success");
			req.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(req, res);
			return;
		}
	}
}
