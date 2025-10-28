import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
public class ClientList {
    private List<Client> clients;

    public ClientList() {
        clients = new ArrayList<>();
    }

    public boolean addClient(Client client) {
        // prevent duplicate IDs
        for (Client c : clients) {
            if (c.getId().equals(client.getId())) {
                return false;
            }
        }
        clients.add(client);
        return true;
    }

    public Client getClient(String id) {
        for (Client c : clients) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null; // not found
    }

    public List<Client> getAllClients() {
        return clients;
    }
    public Iterator getAllClients2() {
    return clients.iterator();
    }
    public void Print_ClientList() {
        Iterator cli = getAllClients2();
        while (cli.hasNext()){
            System.out.println(cli.next());
        }
    }

    @Override
    public String toString() {
        return clients.toString();
    }
}
