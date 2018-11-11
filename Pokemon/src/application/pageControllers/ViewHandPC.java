package application.pageControllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.rdg.HandCardRDG;
import domain.model.Board;

/**
 * Servlet implementation class ViewHandPC
 */
@WebServlet("/ViewHand")
public class ViewHandPC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewHandPC() {
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
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String game = req.getParameter("game");
		int gameId = Integer.parseInt(game);
		int id = req.getSession(true).getAttribute("userid") == null ? -1 : (int)req.getSession(true).getAttribute("userid");
		if(id < 0) {
			req.setAttribute("message", "User Not Login");
			req.setAttribute("status", "fail");
			req.getRequestDispatcher("WEB-INF/jsp/failure.jsp").forward(req, res);
			return;
		}
		
		Board board = new Board(gameId);
		boolean isMyGame = false;
		try {
			isMyGame = board.isMyGame(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!isMyGame) {
			req.setAttribute("message", "This is not your game.");
			req.setAttribute("status", "fail");
			req.getRequestDispatcher("WEB-INF/jsp/failure.jsp").forward(req, res);
			return;
		} else {
			try {
				ArrayList<HandCardRDG> cards = HandCardRDG.viewHand(id);
				req.setAttribute("hand", cards);
				req.setAttribute("status", "success");
				req.getRequestDispatcher("WEB-INF/jsp/viewHand.jsp").forward(req, res);
				return;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
