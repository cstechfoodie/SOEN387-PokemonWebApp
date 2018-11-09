package data.rdg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.connection.DbConnectionManager;
import domain.service.SingleAppUniqueIdFactory;

public class ChallengeRDG {
	private int id;
	
	private int version;
	
	private int challenger;
	
	private int challengee;
	
	private int status;
	
	public ChallengeRDG() {};
	
	public ChallengeRDG(int challenger, int challengee, int status) {
		try {
			this.id = SingleAppUniqueIdFactory.getMaxId("CHALLENGE", "id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.version = 1;
		this.challengee = challengee;
		this.challenger = challenger;
		this.status = status;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public int insert() throws SQLException {
		String sql = "INSERT INTO CHALLENGE (id, version, challenger, challengee, status) VALUES (?, ?, ?, ?, ?);";
		Connection con = DbConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, this.id);
		ps.setInt(2, this.version);
		ps.setInt(3, this.challenger);
		ps.setInt(4, this.challengee);
		ps.setInt(5, this.status);
		int res = ps.executeUpdate();
		ps.close();
		return res;
	}

	public int update() throws SQLException {
		String sql = "UPDATE CHALLENGE" + 
				"SET version = ?, challenger = ?, challengee = ?, status=?" + 
				"WHERE id = ?;";
		Connection con = DbConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(5, this.id);
		ps.setInt(1, this.version + 1);
		ps.setInt(2, this.challenger);
		ps.setInt(3, this.challengee);
		ps.setInt(4, this.status);
		int res = ps.executeUpdate();
		ps.close();
		return res;
	}

	public int delete() throws SQLException {
		String sql = "DELETE FROM CHALLENGE WHERE id = '" + this.id + "';";
		Connection con = DbConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		int res = ps.executeUpdate();
		ps.close();
		return res;
	}
	
	public static ChallengeRDG find(int id) throws SQLException {
		String sql = "SELECT * FROM CHALLENGE WHERE id = '" + id +"';";
		ResultSet res = DbConnectionManager.getConnection().createStatement().executeQuery(sql);
		ChallengeRDG ch = null; 
		while (res.next()) {
			ch = new ChallengeRDG();
			ch.setId(res.getInt("id"));
			ch.setVersion(res.getInt("version"));
			ch.setChallenger(res.getInt("challenger"));
			ch.setChallenger(res.getInt("challengee"));
			ch.setStatus(res.getInt("status"));
		}
		res.close();
		return ch;
	}
	
	public static ArrayList<ChallengeRDG> findAll() throws SQLException {
		ArrayList<ChallengeRDG> list = new ArrayList<>();
		String sql = "SELECT * FROM CHALLENGE;";
		ResultSet res = DbConnectionManager.getConnection().createStatement().executeQuery(sql);
		ChallengeRDG ch = null; 
		while (res.next()) {
			ch = new ChallengeRDG();
			ch.setId(res.getInt("id"));
			ch.setVersion(res.getInt("version"));
			ch.setChallenger(res.getInt("challenger"));
			ch.setChallenger(res.getInt("challengee"));
			ch.setStatus(res.getInt("status"));
			list.add(ch);
		}
		res.close();
		return list;
	}
}
