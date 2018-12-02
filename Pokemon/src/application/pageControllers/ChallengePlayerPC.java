package application.pageControllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.util.URIUtil;
import data.rdg.ChallengeRDG;
import data.rdg.DeckCardRDG;
import data.rdg.UserRDG;
import domain.model.Deck;

/**
 * Servlet implementation class ChallengePlayerPC
 */
@WebServlet("/ChallengePlayer")
public class ChallengePlayerPC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChallengePlayerPC() {
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
	
	public void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int deckId = Integer.parseInt(req.getParameter("deck"));
		int playerId = URIUtil.parseForIdInBeteewn(req.getRequestURI());
		int id = req.getSession(true).getAttribute("userid") == null ? -1 : (int)req.getSession(true).getAttribute("userid");
		if(id < 0) {
			req.setAttribute("message", "User Not Login");
			req.setAttribute("status", "fail");
			req.getRequestDispatcher("/WEB-INF/jsp/failure.jsp").forward(req, res);
			return;
		}
		if(playerId == id) {
			req.setAttribute("message", "Can't challenge yourself.");
			req.setAttribute("status", "fail");
			req.getRequestDispatcher("/WEB-INF/jsp/failure.jsp").forward(req, res);
			return;
		}
		if(Deck.isMyOwnDeck(playerId, deckId)) {
			req.setAttribute("message", "Can't chanllenge with someone else's deck.");
			req.setAttribute("status", "fail");
			req.getRequestDispatcher("/WEB-INF/jsp/failure.jsp").forward(req, res);
			return;
		}
		ArrayList<DeckCardRDG> cards = Deck.viewDeck(deckId);
		if(cards.size() == 0) {
			req.setAttribute("message", "No deck uploaded");
			req.setAttribute("status", "fail");
			req.getRequestDispatcher("/WEB-INF/jsp/failure.jsp").forward(req, res);
			return;
		}
		
		UserRDG u = null;
		try {
			u = UserRDG.find(playerId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		if (u == null) {
			req.setAttribute("message", "Invalid challengee id");
			req.setAttribute("status", "fail");
			req.getRequestDispatcher("/WEB-INF/jsp/failure.jsp").forward(req, res);
			return;
		} else {
			ChallengeRDG ch = new ChallengeRDG(id, playerId, 0, deckId);
			try {
				ch.insert();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("message", "Challenge with id =  " + ch.getId() + " has been successfully created.");
			req.setAttribute("status", "success");
			req.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(req, res);
			return;
		}
	}

}
