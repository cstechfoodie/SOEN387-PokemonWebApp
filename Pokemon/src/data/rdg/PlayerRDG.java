package data.rdg;

import java.util.ArrayList;
import java.util.List;

public class PlayerRDG extends RDG{
	
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

	@Override
	public int insert() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PlayerRDG find(long id) {
		// TODO Auto-generated method stub
		return this;
	}
	
	public PlayerRDG find(String username) {
		return this;
	}
	
	public List<PlayerRDG> findAll(){
		return new ArrayList<PlayerRDG>();
	}

}
