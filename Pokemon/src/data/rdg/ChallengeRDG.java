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
	
	private int deck;
	
	private int deck_ee;
	
	public int getDeck() {
		return deck;
	}

	public void setDeck(int deck) {
		this.deck = deck;
	}

	public ChallengeRDG() {};
	
	public ChallengeRDG(int challenger, int challengee, int status, int deck) {
		try {
			this.id = SingleAppUniqueIdFactory.getMaxId("CHALLENGE", "id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.version = 1;
		this.challengee = challengee;
		this.challenger = challenger;
		this.status = status;
		this.deck = deck;
	}

	public int getDeck_ee() {
		return deck_ee;
	}

	public void setDeck_ee(int deck_ee) {
		this.deck_ee = deck_ee;
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
		String sql = "INSERT INTO CHALLENGE (id, version, challenger, challengee, status, deck) VALUES (?, ?, ?, ?, ?, ?);";
		Connection con = DbConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, this.id);
		ps.setInt(2, this.version);
		ps.setInt(3, this.challenger);
		ps.setInt(4, this.challengee);
		ps.setInt(5, this.status);
		ps.setInt(6, this.deck);
		int res = ps.executeUpdate();
		ps.close();
		con.close();
		return res;
	}

	public int update() throws SQLException {
		String sql = "UPDATE CHALLENGE" + 
				"SET version = ?, challenger = ?, challengee = ?, status=?, deck=?" + 
				"WHERE id = ?;";
		Connection con = DbConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(6, this.id);
		ps.setInt(1, this.version + 1);
		ps.setInt(2, this.challenger);
		ps.setInt(3, this.challengee);
		ps.setInt(4, this.status);
		ps.setInt(5, this.deck);
		int res = ps.executeUpdate();
		ps.close();
		con.close();
		return res;
	}

	public int delete() throws SQLException {
		String sql = "DELETE FROM CHALLENGE WHERE id = '" + this.id + "';";
		Connection con = DbConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		int res = ps.executeUpdate();
		ps.close();
		con.close();
		return res;
	}
	
	public static ChallengeRDG find(int id) throws SQLException {
		String sql = "SELECT * FROM CHALLENGE WHERE id = '" + id +"';";
		Connection con = DbConnectionManager.getConnection();
		ResultSet res = con.createStatement().executeQuery(sql);
		ChallengeRDG ch = null; 
		while (res.next()) {
			ch = new ChallengeRDG();
			ch.setId(res.getInt("id"));
			ch.setVersion(res.getInt("version"));
			ch.setChallenger(res.getInt("challenger"));
			ch.setChallengee(res.getInt("challengee"));
			ch.setStatus(res.getInt("status"));
			ch.setDeck(res.getInt("deck"));
			ch.setDeck_ee(res.getInt("deck_ee"));
		}
		res.close();
		con.close();
		return ch;
	}
	
	public static ArrayList<ChallengeRDG> findAll() throws SQLException {
		ArrayList<ChallengeRDG> list = new ArrayList<>();
		String sql = "SELECT * FROM CHALLENGE;";
		Connection con = DbConnectionManager.getConnection();
		ResultSet res = con.createStatement().executeQuery(sql);
		ChallengeRDG ch = null; 
		while (res.next()) {
			ch = new ChallengeRDG();
			ch.setId(res.getInt("id"));
			ch.setVersion(res.getInt("version"));
			ch.setChallenger(res.getInt("challenger"));
			ch.setChallengee(res.getInt("challengee"));
			ch.setStatus(res.getInt("status"));
			ch.setDeck(res.getInt("deck"));
			ch.setDeck_ee(res.getInt("deck_ee"));
			list.add(ch);
		}
		res.close();
		con.close();
		return list;
	}
	
	public synchronized static boolean updateStatus(int id, int version, int status) {
		String sql = "UPDATE CHALLENGE\n" + 
				"SET version = ?, status = ? \n" + 
				"WHERE id = " + id + " AND version = " + version + ";";
		Connection con = DbConnectionManager.getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, version + 1);
			ps.setInt(2, status);
			int res = ps.executeUpdate();
			ps.close();
			con.close();
			
			ChallengeRDG ch = ChallengeRDG.find(id);
			if(ch.getVersion() == version + 1) {
				return true;
			}
		} catch (SQLException e) {
			return false;
		}
		
		return false;
	}
	
	public static boolean challengeTwice(int challenger, int challengee) throws SQLException {
		String sql = "SELECT * FROM CHALLENGE WHERE challenger =" + challenger + " AND challengee=" + challengee +" AND status=0;";
		Connection con = DbConnectionManager.getConnection();
		ResultSet res = con.createStatement().executeQuery(sql);
		boolean isTwice = false;
		if(res.next()) {
			isTwice = true;			
		}
		res.close();
		con.close();
		return isTwice;
	}
	
	public int updateDeck_ee(int deckId) throws SQLException {
		String sql = "UPDATE CHALLENGE " + 
				"SET deck_ee=? " + 
				"WHERE id = ?;";
		Connection con = DbConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(2, this.id);
		ps.setInt(1, deckId);
		int res = ps.executeUpdate();
		ps.close();
		con.close();
		return res;
	}
}
