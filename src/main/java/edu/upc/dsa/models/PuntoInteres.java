package edu.upc.dsa.models;

public class PuntoInteres {
    public String name;

    public PuntoInteres(String name){
        this.name = name;
    }
    public PuntoInteres(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name +
                '}';
    }
}
