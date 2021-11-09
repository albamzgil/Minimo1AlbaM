package edu.upc.dsa.services;


import edu.upc.dsa.UsuarioManager;
import edu.upc.dsa.UsuarioImpl;
import edu.upc.dsa.models.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Api(value = "/Products", description = "Endpoint to Track Service")
@Path("/Products")
public class UserService {

    private UsuarioManager scenario;

    public UserService() {
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
        order.addLP(new PuntoInteres("Puerta 2"));
        scenario.doOrder(order);
    }

    @POST
    @ApiOperation(value = "Add a new user", notes = "Add a new user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/addUser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {

        if (user.getName()==null) return Response.status(500).entity(user).build();
        this.scenario.addUser(user);
        return Response.status(201).entity(user).build()  ;

    }

    @POST
    @ApiOperation(value = "Add a new point", notes = "Add a new point")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = PuntoInteres.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/addPunto")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPunto(PuntoInteres punto) {

        if (punto.getName()==null) return Response.status(500).entity(punto).build();
        this.scenario.addPunto(punto);
        return Response.status(201).entity(punto).build()  ;

    }

    @POST
    @ApiOperation(value = "Create a new Order", notes = "Create a new Order")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Order.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })

    @Path("/doOrder/")
    @Consumes(MediaType.APPLICATION_JSON)

    public Response doOrder(Order order) {
        Order order2 = new Order(this.scenario.getUser(order.user.getName()));
        if (order2.getUser() == null) return Response.status(500).entity(order).build(); // solo funciona con usuarios ya establecido mas arriba!
        this.scenario.doOrder(order2);
        return Response.status(201).entity(order).build();
    }

    @GET
    @ApiOperation(value = "Get points by user", notes = "Get points by user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = PuntoInteres.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "Products not found")
    })
    @Path("/PuntosByUser/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPuntosByUser(@PathParam("user") String user) {
        List<PuntoInteres> list = scenario.getPuntosByUsers(user);
        GenericEntity<List<PuntoInteres>> entity = new GenericEntity<List<PuntoInteres>>(list) {};
        if (list.size() == 0) return Response.status(404).build();
        return Response.status(201).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "Get users by point", notes = "Get products by sales")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = PuntoInteres.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "Products not found")
    })
    @Path("/UsersByPunto/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsersByPunto(@PathParam("punto interes") PuntoInteres punto) {
        List<String> list = scenario.getUsersByPunto(punto);
        GenericEntity<List<String>> entity = new GenericEntity<List<String>>(list) {};
        if (list.size() == 0) return Response.status(404).build();
        return Response.status(201).entity(entity).build();
    }

   @GET
    @ApiOperation(value = "Get order by users", notes = "Get order by users")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = OrderTO.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "Products not found")
    })

    @Path("/OrderByUsers/{user}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrdersByUser(@PathParam("user") String user) {
        List<Order> list = scenario.getOrdersByUser(user);
        if (list.size() == 0) return Response.status(404).build();

        System.out.println(list.toArray());
        //
        List<OrderTO> aux = new ArrayList<OrderTO>();

        for (Order o: list) {
            aux.add(new OrderTO(o));
        }



        GenericEntity<List<OrderTO>> entity = new GenericEntity<List<OrderTO>>(aux) {};
        return Response.status(201).entity(entity).build()  ;

        /*
        GenericEntity<List<Track>> entity = new GenericEntity<List<Track>>(tracks) {};
        return Response.status(201).entity(entity).build()  ;
        */

    }

/*
    @DELETE
    @ApiOperation(value = "delete a Track", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })

    @Path("/{id}")
    public Response deleteTrack(@PathParam("id") String id) {
        Track t = this.tm.getTrack(id);
        if (t == null) return Response.status(404).build();
        else this.tm.deleteTrack(id);
        return Response.status(201).build();
    }



    @PUT
    @ApiOperation(value = "New order", notes = "New order")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/")
    public Response updateTrack(Track track) {

        Track t = this.tm.updateTrack(track);

        if (t == null) return Response.status(404).build();

        return Response.status(201).build();
    }
*/




}