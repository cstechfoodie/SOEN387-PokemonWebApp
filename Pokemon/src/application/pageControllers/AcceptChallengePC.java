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
import data.rdg.GameRDG;
import data.rdg.UserRDG;
import domain.model.Deck;

/**
 * Servlet implementation class AcceptChallengePC
 */
@WebServlet("/AcceptChallenge")
public class AcceptChallengePC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceptChallengePC() {
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
		//String challenge = req.getParameter("challenge");
		int challengeId = URIUtil.parseForIdInBeteewn(req.getRequestURI());
		int deckId = Integer.parseInt(req.getParameter("deck"));
		int version = Integer.parseInt(req.getParameter("version"));
		ChallengeRDG ch = null;
		try {
			ch = ChallengeRDG.find(challengeId);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int id = req.getSession(true).getAttribute("userid") == null ? -1 : (int)req.getSession(true).getAttribute("userid");
		
		if(id < 0) {
			req.setAttribute("message", "User Not Login");
			req.setAttribute("status", "fail");
			req.getRequestDispatcher("/WEB-INF/jsp/failure.jsp").forward(req, res);
			return;
		}
		if(ch.getChallengee() != id) {
			req.setAttribute("message", "Can't accept others' challenge.");
			req.setAttribute("status", "fail");
			req.getRequestDispatcher("/WEB-INF/jsp/failure.jsp").forward(req, res);
			return;
		}
		
		if(ch.getChallenger() == id) {
			req.setAttribute("message", "Can't accept own challenge.");
			req.setAttribute("status", "fail");
			req.getRequestDispatcher("/WEB-INF/jsp/failure.jsp").forward(req, res);
			return;
		}

		
		boolean success = false;
		//System.out.println("from accept challenge: " + deckId + "  " + version);
		if(Deck.isMyOwnDeck(ch.getChallengee(), deckId) && version == ch.getVersion()) {
			success = ChallengeRDG.updateStatus(challengeId, version, 3);				
		} else {
			req.setAttribute("message", "Challanger deckId or version doesn't match");
			req.setAttribute("status", "fail");
			req.getRequestDispatcher("/WEB-INF/jsp/failure.jsp").forward(req, res);
			return;
		}
		
		if(success) {
			GameRDG g = new GameRDG(ch.getId(), ch.getChallenger(), ch.getChallengee(), 1);
			//should update player's status if more tests added
//			try {
//				UserRDG player1 = UserRDG.find(ch.getChallenger());
//				UserRDG player2 = UserRDG.find(ch.getChallengee());
//				player1.setStatus("playing");
//				player2.setStatus("playing");
//				player1.update();
//				player2.update();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			try {
				g.insert();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("message", "User with id =  " + ch.getChallengee() + " has successfully accept challenge.");
			req.setAttribute("status", "success");
			req.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(req, res);
			return;
		}
		else {
			//lost update situation
			req.setAttribute("message", "Suspected lost update");
			req.setAttribute("status", "fail");
			req.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(req, res);
			return;
		}
	
	}

}
