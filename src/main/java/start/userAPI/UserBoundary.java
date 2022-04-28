package start.userAPI;

import java.security.PrivateKey;
import java.security.PublicKey;
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
	private BlockChain johnStaCoin;
	private Wallet wallet ;

	private ArrayList<Transaction> pendingTransaction;
	public UserBoundary() {
		super();
	}

	public UserBoundary(String username, String password, String email, String role, Wallet wallet, String firstName, String lastName, BlockChain johnStaCoin, ArrayList<Transaction> pendingTransaction) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.wallet = wallet;
        this.setFirstName(firstName);
        this.lastName = lastName;
        this.johnStaCoin = johnStaCoin;
        this.pendingTransaction = pendingTransaction;
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
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", johnStaCoin="
				+ johnStaCoin + ", wallet=" + wallet + ", pendingTransaction=" + pendingTransaction + "]";
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

	public BlockChain getJohnStaCoin() {
		return johnStaCoin;
	}

	public void setJohnStaCoin(BlockChain johnStaCoin) {
		this.johnStaCoin = johnStaCoin;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	public ArrayList<Transaction> getPendingTransaction() {
		return pendingTransaction;
	}

	public void setPendingTransaction(ArrayList<Transaction> pendingTransaction) {
		this.pendingTransaction = pendingTransaction;
	}

	
}
