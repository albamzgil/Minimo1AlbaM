package edu.upc.dsa;

import edu.upc.dsa.models.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class ProductManagerImpTest {
    UsuarioManager scenario;



    @Before
    public void setUp() {

        scenario = UsuarioImpl.getInstance();
        scenario.addPunto(new PuntoInteres("Puerta 1"));
        scenario.addPunto(new PuntoInteres("Casilla 1"));
        scenario.addPunto(new PuntoInteres("Puerta 2"));
        scenario.addPunto(new PuntoInteres("Casilla 2"));
        scenario.addPunto(new PuntoInteres("Puente"));

        scenario.addUser("Laura", "123");
        scenario.addUser("Alba", "231");
        scenario.addUser("Pau", "321");

        Order order = new Order(scenario.getUser("Alba"));
        order.addLP(new PuntoInteres("Puente"));
        order.addLP(new PuntoInteres("Puerta 1"));
        scenario.doOrder(order);
    }


    @After
    public void tearDown() {
        scenario.getOrdersList().clear();
       // scenario.getUsersList().clear();

    }



    @Test
    public void testShowPuntosByUser(){
        Assert.assertEquals(5,scenario.getNumPuntos());
        scenario = UsuarioImpl.getInstance();
        scenario.addPunto(new PuntoInteres("Puerta 3"));
        scenario.addPunto(new PuntoInteres("Casilla 3"));
        scenario.addPunto(new PuntoInteres("Cafeteria"));
        Assert.assertEquals(8,scenario.getNumPuntos());
        List<PuntoInteres> productList = scenario.getPuntosByUsers(scenario.getUser("Alba"));
        Assert.assertEquals("Puente",productList.get(0).getName(), 0.01);
        Assert.assertEquals("Puerta 2",productList.get(1).getName(), 0.01);
    }
    @Test
    public void doOrder(){
        Assert.assertEquals(0, scenario.getOrderAmount());

        scenario.addUser("Laura","123");
        Order order = new Order(scenario.getUser("Laura"));
        order.addLP(new PuntoInteres("Cafeteria"));
        order.addLP(new PuntoInteres("Casilla 2"));
        scenario.doOrder(order);
        Assert.assertEquals(1, scenario.getOrderAmount());


    }

    @Test
    public  void getPuntosListByName()
    {
        List<Order> list = scenario.getOrdersByUser("Alba");
        Assert.assertEquals(1,list.size());
    }


}
