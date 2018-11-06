package data.rdg;

import java.util.ArrayList;
import java.util.List;

public class UserRDG extends RDG{

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
	public UserRDG find(long id) {
		// TODO Auto-generated method stub
		return this;
	}
	
	public UserRDG find(String username) {
		return this;
	}
	
	public UserRDG find(String username, String password) {
		return this;
	}
	
	public List<UserRDG> findAll(){
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
