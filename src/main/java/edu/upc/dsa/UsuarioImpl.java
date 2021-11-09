package edu.upc.dsa;

import edu.upc.dsa.models.Order;
import edu.upc.dsa.models.User;
import edu.upc.dsa.models.PuntoInteres;


import java.util.*;
import java.util.logging.Logger;

public class UsuarioImpl implements UsuarioManager {
    public List<PuntoInteres> puntoInteresList = new ArrayList<>();
    public Queue<Order> ordersList = new LinkedList<>();
    public HashMap<String,User> users = new HashMap<String,User>();
    //public List<User> users = new ArrayList<>();

    final static Logger logger = Logger.getLogger(String.valueOf(UsuarioImpl.class));

    private static UsuarioImpl instance;
    public static UsuarioImpl getInstance() {
        if(instance == null)
            instance = new UsuarioImpl();

        return instance;
    }


    @Override
    public List<PuntoInteres> getPuntosList() {
          return puntoInteresList;
    }

    @Override
    public void doOrder(Order order) {
        ordersList.add(order);
        User client = order.getUser();
        client.getOrderList().add(order);

    }
    public void addUser(String user, String id) {
        this.users.put(user, new User(user,id));
    }

    public String getUser(String user)
    {
        return String.valueOf(this.users.get(user));
    }

    public Queue<Order> getOrdersList() {
        return this.ordersList;
    }

    @Override
    public List<Order> getOrdersByUser(String user) {
            return users.get(user).getOrderList();
    }

    @Override
    public List<PuntoInteres> getPuntosByUsers(String user) {
        return users.get(user).getPuntoInteresList();
    }

    public List<String> getUsersByPunto(PuntoInteres punto){
        List<String> list = new ArrayList<String>((Collection<? extends String>) users.get(punto));
        return list;
    }

    public List<String> getUsersAlphabetic(){
        List<String> list = new ArrayList<String>(users.keySet());
        Collections.sort(list);
        return list;
    }

    @Override
    public void addUser(User user) {
        this.users.put(user); //està malament
    }


    public void addPunto(PuntoInteres punto) {
        this.puntoInteresList.add(punto);
    }

    public int getOrderAmount(){ return this.ordersList.size(); }

    @Override
    public List<PuntoInteres> getUsersList() {
        return null;
    }

    @Override
    public int getNumPuntos() {
        return this.puntoInteresList.size();
    }

/*    public ArrayList<User> getUserByNumPuntos(){
        return
    }
*/
}

