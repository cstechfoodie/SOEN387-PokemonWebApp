package data.rdg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.connection.DbConnectionManager;

public class HandCardRDG {
	private int id;
	
	private int deckId;
	
	private int sequenceId;
	
	private int cardtypeId;
	
	public int getDeckId() {
		return deckId;
	}


	public void setDeckId(int deckId) {
		this.deckId = deckId;
	}


	public int getSequenceId() {
		return sequenceId;
	}


	public void setSequenceId(int sequenceId) {
		this.sequenceId = sequenceId;
	}



	public int getCardtypeId() {
		return cardtypeId;
	}


	public void setCardtypeId(int cardtypeId) {
		this.cardtypeId = cardtypeId;
	}
	
	public int getId() {
		return id;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	
	public HandCardRDG() {};

	public HandCardRDG(int id, int deckId, int sequenceId, int cardtypeId) {
		this.id = id;
		this.deckId = deckId;
		this.sequenceId = sequenceId;
		this.cardtypeId = cardtypeId;
	}
	
	
	public int insert() throws SQLException {
		String sql = "INSERT INTO HANDCARD (id, deckId, sequenceId, cardtypeId) VALUES (?, ?, ?, ?);";
		Connection con = DbConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, this.id);
		ps.setInt(2, this.deckId);
		ps.setInt(3, this.sequenceId);
		ps.setInt(4, this.cardtypeId);
		int res = ps.executeUpdate();
		ps.close();
		return res;
	}
	


	public int delete() throws SQLException {
		String sql = "DELETE FROM HANDCARD WHERE deckId = '" + this.deckId + "' AND sequenceId = '" + this.sequenceId + "';";
		Connection con = DbConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		int res = ps.executeUpdate();
		ps.close();
		return res;
	}
	
	public int deleteAll() throws SQLException {
		String sql = "DELETE FROM HANDCARD WHERE id = '" + this.id  + "';";
		Connection con = DbConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		int res = ps.executeUpdate();
		ps.close();
		return res;
	}
	
	public static int handSize(int id) throws SQLException {
		String sql = "SELECT COUNT(" + id + ") AS count FROM HANDCARD WHERE id = '" + id + "';";
		ResultSet rs = DbConnectionManager.getConnection().createStatement()
				.executeQuery(sql);
		int count  = rs.next() ? rs.getInt("count") : 0;
		rs.close();
		return count;
	}
	
	//return a list of cards associate with the deckId
	public static List<HandCardRDG> viewHand(int id) throws SQLException{
		ArrayList<HandCardRDG> list = new ArrayList<>();
		String sql = "SELECT * FROM HANDCARD WHERE id = '" + id + "';";
		ResultSet res = DbConnectionManager.getConnection().createStatement().executeQuery(sql);
		HandCardRDG card = null;
		while (res.next()) {
			card = new HandCardRDG();
			card.setId(res.getInt("id"));
			card.setDeckId(res.getInt("deckId"));
			card.setSequenceId(res.getInt("sequenceId"));
			card.setCardtypeId(res.getInt("cardtypeId"));
			list.add(card);
		}
		res.close();
		return list;
	}
}
