package inception;

import java.util.ArrayList;

import com.google.gson.GsonBuilder;

public class tester2 {

	public static ArrayList<Block2> blockchain = new ArrayList<Block2>();
	public static int difficulty = 5;	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Trying to Mine block 1... ");
		addBlock(new Block2("im the first block", "0"));
		
		System.out.println("Trying to Mine block 2... ");
		addBlock(new Block2("im the second block",blockchain.get(blockchain.size()-1).hash));
		
		System.out.println("Trying to Mine block 3... ");
		addBlock(new Block2("im the third block",blockchain.get(blockchain.size()-1).hash));	
		
		System.out.println("\nBlockchain is Valid: " + isChainValid());
		
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		
		System.out.println("\nThe block chain: ");
		System.out.println(blockchainJson);
		System.out.println();
		System.out.println("Made by Yunxiang Chi");
	}
	
	public static Boolean isChainValid() 
	{
		Block2 currentBlock; 
		Block2 previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
		//loop through blockchain to check hashes:
		for(int i=1; i < blockchain.size(); i++) 
		{
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			//compare registered hash and calculated hash:
			if(!currentBlock.hash.equals(currentBlock.calculateHash()) )
			{
				System.out.println("Current Hashes not equal");			
				return false;
			}
			//compare previous hash and registered previous hash
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) 
			{
				System.out.println("Previous Hashes not equal");
				return false;
			}
			//check if hash is solved
			if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget))
			{
				System.out.println("This block hasn't been mined");
				return false;
			}
			
		}
		return true;
	}
	
	public static void addBlock(Block2 newBlock)
	{
		newBlock.mineBlock(difficulty);
		blockchain.add(newBlock);
	}
}
