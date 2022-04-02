package start.data.objects;

import java.security.PrivateKey;
import java.security.PublicKey;

public class Wallet {
    private PrivateKey privateKey;
    private PublicKey publicKey;
    private double balance;



    public Wallet (){}



	public PrivateKey getPrivateKey() {
		return privateKey;
	}



	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}



	public PublicKey getPublicKey() {
		return publicKey;
	}



	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}



	public double getBalance() {
		return balance;
	}



	public void setBalance(double balance) {
		this.balance = balance;
	} 
}
