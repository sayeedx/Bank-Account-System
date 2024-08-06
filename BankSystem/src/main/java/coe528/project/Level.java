package coe528.project;
/**
 *
 * @author Sayeed
 */
    /**
     Abstraction Function: Each enum constant represents a different level of customer in the bank, w/ an associated fee for online purchases.
     Rep Invariant: - fee >= 0
    */
public enum Level //this class is immutable, as the state cant be changed after creating it
{
    //the fee for each online purchase
    SILVER(20),    //silver level w/ fee $20
    GOLD(10),      //gold level w/ fee $10
    PLATINUM(0);   //platinum level w/ fee $0

    private final double fee; 

    Level(double fee)
    {
        this.fee = fee; //cosntructing level w/ the specified fee
    }

    public double getFee()
    {
        return fee; //returns fee associated w/ the level
    }
    
    //returning string represention of level
    @Override
    public String toString()
    {
        return name() + " - Fee: $" + fee;
    }

    private boolean repOk() //checking if the rep invariant holds for this level
    {
        return fee >= 0; //true if the rep invaraint holds, false otherwise
    }
}
