package edu.upc.eetac.dsa.service;

import edu.upc.eetac.dsa.DAO.IObjectDAO;
import edu.upc.eetac.dsa.DAO.IUserDAO;
import edu.upc.eetac.dsa.DAO.ObjectDAOImpl;
import edu.upc.eetac.dsa.DAO.UserDAOImpl;
import edu.upc.eetac.dsa.model.BuyedObject;
import edu.upc.eetac.dsa.model.Objects;
import edu.upc.eetac.dsa.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/object", description = "Endpoint to Object Service")
@Path ("/object")
public class ObjectService {

    private IObjectDAO o;
    private IUserDAO u;

    public ObjectService(){
        this.o = ObjectDAOImpl.getInstance();
        this.u = UserDAOImpl.getInstance();

    }

    @PUT
    @ApiOperation(value = "Comprar Objeto", notes = "Nos dice que objeto compra el cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 503, message = "BBDD Down"),
            @ApiResponse(code=406, message = "NOT Enough Money")

    })

    @Path("/buy/{nickName}/{idObject}")
    public Response BuyObject(@PathParam("nickName") String userName, @PathParam("idObject") int idObject ) {

        try{
            User user = u.getUserByNickname(userName);
            int error = o.buyObjectForUser(user,idObject);
            if (error==0)
            {
                return Response.status(200).build();
            }
            else if (error==6){
                return Response.status(406).build();
            }
            else
                return Response.status(503).build();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return Response.status(503).build();
        }

    }

    @GET
    @ApiOperation(value = "Lista Objetos de User", notes = "Nos devuelve todos los objetos de un user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = BuyedObject.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "Error en los datos"),
            @ApiResponse(code = 503, message = "BBDD Down")

    })

    @Path("/getlist/{nickName}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma BuyedObject in a List
    public Response ListBuyedObjects(@PathParam("nickName") String userName) {
        try{
            List<Objects> objectsBuyedByUser = this.o.getListBuyedObjects(userName);
            if (objectsBuyedByUser==null)
            {
                return Response.status(401).build();
            }
            GenericEntity<List<Objects>> entity = new GenericEntity<List<Objects>>(objectsBuyedByUser) {};
            return Response.status(200).entity(entity).build()  ;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return Response.status(503).build();
        }

    }

    /*@GET
    @ApiOperation(value = "Recibimos characteristics de un objeto", notes = "Nos devuelve el objeto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = BuyedObject.class),
            @ApiResponse(code = 503, message = "BBDD Down")

    })

    @Path("/{idObject}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class user
    public Response GetObjectCharacteristics(@PathParam("idObject") int idBuyedObject) {


    }*/
}
