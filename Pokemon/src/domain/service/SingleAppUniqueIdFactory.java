package domain.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import data.connection.DbConnectionManager;

public class SingleAppUniqueIdFactory {

	private static Hashtable<String, Integer> IDs = new Hashtable<String, Integer>();

	public synchronized static int getMaxId(String table) throws SQLException {
		Integer max_id = IDs.get(table + ".id");
		if (max_id == null) {
			ResultSet rs = DbConnectionManager.getConnection().createStatement()
					.executeQuery("SELECT max(id) AS maximum FROM " + table);
			max_id = rs.next() ? rs.getInt("maximum") : -1;
			rs.close();
		}
		IDs.put(table + ".id", ++max_id);
		return max_id;
	}
}
