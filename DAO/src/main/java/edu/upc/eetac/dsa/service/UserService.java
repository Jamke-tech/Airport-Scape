package edu.upc.eetac.dsa.service;
import edu.upc.eetac.dsa.model.*;
import edu.upc.eetac.dsa.DAO.*;
import edu.upc.eetac.dsa.util.Mailer;

import edu.upc.eetac.dsa.util.Mailer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Console;
import java.sql.SQLException;
import java.util.List;

@Api (value = "/user", description = "Endpoint to User Service")
@Path ("/user")

public class UserService {

    private IUserDAO u;

    public UserService(){
        this.u = UserDAOImpl.getInstance();

    }

    //OUR USER CRUD OPERATIONS
    @POST
    @ApiOperation(value = "Register new USER", notes = "Para registrar en Android i WEB")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = User.class),
            @ApiResponse(code = 503, message = "BBDD Down"),
            @ApiResponse(code = 400, message = "NICKNAME USED"),
    })
    @Path ("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response RegisterUser(User user) {
        System.out.println("Registre User");
        try{
            int numException = u.registerUser(user);
            if (numException==0) {
                return Response.status(200).entity(user).build();
            }
            else
            {
                return Response.status(400).build();
            }

        }
        catch (Exception e){
            e.printStackTrace();
            return Response.status(503).build();
        }
    }

    @PUT
    @ApiOperation(value = "LOGIN USER", notes = "Para LOGIN Android i WEB")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = User.class),
            @ApiResponse(code = 401, message = "Not Authorized"),
            @ApiResponse(code = 404, message = "User Not Found"),
            @ApiResponse(code = 503, message = "BBDD Down")

    })

    @Path ("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response LoginUser(User parametrosLogIn) {
        try{
            int numException = u.loginUser(parametrosLogIn);
            if (numException==0) {
                return Response.status(200).entity(parametrosLogIn).build();
            }
            else if(numException==1)
            {
                return Response.status(401).build();
            }
            else
            {
                return Response.status(404).build();
            }
        }
        catch (Exception e){

            return Response.status(503).build();
        }


    }

    @POST
    @ApiOperation(value = "Change Password", notes = "Pedir @ para cambiar el password")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "User Not Found"),
            @ApiResponse(code = 400, message = "BAD REQUEST")

    })

    @Path("/changepass")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response ChangePassword(User mail) {
        try {
            User user = u.getUserByEmail(mail.mail);
            //System.out.println(user.mail);
            //System.out.println(mail.mail);
            if (user.mail != null && user.mail.equals(mail.mail)){
                Mailer.send(mail.mail,"Recover your password","Dear "+user.getUserName()+ ", you can access the following link to set a new password for your account: http://eetacdsa0.upc.es:8080/changepassword.html?user="+user.getUserName());
                return Response.status(200).build();
            }
            else{
                return Response.status(404).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(400).build();
        }
    }

    @PUT
    @ApiOperation(value = "Edit User", notes = "Modificar los parametros de un usaurio")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 503, message = "BBDD Down"),
            @ApiResponse(code = 400, message = "WRONG DATA")
    })
    @Path("/edit")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response EditUser(User user) {

        try{
            int numException = u.updateUser(user);
            if (numException==0) {
                return Response.status(200).entity(user).build();
            }
            else
            {
                return Response.status(400).build();
            }
        }
        catch (Exception e){

            return Response.status(503).build();
        }
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
        try{
            User user = u.getUserByNickname(userName);
            return Response.status(200).entity(user).build();
        }
        catch (Exception e){

            return Response.status(503).build();
        }
    }

    @GET
    @ApiOperation(value = "Get user ranking", notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "ERROR")
    })
    @Path("/ranking")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRanking() throws SQLException {
        List<User> ranking = u.getListUsers();

        GenericEntity<List<User>> entity = new GenericEntity<List<User>>(ranking) {};
        if (ranking.size() > 0) {
            return Response.status(201).entity(entity).build();
        }
        else
            return Response.status(404).build();
    }
}
//Comentario de una linea