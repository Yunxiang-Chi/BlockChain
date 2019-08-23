package inception;

import java.util.Date;

public class Block2 {
	
	public String hash;
	public String previousHash; 
	private String data; //our data will be a simple message.
	private long timeStamp; //as number of milliseconds since 1/1/1970.
	private int nonce;
	
	//Block Constructor.  
	public Block2(String data,String previousHash )
	{
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		
		this.hash = calculateHash(); //Making sure we do this after we set the other values.
	}
	
	//Calculate new hash based on blocks contents
	public String calculateHash()
	{
		String calculatedhash = sha_256_2.getSHA256( previousHash +
													 Long.toString(timeStamp) +
													 Integer.toString(nonce) + 
													 data );
		return calculatedhash;
	}
	
	//Increases nonce value until hash target is reached.
	public void mineBlock(int difficulty)
	{
		// create a string value which depends on the diffculty's total digits
				String target = new String(new char[difficulty]).replace('\0', '0');
				double timep = System.currentTimeMillis();
				while(!hash.substring(0, difficulty).equals(target))
				{
					nonce ++;
					hash = calculateHash();
				}
				double timen = System.currentTimeMillis();
				System.out.println("Congratuations! A Block Is Mined: " + hash 
									+ ". The total time-consuming is: " + (timen - timep)/1000 + "s.");
	}
}
