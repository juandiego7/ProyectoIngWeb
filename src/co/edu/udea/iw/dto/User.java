package co.edu.udea.iw.dto;

/**
 * @author Juan Diego
 *
 */
public class User {
		
	private String username;
	private String typeId;
	private String numberId;
	private String name;
	private String lastName;
	private String email;
	private String password;
	private String role;
	private User manager;
	
	public User() {
		
	}
	/**
	 * @param username
	 * @param typeId
	 * @param numberId
	 * @param name
	 * @param lastName
	 * @param email
	 * @param password
	 * @param role
	 * @param manager
	 */
	public User(String username, String typeId, String numberId, String name, String lastName, String email,
			String password, String role, User manager) {
		super();
		this.username = username;
		this.typeId = typeId;
		this.numberId = numberId;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
		this.manager = manager;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getNumberId() {
		return numberId;
	}
	public void setNumberId(String numberId) {
		this.numberId = numberId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public User getManager() {
		return manager;
	}
	public void setManager(User manager) {
		this.manager = manager;
	}
	
}
