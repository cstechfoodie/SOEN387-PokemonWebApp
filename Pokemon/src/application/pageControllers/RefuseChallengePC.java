package application.pageControllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.util.URIUtil;
import data.rdg.ChallengeRDG;

/**
 * Servlet implementation class RefuseChallengePC
 */
@WebServlet("/RefuseChallenge")
public class RefuseChallengePC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RefuseChallengePC() {
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
		int challengeId = URIUtil.parseForIdInBeteewn(req.getRequestURI());
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

		boolean success = false;
		
		if(ch.getChallenger() == id) {
			success = ChallengeRDG.updateStatus(ch.getId(), version, 2);
			if(success) {
				req.setAttribute("message", "User with id =  " + ch.getChallenger() + " has successfully withdrew from itw own challenge.");
				req.setAttribute("status", "success");
				req.getRequestDispatcher("/WEB-INF/jsp/failure.jsp").forward(req, res);
				return;
			}
			else {
				//lost update
				req.setAttribute("message", "Suspected lost update");
				req.setAttribute("status", "fail");
				req.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(req, res);
				return;
			}
			
		} else if (ch.getChallengee() == id) {
			success = ChallengeRDG.updateStatus(ch.getId(), version, 1); 
			
			if(success) {
				req.setAttribute("message", "User with id =  " + ch.getChallengee() + " has successfully refused from itw own challenge.");
				req.setAttribute("status", "success");
				req.getRequestDispatcher("/WEB-INF/jsp/success.jsp").forward(req, res);
				return;	
			}
			else {
				//lost update
				req.setAttribute("message", "Suspected lost update");
				req.setAttribute("status", "fail");
				req.getRequestDispatcher("/WEB-INF/jsp/failure.jsp").forward(req, res);
				return;
			}
		} else {
			req.setAttribute("message", "Can't refuse others' challenge.");
			req.setAttribute("status", "fail");
			req.getRequestDispatcher("/WEB-INF/jsp/failure.jsp").forward(req, res);
			return;
		}

		

		
	
	}

}
