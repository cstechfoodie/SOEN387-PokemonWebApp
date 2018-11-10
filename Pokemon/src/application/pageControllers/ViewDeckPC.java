package application.pageControllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	
	private void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int deckId = req.getSession(true).getAttribute("deckId") == null ? -1
				: (int) req.getSession(true).getAttribute("deckId");
		if (deckId < 0) {
			req.setAttribute("message", "You don't have a associate deck");
			req.setAttribute("status", "fail");
			req.getRequestDispatcher("WEB-INF/jsp/failure.jsp").forward(req, res);
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
				req.getRequestDispatcher("WEB-INF/jsp/failure.jsp").forward(req, res);
				return;
			} else {
				req.setAttribute("cards", cards);
				req.setAttribute("status", "success");
				req.getRequestDispatcher("WEB-INF/jsp/viewDeck.jsp").forward(req, res);
				return;
			}
		}
	}


}
