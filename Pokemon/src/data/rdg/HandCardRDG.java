package data.rdg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.connection.DbConnectionManager;

public class HandCardRDG {
	
	private int handId;
	
	private int sequenceId;
	
	private String type;
	private String name;
	

	public int getSequenceId() {
		return sequenceId;
	}


	public void setSequenceId(int sequenceId) {
		this.sequenceId = sequenceId;
	}

	
	public HandCardRDG() {};

	public HandCardRDG(int handId, int sequenceId, String type, String name) {
		this.handId = handId;
		this.sequenceId = sequenceId;
		this.type = type;
		this.name = name;
	}
	
	
	public int getHandId() {
		return handId;
	}


	public void setHandId(int handId) {
		this.handId = handId;
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


	public int insert() throws SQLException {
		String sql = "INSERT INTO HANDCARD (handId, sequenceId, type, name) VALUES (?, ?, ?, ?);";
		Connection con = DbConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, this.handId);
		ps.setInt(2, this.sequenceId);
		ps.setString(3, this.type);
		ps.setString(4, this.name);
		int res = ps.executeUpdate();
		ps.close();
		con.close();
		return res;
	}
	


	public int delete() throws SQLException {
		String sql = "DELETE FROM HANDCARD WHERE handId = '" + this.handId + "' AND sequenceId = '" + this.sequenceId + "';";
		Connection con = DbConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		int res = ps.executeUpdate();
		ps.close();
		con.close();
		return res;
	}
	
	public int deleteAll() throws SQLException {
		String sql = "DELETE FROM HANDCARD WHERE handId = '" + this.handId  + "';";
		Connection con = DbConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		int res = ps.executeUpdate();
		ps.close();
		con.close();
		return res;
	}
	
	public static int handSize(int handId) throws SQLException {
		String sql = "SELECT COUNT(\"handId\") AS count FROM HANDCARD WHERE handId = '" + handId + "';";
		Connection con = DbConnectionManager.getConnection();
		ResultSet rs = con.createStatement()
				.executeQuery(sql);
		int count  = rs.next() ? rs.getInt("count") : 0;
		rs.close();
		con.close();
		return count;
	}
	
	//return a list of cards associate with the deckId
	public static ArrayList<HandCardRDG> viewHand(int id) throws SQLException{
		ArrayList<HandCardRDG> list = new ArrayList<>();
		String sql = "SELECT * FROM HANDCARD WHERE handId = '" + id + "' ORDER BY sequenceId ASC;";
		Connection con = DbConnectionManager.getConnection();
		ResultSet res = con.createStatement().executeQuery(sql);
		HandCardRDG card = null;
		while (res.next()) {
			card = new HandCardRDG();
			card.setHandId(res.getInt("handId"));
			card.setSequenceId(res.getInt("sequenceId"));
			card.setName(res.getString("name"));
			card.setType(res.getString("type"));
			list.add(card);
		}
		res.close();
		con.close();
		return list;
	}
	
	public static ArrayList<Integer> viewHandIds(int id) throws SQLException{
		ArrayList<Integer> list = new ArrayList<>();
		String sql = "SELECT * FROM HANDCARD WHERE handId = '" + id + "' ORDER BY sequenceId ASC;";
		Connection con = DbConnectionManager.getConnection();
		ResultSet res = con.createStatement().executeQuery(sql);
		while (res.next()) {
			list.add(res.getInt("sequenceId"));
		}
		res.close();
		con.close();
		return list;
	}
	
	public static HandCardRDG find(int handId, int sequenceId) throws SQLException {
		String sql = "SELECT * FROM HANDCARD WHERE handId = '" + handId + "' AND sequenceId = '" + sequenceId + "';";
		Connection con = DbConnectionManager.getConnection();
		ResultSet res = con.createStatement().executeQuery(sql);
		HandCardRDG card = null;
		while (res.next()) {
			card = new HandCardRDG();
			card.setHandId(res.getInt("handId"));
			card.setSequenceId(res.getInt("sequenceId"));
			card.setName(res.getString("name"));
			card.setType(res.getString("type"));
		}
		res.close();
		con.close();
		return card;
	}

}
