package data.rdg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import data.connection.DbConnectionManager;

public class GameRDG {
	private int id;
	
	private int challenger;
	
	private String challenger_status;
	
	private int challengee;
	
	private String challengee_status;
	
	private int version;
	
	private int current;
	
	private int[] players;
	
	public GameRDG() {};
	
	public GameRDG(int id, int challenger, int challengee, int version) {
		this.id = id;
		this.challenger = challenger;
		this.challenger_status = "playing";
		this.challengee = challengee;
		this.challengee_status = "playing";
		this.version = version;
		this.current = challenger;
		this.players = new int[] {challenger, challengee};
	}

	public String getChallenger_status() {
		return challenger_status;
	}

	public void setChallenger_status(String challenger_status) {
		this.challenger_status = challenger_status;
	}

	public String getChallengee_status() {
		return challengee_status;
	}

	public void setChallengee_status(String challengee_status) {
		this.challengee_status = challengee_status;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getChallenger() {
		return challenger;
	}

	public void setChallenger(int challenger) {
		this.challenger = challenger;
	}

	public int getChallengee() {
		return challengee;
	}

	public void setChallengee(int challengee) {
		this.challengee = challengee;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	public int[] getPlayers() {
		return players;
	}

	public void setPlayers(int[] players) {
		this.players = players;
	}

	public int insert() throws SQLException {
		String sql = "INSERT INTO GAME (id, version, challenger, challengee, challenger_status, challengee_status, current) VALUES (?, ?, ?, ?, ?, ?, ?);";
		Connection con = DbConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, this.id);
		ps.setInt(2, this.version);
		ps.setInt(3, this.challenger);
		ps.setInt(4, this.challengee);
		ps.setString(5, this.challenger_status);
		ps.setString(6, this.challengee_status);
		ps.setInt(7, this.current);
		int res = ps.executeUpdate();
		ps.close();
		con.close();
		return res;
	}

	public int update() throws SQLException {
		String sql = "UPDATE GAME" + 
				"SET version = ?, challenger_status = ?, challengee_status = ?" + 
				"WHERE id = ?;";
		Connection con = DbConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(4, this.id);
		ps.setInt(1, this.version + 1);
		ps.setString(2, this.challenger_status);
		ps.setString(3, this.challengee_status);
		int res = ps.executeUpdate();
		ps.close();
		con.close();
		return res;
	}

	public int delete() throws SQLException {
		String sql = "DELETE FROM GAME WHERE id = '" + this.id + "';";
		Connection con = DbConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		int res = ps.executeUpdate();
		ps.close();
		con.close();
		return res;
	}
	
	public static GameRDG find(int id) throws SQLException {
		String sql = "SELECT * FROM GAME WHERE id = '" + id +"';";
		Connection con = DbConnectionManager.getConnection();
		ResultSet res = con.createStatement().executeQuery(sql);
		GameRDG g = null; 
		while (res.next()) {
			g = new GameRDG();
			g.setId(res.getInt("id"));
			g.setVersion(res.getInt("version"));
			g.setChallenger(res.getInt("challenger"));
			g.setChallengee(res.getInt("challengee"));
			g.setChallengee_status(res.getString("challengee_status"));
			g.setChallenger_status(res.getString("challenger_status"));
			g.setCurrent(res.getInt("current"));
			g.setPlayers(new int[] {res.getInt("challenger"), res.getInt("challengee")});
		}
		res.close();
		con.close();
		return g;
	}
	
	public static ArrayList<GameRDG> findAll() throws SQLException {
		ArrayList<GameRDG> list = new ArrayList<>();
		String sql = "SELECT * FROM GAME;";
		Connection con = DbConnectionManager.getConnection();
		ResultSet res = con.createStatement().executeQuery(sql);
		GameRDG g = null; 
		while (res.next()) {
			g = new GameRDG();
			g.setId(res.getInt("id"));
			g.setVersion(res.getInt("version"));
			g.setChallenger(res.getInt("challenger"));
			g.setChallenger(res.getInt("challengee"));
			g.setChallengee_status(res.getString("challengee_status"));
			g.setChallenger_status(res.getString("challenger_status"));
			g.setCurrent(res.getInt("current"));
			g.setPlayers(new int[] {res.getInt("challenger"), res.getInt("challengee")});
			list.add(g);
		}
		res.close();
		con.close();
		return list;
	}
	
	public int updateStatus(int playerId, String status) throws SQLException {
		String sql = "UPDATE GAME " + 
				"SET version=?, challenger_status=?, challengee_status=? " + 
				"WHERE id=?;";
		Connection con = DbConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(4, this.id);
		ps.setInt(1, this.version + 1);
		if(playerId == this.challenger) {
			ps.setString(2, status);
			ps.setString(3, this.challengee_status);
		} else {
			ps.setString(2, this.challenger_status);
			ps.setString(3, status);			
		}
		int res = ps.executeUpdate();
		ps.close();
		con.close();
		return res;
	}
}
