/**
 * @author Bryan Erickson, Jiali Zhao
 */
//package Pro1_Warehouse;

import java.io.*;
import java.util.*;

public class Warehouse implements Serializable {
    private static Warehouse warehouse; // singleton instance

    private ProductList productList;
    private ClientList clientList;

    // Private constructor for singleton
    private Warehouse() {
        productList = ProductList.instance();
        clientList = ClientList.instance();
    }

    // Singleton accessor
    public static Warehouse instance() {
        if (warehouse == null) {
            warehouse = new Warehouse();
        }
        return warehouse;
    }

}

public Client searchClient(String id){
    
    return c_list.getClient(id);
}

public ProductList getProductList(){
    return p_list;
}


}