package data.rdg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import data.connection.DbConnectionManager;
import domain.service.SingleAppUniqueIdFactory;

public class CardTypeRDG {
	private int id;
	private String type;
	private String name;
	
	public CardTypeRDG(String type, String name) {
		try {
			this.id = SingleAppUniqueIdFactory.getMaxId("CARDTYPE", "id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.type = type;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		String sql = "INSERT INTO CARDTYPE (id, type, name) VALUES (?, ?, ?);";
		Connection con = DbConnectionManager.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, this.id);
		ps.setString(2, this.type);
		ps.setString(3, this.name);
		int res = ps.executeUpdate();
		ps.close();
		return res;
	}
}
