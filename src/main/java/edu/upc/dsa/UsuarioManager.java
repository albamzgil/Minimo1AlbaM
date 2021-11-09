package edu.upc.dsa;

import edu.upc.dsa.models.Order;
import edu.upc.dsa.models.PuntoInteres;
import edu.upc.dsa.models.User;

import java.util.List;
import java.util.Queue;

public interface UsuarioManager {

    List<PuntoInteres> getPuntosList();

    void doOrder(Order order);
    List<Order> getOrdersByUser(String user);

    List<PuntoInteres> getPuntosByUsers(String user);
    List<String> getUsersByPunto(PuntoInteres punto);

    void addPunto(PuntoInteres punto);

    int getNumPuntos();
    void addUser(String user, String id);
    String getUser(String user);
    int getOrderAmount();
    List<PuntoInteres> getUsersList();
    Queue<Order> getOrdersList();
    List<String> getUsersAlphabetic();

    void addUser(User user);
}
