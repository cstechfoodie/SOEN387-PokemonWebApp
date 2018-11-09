package data.rdg;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.connection.DbConnectionManager;
import domain.service.SingleAppUniqueIdFactory;

public class UserRDG implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;

	private int version;

	private String username;

	private String password;

	public UserRDG() {
	}

	public UserRDG(String username, String password) {
		try {
			this.id = SingleAppUniqueIdFactory.getMaxId("USER", "id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.version = 1;
		this.username = username;
		this.password = password;
	}

	public int insert() throws SQLException {
		String sql = "INSERT INTO USER (id, version, username, password) VALUES (?, ?, ?, ?);";
		Connection con = DbConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, this.id);
		ps.setInt(2, this.version);
		ps.setString(3, this.username);
		ps.setString(4, this.password);
		int res = ps.executeUpdate();
		ps.close();
		return res;
	}

	public int update() throws SQLException {
		String sql = "UPDATE USER" + 
				"SET version = ?, username = ?, password = ?" + 
				"WHERE id = ?;";
		Connection con = DbConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(4, this.id);
		ps.setInt(1, this.version + 1);
		ps.setString(2, this.username);
		ps.setString(3, this.password);
		int res = ps.executeUpdate();
		ps.close();
		return res;
	}

	public int delete() throws SQLException {
		String sql = "DELETE FROM USER WHERE id = '" + this.id + "';";
		Connection con = DbConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		int res = ps.executeUpdate();
		ps.close();
		return res;
	}

	public static UserRDG find(int id) throws SQLException {
		String sql = "SELECT * FROM USER WHERE id = '" + id +"';";
		ResultSet res = DbConnectionManager.getConnection().createStatement().executeQuery(sql);
		UserRDG user = null; 
		while (res.next()) {
			user = new UserRDG();
			user.setId(res.getInt("id"));
			user.setUsername(res.getString("username"));
			user.setPassword(res.getString("password"));
			user.setVersion(res.getInt("version"));
		}
		res.close();
		return user;
	}

	public static UserRDG find(String username) throws SQLException {
		String sql = "SELECT * FROM USER WHERE username = '" + username + "';";
		ResultSet res = DbConnectionManager.getConnection().createStatement().executeQuery(sql);
		UserRDG user = null;
		while (res.next()) {
			user = new UserRDG();
			user.setId(res.getInt("id"));
			user.setUsername(res.getString("username"));
			user.setPassword(res.getString("password"));
			user.setVersion(res.getInt("version"));
		}
		res.close();
		return user;
	}

	public static UserRDG find(String username, String password) throws SQLException {
		String sql = "SELECT * FROM USER WHERE username = '" + username + "' AND password = '" + password +"';";
		ResultSet res = DbConnectionManager.getConnection().createStatement().executeQuery(sql);
		UserRDG user = null;
		while (res.next()) {
			user = new UserRDG();
			user.setId(res.getInt("id"));
			user.setUsername(res.getString("username"));
			user.setPassword(res.getString("password"));
			user.setVersion(res.getInt("version"));
		}
		res.close();
		return user;
	}

	public static ArrayList<UserRDG> findAll() throws SQLException {
		ArrayList<UserRDG> list = new ArrayList<>();
		String sql = "SELECT * FROM USER;";
		ResultSet res = DbConnectionManager.getConnection().createStatement().executeQuery(sql);
		UserRDG user = null;
		while (res.next()) {
			user = new UserRDG();
			user.setId(res.getInt("id"));
			user.setUsername(res.getString("username"));
			user.setPassword(res.getString("password"));
			user.setVersion(res.getInt("version"));
			list.add(user);
		}
		res.close();
		return list;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
