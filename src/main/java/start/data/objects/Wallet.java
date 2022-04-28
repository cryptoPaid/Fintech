package start.data.objects;

import java.security.PrivateKey;
import java.security.PublicKey;

public class Wallet {
    private String privateKey;
    private String publicKey;
    private double balance;



    public Wallet (){}



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
}
