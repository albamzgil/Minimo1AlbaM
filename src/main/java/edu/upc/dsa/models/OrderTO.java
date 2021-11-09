package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

public class OrderTO {

    private String user;
    private List<PuntoInteresTO> puntos;

    public OrderTO() {
        this.puntos = new ArrayList<PuntoInteresTO>();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<PuntoInteresTO> getPuntos() {
        return puntos;
    }

    public void setProducts(List<PuntoInteresTO> puntos) {
        this.puntos = puntos;
    }

    public OrderTO(Order order) {
        this();
        this.user = order.user.name;
        for (PuntoInteres p: order.puntosList) {
            this.puntos.add(new PuntoInteresTO(p.name));
        }
    }
}
