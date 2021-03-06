package start.data;


import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;



@Entity
@Table(name = "USERS")
public class UserEntity {
	private String email_space;
	private String space;
	private String email;
	private String role;
	private String username;
	private String password;
	private String johnStaCoin;
	private String privateKey;
	private String publicKey;
	private double balance;

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}


	/*private String wallet ;
	private String pendingTransaction;*/
	

	public UserEntity() {

	}

	@Id
	public String getEmail_space() {
		return email_space;
	}
	public void setEmail_space(String emailAndSpace) {
		this.email_space = emailAndSpace;
	}
	
	public String getSpace() {
		return space;
	}

	@Value("${spring.application.name:dummy}")
	public void setSpace(String space) {
		this.space = space;
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
	
	public String getJohnStaCoin() {
		return johnStaCoin;
	}

	public void setJohnStaCoin(String johnStaCoin) {
		this.johnStaCoin = johnStaCoin;
	}
/*
	public String getWallet() {
		return wallet;
	}

	public void setWallet(String wallet) {
		this.wallet = wallet;
	}

	public String getPendingTransaction() {
		return pendingTransaction;
	}

	public void setPendingTransaction(String pendingTransaction) {
		this.pendingTransaction = pendingTransaction;
	}*/
}
