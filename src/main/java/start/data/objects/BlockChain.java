package start.data.objects;

import java.util.ArrayList;

public class BlockChain {
	 //	private ArrayList<Block> chain;
	 	private ArrayList<String> list;
	    private int miningReward;
		private int difficulty;
		public BlockChain() {
	    	
	    }
		
	    public BlockChain(ArrayList<String> list, int miningReward, int difficulty) {
			super();
			this.list =list;
			this.miningReward = miningReward;
			this.difficulty = difficulty;
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
		public ArrayList<String> getList() {
			return list;
		}
		public void setList(ArrayList<String> list) {
			this.list = list;
		}
		

	    
}
