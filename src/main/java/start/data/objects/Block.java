package start.data.objects;

import java.util.ArrayList;
import java.util.Date;

public class Block {
	 	private Date timestamp;
	    private String data="";
	    private String hash;
	    private String previousHash;
	    private ArrayList<Transaction> transaction = new ArrayList<>();
	    private int nonce ;
	    
	    public Block() {
	    	
	    }
	    
	    public Date getTimestamp() {
	        return timestamp;
	    }

	    public String getData() {
	        return data;
	    }

	    public String getHash() {
	        return hash;
	    }

	    public String getPreviousHash() {
	        return previousHash;
	    }

	    public int getNonce() {
			return nonce;
		}

		public void setNonce(int nonce) {
			this.nonce = nonce;
		}

		public void setTimestamp(Date timestamp) {
			this.timestamp = timestamp;
		}

		public void setData(String data) {
			this.data = data;
		}

		public void setTransaction(ArrayList<Transaction> transaction) {
			this.transaction = transaction;
		}

		public ArrayList<Transaction> getTransaction() {
	        return transaction;
	    }

	    public void setHash(String hash) {
	        this.hash = hash;
	    }

	    public void setPreviousHash(String previousHash) {
	        this.previousHash = previousHash;
	    }


	    

}
