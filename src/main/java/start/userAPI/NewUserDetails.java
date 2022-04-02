package start.userAPI;

public class NewUserDetails {
	String email;
	String role;
	String username;
	String password;

	public NewUserDetails() {
	}

	public NewUserDetails(String email, String role, String username, String password) {
		super();
		this.email = email;
		this.role = role;
		this.username = username;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	@Override
	public String toString() {
		return "NewUserDetails : email= " + email + ", role= " + role + ", username= " + username + ", password= "
				+ password;
	}

}
