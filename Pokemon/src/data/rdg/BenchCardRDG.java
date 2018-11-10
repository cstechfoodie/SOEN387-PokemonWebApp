package data.rdg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.connection.DbConnectionManager;

public class BenchCardRDG {
	
	private int benchId;
	
	private int sequenceId;
	
	private String type;
	private String name;
	

	public int getSequenceId() {
		return sequenceId;
	}


	public void setSequenceId(int sequenceId) {
		this.sequenceId = sequenceId;
	}

	
	public BenchCardRDG() {};

	public BenchCardRDG(int benchId, int sequenceId, String type, String name) {
		this.benchId = benchId;
		this.sequenceId = sequenceId;
		this.type = type;
		this.name = name;
	}
	
	
	public int getBenchId() {
		return benchId;
	}


	public void setBenchId(int benchId) {
		this.benchId = benchId;
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
		String sql = "INSERT INTO BENCHCARD (id, benchId, sequenceId, cardtypeId) VALUES (?, ?, ?, ?);";
		Connection con = DbConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, this.benchId);
		ps.setInt(2, this.sequenceId);
		ps.setString(3, this.type);
		ps.setString(4, this.name);
		int res = ps.executeUpdate();
		ps.close();
		con.close();
		return res;
	}
	


	public int delete() throws SQLException {
		String sql = "DELETE FROM BENCHCARD WHERE benchId = '" + this.benchId + "' AND sequenceId = '" + this.sequenceId + "';";
		Connection con = DbConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		int res = ps.executeUpdate();
		ps.close();
		con.close();
		return res;
	}
	
	public int deleteAll() throws SQLException {
		String sql = "DELETE FROM BENCHCARD WHERE benchId = '" + this.benchId  + "';";
		Connection con = DbConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		int res = ps.executeUpdate();
		ps.close();
		con.close();
		return res;
	}
	
	public static int BenchSize(int id) throws SQLException {
		String sql = "SELECT COUNT(\"benchId\") AS count FROM BENCHCARD WHERE benchId = '" + id + "';";
		Connection con = DbConnectionManager.getConnection();
		ResultSet rs = con.createStatement()
				.executeQuery(sql);
		int count  = rs.next() ? rs.getInt("count") : 0;
		rs.close();
		con.close();
		return count;
	}
	
	//return a list of cards associate with the deckId
	public static ArrayList<BenchCardRDG> viewBench(int id) throws SQLException{
		ArrayList<BenchCardRDG> list = new ArrayList<>();
		String sql = "SELECT * FROM BENCHCARD WHERE benchId = '" + id + "';";
		Connection con = DbConnectionManager.getConnection();
		ResultSet res = con.createStatement().executeQuery(sql);
		BenchCardRDG card = null;
		while (res.next()) {
			card = new BenchCardRDG();
			card.setBenchId(res.getInt("benchId"));
			card.setSequenceId(res.getInt("sequenceId"));
			card.setName(res.getString("name"));
			card.setType(res.getString("type"));
			list.add(card);
		}
		res.close();
		con.close();
		return list;
	}
}
