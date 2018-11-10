package application.pageControllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.rdg.UserRDG;

/**
 * Servlet implementation class ListPlayersPC
 */
@WebServlet("/ListPlayers")
public class ListPlayersPC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListPlayersPC() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	private void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int id = req.getSession(true).getAttribute("userid") == null ? -1 : (int)req.getSession(true).getAttribute("userid");
		ArrayList<UserRDG> u = null;
		try {
			u = (ArrayList<UserRDG>) UserRDG.findAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(id < 0) {
			req.setAttribute("message", "You have not successfully logged in.");
			req.setAttribute("status", "fail");
			req.getRequestDispatcher("WEB-INF/jsp/failure.jsp").forward(req, res);
		} else {
			if (u.size() == 0) {
				req.setAttribute("message", "No players exist.");
				req.setAttribute("status", "fail");
				req.getRequestDispatcher("WEB-INF/jsp/failure.jsp").forward(req, res);
			} else {
				req.setAttribute("players", u);
				req.setAttribute("status", "success");
				req.getRequestDispatcher("WEB-INF/jsp/listPlayers.jsp").forward(req, res);
			}	
		}
	}
}
