package start.userAPI;


import java.util.ArrayList;

import start.data.objects.BlockChain;
import start.data.objects.Transaction;
import start.data.objects.Wallet;

/**
 * @author johny
 *
 */
public class UserBoundary {

	private UserID userId;
	private String role;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
/*
	private BlockChain johnStaCoin;
	private Wallet wallet ;

	private ArrayList<Transaction> pendingTransaction;*/
	public UserBoundary() {
		super();
	}

	public UserBoundary(String username, String password, String email, String role, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.setFirstName(firstName);
        this.lastName = lastName;

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
		return "UserBoundary [userId=" + userId + ", role=" + role + ", username=" + username + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email  + "]";
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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

	

	
}
