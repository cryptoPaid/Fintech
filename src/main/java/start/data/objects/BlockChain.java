package start.data.objects;

import java.util.ArrayList;

public class BlockChain {
	 	private ArrayList<Block> chain = new ArrayList<>();
	    private int miningReward;
	    private int difficulty;
	    private ArrayList<Transaction> pendingTransaction = new ArrayList<>();
	    public BlockChain() {
	    	
	    }
	    public ArrayList<Block> getChain() {
	        return chain;
	    }
	    
	    public void createTransaction(Transaction transaction, ArrayList<Transaction> pendingTransaction) {
	        pendingTransaction.add(transaction);
	    }

	    public int getBalanceOfAddress(String address) {
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
}
