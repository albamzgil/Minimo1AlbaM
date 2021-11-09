package edu.upc.dsa.models;

public class PuntoInteresTO {
    private String name;
    private int q;

    public PuntoInteresTO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQ() {
        return q;
    }

    public void setQ(int q) {
        this.q = q;
    }

    public PuntoInteresTO(String name) {
        this.name = name;
    }
}
