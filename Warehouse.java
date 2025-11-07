/**
 * @author Bryan Erickson
 */
public class Warehouse{


private static Warehouse warehouse;
private ClientList c_list = new ClientList();

ProductList p_list = new ProductList();
//private Waitlist waitlist = new Waitlist();

private Warehouse(){
    Client testClient = new Client("101", "Bob", 50);
    c_list.addClient(testClient);

   
    p_list.addProduct("p1", 5.25, 10);
}

public static Warehouse instance(){
    if(warehouse == null){
        return (warehouse = new Warehouse());
    }else{
        return warehouse;
    }

}

public Client searchClient(String id){
    
    return c_list.getClient(id);
}

public ProductList getProductList(){
    return p_list;
}

public ClientList getClientList()
{
    return c_list;
}
}