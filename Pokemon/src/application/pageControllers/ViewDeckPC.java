package application.pageControllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.util.URIUtil;
import data.rdg.DeckCardRDG;
import domain.model.Deck;
import domain.service.SingleAppUniqueIdFactory;

/**
 * Servlet implementation class ViewDeckPC
 */
@WebServlet("/ViewDeck")
public class ViewDeckPC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewDeckPC() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	public void viewDeck(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int deckId = URIUtil.parseForIdAtEnd(req.getRequestURL().toString());
		int id = req.getSession(true).getAttribute("userid") == null ? -1 : (int)req.getSession(true).getAttribute("userid");
		if (id < 0) {
			req.setAttribute("message", "You don't login");
			req.setAttribute("status", "fail");
			req.getRequestDispatcher("/WEB-INF/jsp/failure.jsp").forward(req, res);
			return;
		} else {
			ArrayList<DeckCardRDG> cards = Deck.viewDeck(deckId);
			String deck = "";
			for(int i = 0; i < cards.size(); i++) {
				deck = deck + cards.get(i).getType()+ " \"" + cards.get(i).getName() + "\"\n";
			}
			if(cards.size() == 0) {
				req.setAttribute("message", "Deck size is 0");
				req.setAttribute("status", "fail");
				req.getRequestDispatcher("/WEB-INF/jsp/failure.jsp").forward(req, res);
				return;
			} else {
				Deck d = new Deck();
				d.setCards(cards);
				req.setAttribute("id", cards.get(0).getDeckId());
				req.setAttribute("cards", cards);
				req.setAttribute("status", "success");
				req.getRequestDispatcher("/WEB-INF/jsp/viewDeck.jsp").forward(req, res);
				//res.getWriter().append("Served at: ");
				return;
			}
		}
	}
	
	public void viewDecks(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//int deckId = URIUtil.parseForIdAtEnd(req.getRequestURL().toString());
		int id = req.getSession(true).getAttribute("userid") == null ? -1 : (int)req.getSession(true).getAttribute("userid");
		if (id < 0) {
			req.setAttribute("message", "You don't have a associate deck");
			req.setAttribute("status", "fail");
			req.getRequestDispatcher("/WEB-INF/jsp/failure.jsp").forward(req, res);
			return;
		} else {
			ArrayList<Integer> decks = Deck.getDeckIds();
		
			if(decks == null) {
				req.setAttribute("message", "There are no decks");
				req.setAttribute("status", "fail");
				req.getRequestDispatcher("/WEB-INF/jsp/failure.jsp").forward(req, res);
				return;
			} else {
				req.setAttribute("decks", decks);
				req.setAttribute("status", "success");
				req.getRequestDispatcher("/WEB-INF/jsp/viewDecks.jsp").forward(req, res);
				//res.getWriter().append("Served at: ");
				return;
			}
		}
	}


}
