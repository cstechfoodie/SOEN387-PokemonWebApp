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
import data.rdg.UserRDG;
import domain.model.Deck;
import domain.service.SingleAppUniqueIdFactory;

/**
 * Servlet implementation class UploadDeckPC
 */
@WebServlet("/UploadDeck")
public class UploadDeckPC extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadDeckPC() {
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

	private void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int id = req.getSession(true).getAttribute("userid") == null ? -1
				: (int) req.getSession(true).getAttribute("userid");
		boolean isSuccessful = false;
		if (id < 0) {
			req.setAttribute("message", "You have not successfully logged in.");
			req.setAttribute("status", "fail");
			req.getRequestDispatcher("WEB-INF/jsp/failure.jsp").forward(req, res);
			return;
		} else {
			String deck = req.getParameter("deck");
			String[] deckCards = deck.split("\n");
			if(deckCards.length > 40) {
				req.setAttribute("message", "Deck size is too large (>40)");
				req.setAttribute("status", "fail");
				req.getRequestDispatcher("WEB-INF/jsp/failure.jsp").forward(req, res);
				return;
			} else if(deckCards.length < 40) {
				req.setAttribute("message", "Deck size is too small (<40)");
				req.setAttribute("status", "fail");
				req.getRequestDispatcher("WEB-INF/jsp/failure.jsp").forward(req, res);
				return;
			} else {
				Deck d = new Deck();
				d.setId(id);
				req.getSession().setAttribute("deckId", d.getId());
				DeckCardRDG card = null;
				for(int i = 1; i <= 40; i++) {
					card = new DeckCardRDG();
					String[] each = deckCards[i-1].split(" ");
					card.setDeckId(d.getId());
					card.setSequenceId(i);
					card.setType(each[0].trim());
					String name = each[1].trim();
					card.setName(name.substring(1, name.length()-1));
					d.getCards().add(card);				
				}
				isSuccessful = d.uploadDeck();
			}
			if (!isSuccessful) {
				req.setAttribute("message", "Upload Deck failed");
				req.setAttribute("status", "fail");
				req.getRequestDispatcher("WEB-INF/jsp/failure.jsp").forward(req, res);
				return;
			} else {
				req.setAttribute("message", "Upload Deck Succeed");
				req.setAttribute("status", "success");
				req.getRequestDispatcher("WEB-INF/jsp/success.jsp").forward(req, res);
				return;
			}
		}
	}

}
