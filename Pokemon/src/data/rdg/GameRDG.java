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
	
	private int challengee;
	
	private int version;

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
	
	public int insert() throws SQLException {
		String sql = "INSERT INTO GAME (id, version, challenger, challengee) VALUES (?, ?, ?, ?);";
		Connection con = DbConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, this.id);
		ps.setInt(2, this.version);
		ps.setInt(3, this.challenger);
		ps.setInt(4, this.challengee);
		int res = ps.executeUpdate();
		ps.close();
		con.close();
		return res;
	}

	public int update() throws SQLException {
		String sql = "UPDATE GAME" + 
				"SET version = ?, challenger = ?, challengee = ?" + 
				"WHERE id = ?;";
		Connection con = DbConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(5, this.id);
		ps.setInt(1, this.version + 1);
		ps.setInt(2, this.challenger);
		ps.setInt(3, this.challengee);
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
			g.setChallenger(res.getInt("challengee"));
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
			list.add(g);
		}
		res.close();
		con.close();
		return list;
	}
}
