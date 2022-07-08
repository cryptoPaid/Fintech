<<<<<<< HEAD
package start.userAPI;

import java.util.ArrayList;

import start.data.objects.BlockChain;
import start.data.objects.Transaction;
import start.data.objects.Wallet;

public class NewUserDetails {
	String email;
	String role;
	String username;
	String password;
	
	//ArrayList<Transaction> pendingTransaction;
	public NewUserDetails() {
	}
//, BlockChain johnStaCoin,Wallet wallet, ArrayList<Transaction> pendingTransaction
	
	public NewUserDetails(String email, String role, String username, String password) {
		super();
		this.email = email;
		this.role = role;
		this.username = username;
		this.password = password;

		/*this.pendingTransaction = pendingTransaction;*/
	}


	

		/*public ArrayList<Transaction> getPendingTransaction() {
		return pendingTransaction;
	}

	public void setPendingTransaction(ArrayList<Transaction> pendingTransaction) {
		this.pendingTransaction = pendingTransaction;
	}
*/
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
				+ password ;
	}

}
=======
package start.userAPI;

import java.util.ArrayList;

import start.data.objects.BlockChain;
import start.data.objects.Transaction;
import start.data.objects.Wallet;

public class NewUserDetails {
	String email;
	String role;
	String username;
	String password;
	
	//ArrayList<Transaction> pendingTransaction;
	public NewUserDetails() {
	}
//, BlockChain johnStaCoin,Wallet wallet, ArrayList<Transaction> pendingTransaction
	
	public NewUserDetails(String email, String role, String username, String password) {
		super();
		this.email = email;
		this.role = role;
		this.username = username;
		this.password = password;

		/*this.pendingTransaction = pendingTransaction;*/
	}


	

		/*public ArrayList<Transaction> getPendingTransaction() {
		return pendingTransaction;
	}

	public void setPendingTransaction(ArrayList<Transaction> pendingTransaction) {
		this.pendingTransaction = pendingTransaction;
	}
*/
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
				+ password ;
	}

}
>>>>>>> c813bdfdde53cb9a25135a0dcf4dc84cf0ce9f7c
