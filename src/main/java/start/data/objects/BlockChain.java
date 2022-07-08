<<<<<<< HEAD
package start.data.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class BlockChain {
	 	private List<Map<String,Object>> chain;
		//private HashMap<String,Object> list;
	    private int miningReward;
		private int difficulty;
		public BlockChain() {
	    	
	    }
		
		public BlockChain(List<Map<String, Object>> chain, int miningReward, int difficulty) {
			super();
			this.chain = chain;
			this.miningReward = miningReward;
			this.difficulty = difficulty;
		}

		public List<Map<String, Object>> getChain() {
			return chain;
		}



		public void setChain(List<Map<String, Object>> chain) {
			this.chain = chain;
		}
		public int getMiningReward() {
			return miningReward;
		}
		public void setMiningReward(int miningReward) {
			this.miningReward = miningReward;
		}
		public int getDifficulty() {
			return difficulty;
		}
		public void setDifficulty(int difficulty) {
			this.difficulty = difficulty;
		}
	    
	   
	 /*  public int getBalanceOfAddress(String address) {
	       int balance = 0;
	        for (Block block : this.chain) {
	             for (Transaction trans : block.getTransaction()) {
	                if (trans.getFromAddress().equals(address)) {
	                    balance -= trans.getAmount();
	                }
	                if (trans.getToAddress().equals(address)) {
	                    balance += trans.getAmount();
	                }
	            }
	        }
	        return balance;
	    }
	    public ArrayList<Block> getChain() {
	        return chain;
	    }
	    public void setChain(ArrayList<Block> chain) {
			this.chain = chain;
		}
	    */
	    public void createTransaction(Transaction transaction, ArrayList<Transaction> pendingTransaction) {
	        pendingTransaction.add(transaction);
	    }


		@Override
		public String toString() {
			return "BlockChain [list=" + chain + ", miningReward=" + miningReward + ", difficulty=" + difficulty + "]";
		}
		

	    
}
=======
package start.data.objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class BlockChain {
	 	private List<Map<String,Object>> chain;
		//private HashMap<String,Object> list;
	    private int miningReward;
		private int difficulty;
		public BlockChain() {
	    	
	    }
		
		public BlockChain(List<Map<String, Object>> chain, int miningReward, int difficulty) {
			super();
			this.chain = chain;
			this.miningReward = miningReward;
			this.difficulty = difficulty;
		}

		public List<Map<String, Object>> getChain() {
			return chain;
		}



		public void setChain(List<Map<String, Object>> chain) {
			this.chain = chain;
		}
		public int getMiningReward() {
			return miningReward;
		}
		public void setMiningReward(int miningReward) {
			this.miningReward = miningReward;
		}
		public int getDifficulty() {
			return difficulty;
		}
		public void setDifficulty(int difficulty) {
			this.difficulty = difficulty;
		}
	    
	   
	 /*  public int getBalanceOfAddress(String address) {
	       int balance = 0;
	        for (Block block : this.chain) {
	             for (Transaction trans : block.getTransaction()) {
	                if (trans.getFromAddress().equals(address)) {
	                    balance -= trans.getAmount();
	                }
	                if (trans.getToAddress().equals(address)) {
	                    balance += trans.getAmount();
	                }
	            }
	        }
	        return balance;
	    }
	    public ArrayList<Block> getChain() {
	        return chain;
	    }
	    public void setChain(ArrayList<Block> chain) {
			this.chain = chain;
		}
	    */
	    public void createTransaction(Transaction transaction, ArrayList<Transaction> pendingTransaction) {
	        pendingTransaction.add(transaction);
	    }


		@Override
		public String toString() {
			return "BlockChain [list=" + chain + ", miningReward=" + miningReward + ", difficulty=" + difficulty + "]";
		}
		

	    
}
>>>>>>> c813bdfdde53cb9a25135a0dcf4dc84cf0ce9f7c
