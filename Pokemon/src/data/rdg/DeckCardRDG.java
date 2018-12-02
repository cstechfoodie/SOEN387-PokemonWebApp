package data.rdg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.connection.DbConnectionManager;
import domain.service.SingleAppUniqueIdFactory;

public class DeckCardRDG {
	private int playerId;
	private int deckId;
	private int sequenceId;
	private String type;
	private String name;
	private String basic;

	public int getPlayerId() {
		return playerId;
	}


	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}


	public String getBasic() {
		return basic;
	}


	public void setBasic(String basic) {
		this.basic = basic;
	}


	public int getDeckId() {
		return deckId;
	}


	public void setDeckId(int deckId) {
		this.deckId = deckId;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getSequenceId() {
		return sequenceId;
	}


	public void setSequenceId(int sequenceId) {
		this.sequenceId = sequenceId;
	}

	
	public DeckCardRDG() {};

	public DeckCardRDG(int deckId, int sequenceId, String type, String name, String basic) {
		this.deckId = deckId;
		this.sequenceId = sequenceId;
		this.type = type;
		this.name = name;
		this.basic = basic;
	}
	
	
	public int insert() throws SQLException {
		String sql = "INSERT INTO DECKCARD (playerId, deckId, sequenceId, type, name, basic) VALUES (?, ?, ?, ?, ?, ?);";
		Connection con = DbConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, this.playerId);
		ps.setInt(2, this.deckId);
		ps.setInt(3, this.sequenceId);
		ps.setString(4, this.type);
		ps.setString(5, this.name);
		ps.setString(6, this.basic);
		int res = ps.executeUpdate();
		ps.close();
		con.close();
		return res;
	}
	
	public int delete() throws SQLException {
		String sql = "DELETE FROM DECKCARD WHERE deckId = '" + this.deckId + "' AND sequenceId = '" + this.sequenceId + "';";
		Connection con = DbConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		int res = ps.executeUpdate();
		ps.close();
		con.close();
		return res;
	}
	
	//return a list of cards associate with the deckId
	public static ArrayList<DeckCardRDG> viewDeck(int deckId) throws SQLException{
		ArrayList<DeckCardRDG> list = new ArrayList<>();
		String sql = "SELECT * FROM DECKCARD WHERE deckId = '" + deckId + "' ORDER BY sequenceId ASC;";
		Connection con = DbConnectionManager.getConnection();
		ResultSet res = con.createStatement().executeQuery(sql);
		DeckCardRDG card = null;
		while (res.next()) {
			card = new DeckCardRDG();
			card.setDeckId(res.getInt("deckId"));
			card.setSequenceId(res.getInt("sequenceId"));
			card.setType(res.getString("type"));
			card.setName(res.getString("name"));
			card.setBasic(res.getString("basic"));
			list.add(card);
		}
		res.close();
		con.close();
		return list;
	}
	
	public static int deckSize(int deckId) throws SQLException {
		int size = viewDeck(deckId).size();
		return size;
	}
	
	public static ArrayList<Integer> viewDecks() throws SQLException{
		ArrayList<Integer> list = new ArrayList<>();
		String sql = "SELECT DISTINCT deckId FROM DECKCARD;";
		Connection con = DbConnectionManager.getConnection();
		ResultSet res = con.createStatement().executeQuery(sql);
		while (res.next()) {
			list.add(res.getInt("deckId"));
		}
		res.close();
		con.close();
		return list;
	}
	
	public static ArrayList<Integer> viewDecksByPlayer(int playerId) throws SQLException{
		ArrayList<Integer> list = new ArrayList<>();
		String sql = "SELECT DISTINCT deckId FROM DECKCARD WHERE playerId = '" + playerId + "';";
		Connection con = DbConnectionManager.getConnection();
		ResultSet res = con.createStatement().executeQuery(sql);
		while (res.next()) {
			list.add(res.getInt("deckId"));
		}
		res.close();
		con.close();
		return list;
	}
}
