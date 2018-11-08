package domain.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import data.connection.DbConnectionManager;

public class SingleAppUniqueIdFactory {

	private static Hashtable<String, Integer> IDs = new Hashtable<String, Integer>();

	public synchronized static int getMaxId(String table, String field) throws SQLException {
		Integer max_id = IDs.get(table + "." + field);
		if (max_id == null) {
			ResultSet rs = DbConnectionManager.getConnection().createStatement()
					.executeQuery("SELECT max("+ field + ") AS maximum FROM " + table);
			max_id = rs.next() ? rs.getInt("maximum") : -1;
			rs.close();
		}
		IDs.put(table + "." + field, ++max_id);
		return max_id;
	}
	
	public synchronized static void reset(String table, String field) throws SQLException {
		IDs.put(table + "." + field, -1);
	}
	
	public synchronized static int getConstantId(String table, String field) throws SQLException {
		Integer max_id = IDs.get(table + "." + field);
		if (max_id == null) {
			max_id = 1;
			IDs.put(table + "." + field, max_id);
		}
		return max_id;
	}
	
	
}
