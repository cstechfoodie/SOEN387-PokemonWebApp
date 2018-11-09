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

}
