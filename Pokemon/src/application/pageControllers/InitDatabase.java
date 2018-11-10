package application.pageControllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.connection.DbConnectionManager;

/**
 * Servlet implementation class InitDatabase
 */

public class InitDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitDatabase() {
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
		doGet(request, response);
	}
	
	public void init() throws ServletException{
		Connection con = DbConnectionManager.getConnection();
		try {
			
			//con.createStatement().executeUpdate("CREATE TABLE USER (id int, version int, username varchar(255), password varchar(255), PRIMARY KEY (id));");
//			con.createStatement().executeUpdate("CREATE TABLE CARDTYPE (\n" + 
//					"  `id` INT NOT NULL,\n" + 
//					"  `type` VARCHAR(1) NOT NULL,\n" + 
//					"  `name` VARCHAR(255) NOT NULL,\n" + 
//					"  PRIMARY KEY (`id`));");
			con.createStatement().executeUpdate("CREATE TABLE DECKCARD (\n" + 
					"  `deckId` INT NOT NULL,\n" + 
					"  `sequenceId` INT NOT NULL,\n" + 
					"  `type` VARCHAR(1) NOT NULL,\n" + 
					"  `name` VARCHAR(255) NOT NULL,\n" + 
					"  PRIMARY KEY (`deckId`, `sequenceId`));");
//			con.createStatement().executeUpdate("CREATE TABLE CHALLENGE (\n" + 
//					"  `id` INT NOT NULL,\n" + 
//					"  `version` INT NOT NULL,\n" + 
//					"  `challenger` INT NOT NULL,\n" + 
//					"  `challengee` INT NOT NULL,\n" + 
//					"  `status` INT NOT NULL,\n" + 
//					"  PRIMARY KEY (`id`));");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Database Tables Have Been Initialized.");
	}

}
