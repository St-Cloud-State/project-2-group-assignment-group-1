/**
 * @author Bryan Erickson
 */
public class SecuritySystem{

    private final String UserId;
    private final String userPassWord;

    public SecuritySystem(String id, String pw)
    {
        this.UserId = id;
        this.userPassWord = pw;
    }

    public boolean verifyPassword(){

        if(this.UserId.equals("Clerk")){
            if(this.UserId.equals(this.userPassWord)){
                return true;
            }else{
                return false;
            }
        }else if(this.UserId.equals("Manager")){
            if(this.UserId.equals(this.userPassWord)){
                return true;
            }else{
                return false;
            }
        }else{
            if(Warehouse.instance().searchClient(this.UserId) != null){
            if(this.UserId.equals(this.userPassWord)){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
    }
    public static void main(String[] args) {
        
    }

        }


