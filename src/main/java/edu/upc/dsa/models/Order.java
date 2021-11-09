package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class Order {
    public User user;
    public List<PuntoInteres> puntosList;


    public Order(User user) {
        this.user = user;
        this.puntosList = new ArrayList<PuntoInteres>();
    }

    public Order(String user){}

    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }

    public List<PuntoInteres> getPuntosList() {
        return puntosList;
    }

    public void setPuntosList(List<PuntoInteres> puntosList) {
        this.puntosList = puntosList;
    }


    public void addLP(PuntoInteres punto) {
        puntosList.add(punto);
    }

    @Override
    public String toString() {
        return "Order{" +
                "user=" + user +
                ", puntosList=" + puntosList +
                '}';
    }
}
