package start.data.objects;

import java.security.PrivateKey;
import java.security.PublicKey;

public class Wallet {
    private String privateKey;
    private String publicKey;
    private double balance;



    public Wallet (){}



	public Wallet(String privateKey, String publicKey, double balance) {
		super();
		this.privateKey = privateKey;
		this.publicKey = publicKey;
		this.balance = balance;
	}



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



	@Override
	public String toString() {
		return "Wallet [privateKey=" + privateKey + ", publicKey=" + publicKey + ", balance=" + balance + "]";
	}



	public void setBalance(double balance) {
		this.balance = balance;
	} 
}
