package application.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.util.URIUtil;
import data.rdg.HandCardRDG;
import domain.model.Board;
import domain.model.State;

/**
 * Servlet implementation class ViewBoardPC
 */
@WebServlet("/ViewBoard")
public class ViewBoardPC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewBoardPC() {
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
	
	public void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int gameId = URIUtil.parseForIdAtEnd(req.getRequestURI());
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
			req.getRequestDispatcher("/WEB-INF/jsp/failure.jsp").forward(req, res);
			return;
		} else {
			try {
				board.fillBoardData();
				req.setAttribute("id", gameId);
				req.setAttribute("version", board.getVersion());
				req.setAttribute("current", board.getCurrent());
				int[] players = board.getPlayers();
				req.setAttribute("players", players);
				int[] decks = board.getDecks();
				req.setAttribute("decks", decks);
				State s1 = board.getPlay().get(players[0]+"");
				req.setAttribute("player1", players[0]+"");
				req.setAttribute("s1", s1);
				State s2 = board.getPlay().get(players[1]+"");
				req.setAttribute("player2", players[1]+"");
				req.setAttribute("s2", s2);
				req.setAttribute("status", "success");
				req.getRequestDispatcher("/WEB-INF/jsp/viewBoard.jsp").forward(req, res);
				return;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
