/**
 * @author Bryan Erickson
 */
public abstract class wareHouseState{
    protected static wareHouseContext context;
    protected wareHouseState(){
        //context = wareHouseContext.instance();
    }
    public abstract void run();
}