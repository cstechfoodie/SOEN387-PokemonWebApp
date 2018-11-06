package data.rdg;

import java.util.ArrayList;
import java.util.List;

public class PlayerRDG{
	
	private int id;
	
	private int version;
	
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

	public static int insert() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int update() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int delete() {
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
	
	public static List<PlayerRDG> findAll(){
		return new ArrayList<PlayerRDG>();
	}

}
