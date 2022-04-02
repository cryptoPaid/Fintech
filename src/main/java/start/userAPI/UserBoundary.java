package start.userAPI;

public class UserBoundary {

	private UserID userId;
	private String role;
	private String username;
	private String password;

	public UserBoundary() {
		super();
	}

	public UserBoundary(UserID userId, String role, String username, String password) {
		super();
		this.userId = userId;
		this.role = role;
		this.username = username;
		this.password = password;
	}

	public UserID getUserId() {
		return userId;
	}

	public void setUserId(UserID userId) {
		this.userId = userId;
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
		return "UserBoundary [userId=" + userId + ", role=" + role + ", username=" + username + ", Password=" + password
				+ "]";
	}

}
