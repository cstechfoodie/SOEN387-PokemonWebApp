package application.pageControllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.rdg.GameRDG;
import domain.model.Board;

/**
 * Servlet implementation class RetirePC
 */
@WebServlet("/Retire")
public class RetirePC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetirePC() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
				//retire from the game. The status of the player should be reset
				GameRDG g = GameRDG.find(gameId);
				g.delete();
				req.setAttribute("message", "User with id =  " + id + " has successfully retire from the game");
				req.setAttribute("status", "success");
				req.getRequestDispatcher("WEB-INF/jsp/success.jsp").forward(req, res);
				return;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
