package edu.upc.eetac.dsa.service;
import edu.upc.eetac.dsa.model.*;
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

@Api (value = "/game")
@Path ("/game")

public class GameService {

    //Aqui hem d'afegir o inicialitzar les coses


    public GameService(){

    }


    @POST
    @ApiOperation(value = "Register new USER", notes = "PARA registrar en Android i WEB")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 503, message = "BBDD Down"),
            @ApiResponse(code = 400, message = "WRONG DATA"),
            @ApiResponse(code = 403, message = "NICKNAME USED")

    })

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
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
    public Response LoginUser(Login parametrosLogin) {


    }

    @POST
    @ApiOperation(value = "Change Password", notes = "Pedir @ para cambiar el password")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "WRONG DATA")

    })

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response ChangePassword(User user) {


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
