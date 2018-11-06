package application.pageControllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.rdg.UserRDG;

/**
 * Servlet implementation class LoginPC
 */
@WebServlet("/LoginPC")
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

	private void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String user = req.getParameter("user");
		String pass = req.getParameter("pass");
		UserRDG u = UserRDG.find(user, pass);
		if (u == null) {
			req.setAttribute("message", "I do not recognize that username and password combination.");
			//req.forward("failure.jsp");
			req.getRequestDispatcher("failure.jsp").forward(req, res);
		} else {
			long id = u.getId();
			req.getSession(true).setAttribute("userid", id);
			req.setAttribute("message", "User '" + u.getUsername() + "' has been successfully logged in.");
			//req.forward("success.jsp");
			req.getRequestDispatcher("success.jsp").forward(req, res);
		}
	}

}
