package edu.upc.eetac.dsa.service;
import edu.upc.eetac.dsa.FactorySession;
import edu.upc.eetac.dsa.IUserDAO;
import edu.upc.eetac.dsa.Session;
import edu.upc.eetac.dsa.UserDAOImpl;
import edu.upc.eetac.dsa.model.BuyedObject;
import edu.upc.eetac.dsa.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static edu.upc.eetac.dsa.FactorySession.openSession;

@Api (value = "/user")
@Path ("/user")

public class UserService {

    //Aqui hem d'afegir o inicialitzar les coses
    private IUserDAO u;


    public UserService(){
        this.u = UserDAOImpl.getInstance();


    }


    @POST
    @ApiOperation(value = "Register new USER", notes = "PARA registrar en Android i WEB")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = User.class),
            @ApiResponse(code = 503, message = "BBDD Down"),
            @ApiResponse(code = 400, message = "WRONG DATA"),
            @ApiResponse(code = 403, message = "NICKNAME USED")

    })

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response RegisterUser(User user) {






    }

    @POST
    @ApiOperation(value = "LOGIN USER", notes = "PARA LOGIN Android i WEB")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Not Authorized"),
            @ApiResponse(code = 404, message = "User Not Found")

    })

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response LoginUser(User parametrosLogIn) {


    }

    @POST
    @ApiOperation(value = "Change Password", notes = "Pedir @ para cambiar el password")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "WRONG DATA")

    })

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response ChangePassword(User mail) {


    }

    @PUT
    @ApiOperation(value = "Edit User", notes = "Modificar los parametros de un usaurio")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 503, message = "BBDD Down"),
            @ApiResponse(code = 400, message = "WRONG DATA")

    })

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response EditUser(User user) {


    }

    @GET
    @ApiOperation(value = "Obtener Datos user", notes = "Nos devuelve todos los datos del user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = User.class),
            @ApiResponse(code = 503, message = "BBDD Down")

    })

    @Path("/{nickName}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response GetUser(@PathParam("nickName") String userName) {


    }

    @PUT
    @ApiOperation(value = "Comprar Objeto", notes = "Nos dice que objeto compra el cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 503, message = "BBDD Down"),
            @ApiResponse(code=406, message = "NOT Enough Money")

    })

    @Path("/{nickName}/{idObject}")
    public Response BuyObject(@PathParam("nickName") String userName,@PathParam("idObject") int idBuyedObject ) {


    }

    @GET
    @ApiOperation(value = "Lista Objetos de User", notes = "Nos devuelve todos los objetos de un user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = BuyedObject.class, responseContainer = "List"),
            @ApiResponse(code = 503, message = "BBDD Down")

    })

    @Path("/{nickName}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma BuyedObject in a List
    public Response ListBuyedObjects(@PathParam("nickName") String userName) {


    }

    @GET
    @ApiOperation(value = "Recibimos characteristics de un objeto", notes = "Nos devuelve el objeto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = BuyedObject.class),
            @ApiResponse(code = 503, message = "BBDD Down")

    })

    @Path("/{idObject}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response GetObjectCharacteristics(@PathParam("idObject") int idBuyedObject) {


    }









}
