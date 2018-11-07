package data.rdg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class UserRDG {

	private static int counter = 1;

	private int id;

	private int version;

	private String username;

	private String password;

	public UserRDG() {
		id = counter;
		counter++;
	}

	public UserRDG(String username, String password) {
		id = counter;
		counter++;
		this.username = username;
		this.password = password;
	}

	public static int insert() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int update() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delete() throws SQLException {
		String sql = "DELETE FROM User WHERE id = " + this.id;
		Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		int res = ps.executeUpdate();
		ps.close();
		return res;
	}

	public static UserRDG find(long id) throws SQLException {
		String sql = "SELECT id, username, username, version FROM User WHERE id = " + id;
		ResultSet res = DbRegistry.getDbConnection().createStatement().executeQuery(sql);
		UserRDG user = new UserRDG();
		while (res.next()) {
			user.setId(res.getInt("id"));
			user.setUsername(res.getString("username"));
			user.setPassword(res.getString("username"));
			user.setVersion(res.getInt("version"));
		}
		res.close();
		return user;
	}

	public static UserRDG find(String username) throws SQLException {
		String sql = "SELECT id, username, username, version FROM User WHERE username = " + username;
		ResultSet res = DbRegistry.getDbConnection().createStatement().executeQuery(sql);
		UserRDG user = new UserRDG();
		while (res.next()) {
			user.setId(res.getInt("id"));
			user.setUsername(res.getString("username"));
			user.setPassword(res.getString("username"));
			user.setVersion(res.getInt("version"));
		}
		res.close();
		return user;
	}

	public static UserRDG find(String username, String password) throws SQLException {
		String sql = "SELECT id, username, username, version FROM User WHERE username = " + username + "password = " + password;
		ResultSet res = DbRegistry.getDbConnection().createStatement().executeQuery(sql);
		UserRDG user = new UserRDG();
		while (res.next()) {
			user.setId(res.getInt("id"));
			user.setUsername(res.getString("username"));
			user.setPassword(res.getString("username"));
			user.setVersion(res.getInt("version"));
		}
		res.close();
		return user;
	}

	public static List<UserRDG> findAll() throws SQLException {
		ArrayList<UserRDG> list = new ArrayList<>();
		String sql = "SELECT id, username, username, version FROM User";
		ResultSet res = DbRegistry.getDbConnection().createStatement().executeQuery(sql);
		while (res.next()) {
			UserRDG user = new UserRDG();
			user.setId(res.getInt("id"));
			user.setUsername(res.getString("username"));
			user.setPassword(res.getString("username"));
			user.setVersion(res.getInt("version"));
			list.add(user);
		}
		res.close();
		return new ArrayList<UserRDG>();
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
