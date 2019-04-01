import java.util.ArrayList;
import java.util.logging.Logger;
import com.google.gson.GsonBuilder;

public class Blockchain {

    public static ArrayList<Block> ledger = new ArrayList<Block>();
    public static int difficulty = 4;

    static Logger logger = Logger.getLogger(Blockchain.class.getName());
    public static void main(String[] args) {
        //add our blocks to the blockchain ArrayList:


        Block genesisBlock =new Block(new Transaction("Shubham","Poonam",100,"Rs 100 has been sent"), "0");
        ledger.add(genesisBlock);
        logger.info("Trying to Mine block 1... ");
        ledger.get(0).mineBlock(difficulty);

        Block secondBlock =new Block(new Transaction("Shubham","Poonam",200,"Rs 200 has been sent"),ledger.get(ledger.size()-1).hash);
        ledger.add(secondBlock);
        logger.info("Trying to Mine block 2... ");
        ledger.get(1).mineBlock(difficulty);

        ledger.add(new Block(new Transaction("Shubham","Poonam",300,"Rs 300 has been sent"),ledger.get(ledger.size()-1).hash));
        logger.info("Trying to Mine block 3... ");
        ledger.get(2).mineBlock(difficulty);

        ledger.add(new Block(new Transaction("Shubham","Poonam",400,"Rs 400 has been sent"),ledger.get(ledger.size()-1).hash));
        logger.info("Trying to Mine block 4... ");
        ledger.get(3).mineBlock(difficulty);

        logger.info("\nBlockchain is Valid: " + isChainValid());

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(ledger);
        logger.info("\nThe block chain: ");
        logger.info(blockchainJson);

        //second block is changed
        secondBlock.transaction=new Transaction("Shubham","Poonam",120,"Rs 120 has been sent");
        ledger.set(1, secondBlock);
        ledger.get(1).mineBlock(difficulty);
        logger.info("\nBlockchain is Valid: " + isChainValid());

        String blockchainAfterJson = new GsonBuilder().setPrettyPrinting().create().toJson(ledger);
        logger.info("\nThe block chain: ");
        logger.info(blockchainJson);

    }

    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        //loop through blockchain to check hashes:
        for(int i=1; i < ledger.size(); i++) {
            currentBlock = ledger.get(i);
            previousBlock = ledger.get(i-1);
            //compare registered hash and calculated hash:
            if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
                logger.info("Current Hashes not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
                logger.info("Previous Hashes not equal");
                return false;
            }
            //check if hash is solved
            if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
                logger.info("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }

}
