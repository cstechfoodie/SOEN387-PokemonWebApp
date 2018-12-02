package application.pageControllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.util.URIUtil;
import data.connection.DbConnectionManager;

/**
 * Servlet implementation class InitDatabase
 */

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("REQUEST from FrontController doGet--> " + request.getRequestURI());
		processRequest(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("REQUEST from FrontController doPost--> " + request.getRequestURI());
		processRequest(request, response);
		return;
	}
	
	private void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if(req.getRequestURI().contains("/Player/Register")) {
			RegisterPC dp = new RegisterPC();
			dp.processRequest(req, res);
			return;
		}
		
		else if(req.getRequestURI().contains("/Player/Login")) {
			LoginPC dp = new LoginPC();
			dp.processRequest(req, res);
			return;
		}
		
		else if(req.getRequestURI().contains("/Player/Logout")) {
			LogoutPC dp = new LogoutPC();
			dp.processRequest(req, res);
			return;
		}
		else if(req.getRequestURI().contains("/Deck") && req.getMethod().equals("POST")) {

			UploadDeckPC dp = new UploadDeckPC();
			dp.processRequest(req, res);
			return;
		}
		else if(req.getRequestURI().contains("/Deck") && req.getMethod().equals("GET")) {
			ViewDeckPC dp = new ViewDeckPC();
			if(URIUtil.numbersInUrI(req.getRequestURI()) > 0) {
				dp.viewDeck(req, res);
			} else {
				dp.viewDecks(req, res);
			}
			return;
		}
		
		else if(req.getRequestURI().contains("/Player") && req.getMethod().equals("GET")) {
			ListPlayersPC dp = new ListPlayersPC();
			dp.processRequest(req, res);
			return;
		}
		else if(req.getRequestURI().contains("/Player") && req.getRequestURI().contains("/Challenge") && req.getMethod().equals("POST")) {
				ChallengePlayerPC dp = new ChallengePlayerPC();
				dp.processRequest(req, res);
				return;				
		}
		else if(req.getRequestURI().contains("/Challenge") && req.getMethod().equals("GET")) {
			ListChallengesPC dp = new ListChallengesPC();
			dp.processRequest(req, res);
			return;
		}
		else if(req.getRequestURI().contains("/Challenge") && req.getRequestURI().contains("/Accept") && req.getMethod().equals("POST")) {
			AcceptChallengePC dp = new AcceptChallengePC();
			dp.processRequest(req, res);
			return;
		}
		else if(req.getRequestURI().contains("/Challenge") && req.getRequestURI().contains("/Refuse") && req.getMethod().equals("POST")) {
			RefuseChallengePC dp = new RefuseChallengePC();
			dp.processRequest(req, res);
			return;
		}
		
		else if(req.getRequestURI().contains("/Challenge") && req.getRequestURI().contains("/Withdraw") && req.getMethod().equals("POST")) {
			RefuseChallengePC dp = new RefuseChallengePC();
			dp.processRequest(req, res);
			return;
		}
		else if(req.getRequestURI().contains("/Game") && URIUtil.parseForIdAtEnd(req.getRequestURI()) > 0 && req.getMethod().equals("GET")) {
			ViewBoardPC dp = new ViewBoardPC();
			dp.processRequest(req, res);
			return;
		}
		else if(req.getRequestURI().contains("/Game") && req.getMethod().equals("GET")) {

			ListGamesPC dp = new ListGamesPC();
			dp.processRequest(req, res);
			return;
		}
		
		return;
	}
	
	public void init() throws ServletException{
		Connection con = DbConnectionManager.getConnection();
		try {
			
			con.createStatement().executeUpdate("DROP TABLE USER");
			con.createStatement().executeUpdate("DROP TABLE DECKCARD");
			con.createStatement().executeUpdate("DROP TABLE BENCHCARD");
			con.createStatement().executeUpdate("DROP TABLE HANDCARD");
			con.createStatement().executeUpdate("DROP TABLE CHALLENGE");
			con.createStatement().executeUpdate("DROP TABLE GAME");
			
			con.createStatement().executeUpdate("CREATE TABLE USER (id int, version int, username varchar(255), password varchar(255), status varchar(255), PRIMARY KEY (id));");
//			con.createStatement().executeUpdate("CREATE TABLE CARDTYPE (\n" + 
//					"  `id` INT NOT NULL,\n" + 
//					"  `type` VARCHAR(1) NOT NULL,\n" + 
//					"  `name` VARCHAR(255) NOT NULL,\n" + 
//					"  PRIMARY KEY (`id`));");
			con.createStatement().executeUpdate("CREATE TABLE DECKCARD (\n" + 
					"  `playerId` INT NOT NULL,\n" + 
					"  `deckId` INT NOT NULL,\n" + 
					"  `sequenceId` INT NOT NULL,\n" + 
					"  `type` VARCHAR(1) NOT NULL,\n" + 
					"  `name` VARCHAR(255) NOT NULL,\n" + 
					"  `basic` VARCHAR(255),\n" + 
					"  PRIMARY KEY (`deckId`, `sequenceId`));");
			con.createStatement().executeUpdate("CREATE TABLE BENCHCARD (\n" + 
					"  `benchId` INT NOT NULL,\n" + 
					"  `sequenceId` INT NOT NULL,\n" + 
					"  `type` VARCHAR(1) NOT NULL,\n" + 
					"  `name` VARCHAR(255) NOT NULL,\n" + 
					"  PRIMARY KEY (`benchId`, `sequenceId`));");
			
			con.createStatement().executeUpdate("CREATE TABLE HANDCARD (\n" + 
					"  `handId` INT NOT NULL,\n" + 
					"  `sequenceId` INT NOT NULL,\n" + 
					"  `type` VARCHAR(1) NOT NULL,\n" + 
					"  `name` VARCHAR(255) NOT NULL,\n" + 
					"  PRIMARY KEY (`handId`, `sequenceId`));");
			
//			con.createStatement().executeUpdate("CREATE TABLE PLAYER (\n" + 
//					"  `id` INT NOT NULL,\n" + 
//					"  `version` INT,\n" +
//					"  `status` varchar(255),\n" + 
//					"  `deckId` INT,\n" + 
//					"  `handId` INT,\n" + 
//					"  `benchId` INT,\n" + 
//					"  `gameId` INT,\n" + 
//					"  PRIMARY KEY (`id`));");
			con.createStatement().executeUpdate("CREATE TABLE CHALLENGE (\n" + 
					"  `id` INT NOT NULL,\n" + 
					"  `version` INT,\n" + 
					"  `challenger` INT,\n" + 
					"  `challengee` INT,\n" + 
					"  `status` INT,\n" + 
					"  `deck` INT,\n" + 
					"  PRIMARY KEY (`id`));");
			
			con.createStatement().executeUpdate("CREATE TABLE GAME (\n" + 
					"  `id` INT NOT NULL,\n" + 
					"  `version` INT,\n" + 
					"  `challenger` INT,\n" + 
					"  `challengee` INT,\n" + 
					"  PRIMARY KEY (`id`));");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Database Tables Have Been Initialized.");
	}

}
