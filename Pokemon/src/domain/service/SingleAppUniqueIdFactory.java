package domain.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class SingleAppUniqueIdFactory {

	private static Hashtable<String, Long> IDs = new Hashtable<String, Long>();

	public synchronized long getId(String table, String field) throws SQLException {
		Long max_id = IDs.get(table + "." + field);
		if (max_id == null) {
			
			ResultSet rs = DbRegistry.getDbConnection().createStatement()
					.executeQuery("SELECT max(" + field + ") AS maximum FROM " + DbRegistry.getTablePrefix() + table);
			max_id = rs.next() ? rs.getLong("maximum") : 1;
			rs.close();
		}
		IDs.put(table + "." + field, ++max_id);
		return max_id;
	}
}
