package data.rdg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import data.connection.DbConnectionManager;

public class PlayerRDG{
	
	private int id;
	
	private int version;
	
	private String status;
	
	private int handId;

	private int deckId;
	
	private int benchId;
	
	private int gameId;
	
	public PlayerRDG() {};
	
	public PlayerRDG(int id) {
		this.id = id;
	}


	public int insert() throws SQLException {
		String sql = "INSERT INTO PLAYER (id, version, status, deckId, handId, benchId, gameId) VALUES (?, ?, ?, ?, ?, ?, ?);";
		Connection con = DbConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, this.id);
		ps.setInt(2, this.version);
		ps.setString(3, this.status);
		ps.setInt(4, this.deckId);
		ps.setInt(5, this.handId);
		ps.setInt(6, this.benchId);
		ps.setInt(7, this.gameId);
		int res = ps.executeUpdate();
		ps.close();
		con.close();
		return res;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getHandId() {
		return handId;
	}

	public void setHandId(int handId) {
		this.handId = handId;
	}

	public int getDeckId() {
		return deckId;
	}

	public void setDeckId(int deckId) {
		this.deckId = deckId;
	}

	public int getBenchId() {
		return benchId;
	}

	public void setBenchId(int benchId) {
		this.benchId = benchId;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public int update() throws SQLException {
		String sql = "UPDATE PLAYER" + 
				"SET version = ?, status = ?, deckId = ?, handId = ?, benchId = ?, gameId = ?" + 
				"WHERE id = ?;";
		Connection con = DbConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(7, this.id);
		ps.setInt(1, this.version);
		ps.setString(2, this.status);
		ps.setInt(3, this.deckId);
		ps.setInt(4, this.handId);
		ps.setInt(5, this.benchId);
		ps.setInt(6, this.gameId);
		int res = ps.executeUpdate();
		ps.close();
		con.close();
		return res;
	}

	public int delete() throws SQLException {
		String sql = "DELETE FROM PLAYER WHERE id = '" + this.id + "';";
		Connection con = DbConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		int res = ps.executeUpdate();
		ps.close();
		con.close();
		return res;
	}

	public static PlayerRDG find(long id) throws SQLException {
		String sql = "SELECT * FROM PLAYER WHERE id = '" + id +"';";
		Connection con = DbConnectionManager.getConnection();
		ResultSet res = con.createStatement().executeQuery(sql);
		PlayerRDG player = null; 
		while (res.next()) {
			player = new PlayerRDG();
			player.setId(res.getInt("id"));
			player.setStatus(res.getString("status"));
			player.setDeckId(res.getInt("deckId"));
			player.setVersion(res.getInt("version"));
			player.setHandId(res.getInt("handId"));
			player.setBenchId(res.getInt("benchId"));
			player.setGameId(res.getInt("gameId"));
		}
		res.close();
		con.close();
		return player;
	}
}
