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
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class RegisterPC extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterPC() {
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
		processRequest(request, response);
	}

	public void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String user = req.getParameter("user");
		String pass = req.getParameter("pass");
		if (user == null || user.isEmpty() || pass == null || pass.isEmpty()) {
			req.setAttribute("message", "Please enter both a username and a password.");
			req.setAttribute("status", "fail");
			req.getRequestDispatcher("WEB-INF/jsp/failure.jsp").forward(req, res);
			return;
		} else {
			UserRDG u = null;
			try {
				u = UserRDG.find(user);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (u != null) {
				req.setAttribute("message", "That user has already registered.");
				req.setAttribute("status", "fail");
				req.getRequestDispatcher("WEB-INF/jsp/failure.jsp").forward(req, res);
				return;
			} else {
				u = new UserRDG(user, pass);
				try {
					u.insert();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int id = u.getId();
				req.getSession(true).setAttribute("userid", id);
				req.setAttribute("message", "User " + user + " has been successfully registered.");
				req.setAttribute("status", "success");
				req.getRequestDispatcher("WEB-INF/jsp/success.jsp").forward(req, res);
				return;
			}
		}
	}

}
