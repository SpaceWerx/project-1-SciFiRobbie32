package Model;

public class Users {
	private int id;
	private String userName;
	private String password;
	private Roles role;
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Roles getRole() {
		return role;
	}
	public void setRole(Roles role) {
		this.role = role;
	}
	public Users(int id, String userName, String password, Roles role) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.role = role;
	}
	
}