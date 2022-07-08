<<<<<<< HEAD
package start.data.objects;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Block {
		
	 	private Date timestamp;
	    private String data="";
	    private String hash;
	    private String previousHash;
	    private Map<String,Transaction> transaction =new HashMap<>();
	    private int nonce ;
	    
	    public Block() {
	    	
	    }
	    public Block(Date timestamp, String data, String hash, String previousHash,
			 int nonce,Map<String,Transaction> transaction) {
			super();
			this.timestamp = timestamp;
			this.data = data;
			this.hash = hash;
			this.previousHash = previousHash;
			this.transaction = transaction;
			this.nonce = nonce;
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

		public void setTransaction(Map<String,Transaction> transaction) {
			this.transaction = transaction;
		}

		public Map<String,Transaction> getTransaction() {
	        return transaction;
	    }



		public void setHash(String hash) {
	        this.hash = hash;
	    }

	    public void setPreviousHash(String previousHash) {
	        this.previousHash = previousHash;
	    }


	    

}
=======
package start.data.objects;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Block {
		
	 	private Date timestamp;
	    private String data="";
	    private String hash;
	    private String previousHash;
	    private Map<String,Transaction> transaction =new HashMap<>();
	    private int nonce ;
	    
	    public Block() {
	    	
	    }
	    public Block(Date timestamp, String data, String hash, String previousHash,
			 int nonce,Map<String,Transaction> transaction) {
			super();
			this.timestamp = timestamp;
			this.data = data;
			this.hash = hash;
			this.previousHash = previousHash;
			this.transaction = transaction;
			this.nonce = nonce;
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

		public void setTransaction(Map<String,Transaction> transaction) {
			this.transaction = transaction;
		}

		public Map<String,Transaction> getTransaction() {
	        return transaction;
	    }



		public void setHash(String hash) {
	        this.hash = hash;
	    }

	    public void setPreviousHash(String previousHash) {
	        this.previousHash = previousHash;
	    }


	    

}
>>>>>>> c813bdfdde53cb9a25135a0dcf4dc84cf0ce9f7c
