package data.rdg;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.connection.DbConnectionManager;

public class PlayerRDG{
	
	private int id;
	
	private int version;
	
	private String status;
	
	private int handSize;
	
	private int deckSize;
	
	private int discardSize;
	
	
	
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private String username;

	public int insert() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int update() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delete() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static PlayerRDG find(long id) {
		// TODO Auto-generated method stub
		return new PlayerRDG();
	}
	
	public static PlayerRDG find(String username) {
		return new PlayerRDG();
	}
	
	public static List<PlayerRDG> findAll() throws SQLException {
//		ArrayList<PlayerRDG> list = new ArrayList<>();
//		String sql = "SELECT id, username, username, version FROM User;";
//		ResultSet res = DbConnectionManager.getConnection().createStatement().executeQuery(sql);
//		PlayerRDG user = null;
//		while (res.next()) {
//			user = new PlayerRDG();
//			user.setId(res.getInt("id"));
//			user.setUsername(res.getString("username"));
//			user.setVersion(res.getInt("version"));
//			list.add(user);
//		}
//		res.close();
//		return list;
	}

}
