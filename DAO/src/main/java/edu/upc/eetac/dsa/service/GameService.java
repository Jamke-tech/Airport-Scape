package edu.upc.eetac.dsa.service;

import edu.upc.eetac.dsa.DAO.GameDAOImpl;
import edu.upc.eetac.dsa.DAO.IGameDAO;
import edu.upc.eetac.dsa.model.Game;
import edu.upc.eetac.dsa.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "/game", description = "Endpoint to Game Service")
@Path("/game")
public class GameService {
    private IGameDAO g;

    public GameService() {
        this.g = GameDAOImpl.getInstance();
    }

    @POST
    @ApiOperation(value = "Save GAME", notes = "Guarda la partida que estamos jugando")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Game.class),
            @ApiResponse(code = 503, message = "BBDD Down"),
            @ApiResponse(code = 400, message = "NICKNAME USED"),
    })
    @Path ("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveGame(Game game) {
        System.out.println("Save Game");
        try{
            int numException = g.saveGame(game);
            if (numException==0) {
                return Response.status(200).entity(game).build();
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

    @GET
    @ApiOperation(value = "Obtener Game saved", notes = "Nos devuelve todos los datos de la partida")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Game.class),
            @ApiResponse(code = 503, message = "BBDD Down")

    })

    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class game
    public Response getGameSaved(@PathParam("name") String name) {
        try{
            Game game = g.getGameSaved(name);
            return Response.status(200).entity(game).build();
        }
        catch (Exception e){

            return Response.status(503).build();
        }
    }

    @GET
    @ApiOperation(value = "Obtener id mapa", notes = "Nos devuelve el id del mapa en el que se había quedado la partida")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Integer.class),
            @ApiResponse(code = 503, message = "BBDD Down")

    })

    @Path("/getIdMap/{name}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class game
    public Response getIdMap (@PathParam("name") String name) {
        try{
            int idMap = g.getIdMap(name);
            return Response.status(200).entity(idMap).build();
        }
        catch (Exception e){

            return Response.status(503).build();
        }
    }

    @GET
    @ApiOperation(value = "Obtener StringMap mapa", notes = "Nos devuelve el mapa (modo string) en el que se había quedado la partida")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = String.class),
            @ApiResponse(code = 503, message = "BBDD Down")

    })

    @Path("/getStringMapByGameName/{name}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma class game
    public Response getStringMapByGameName (@PathParam("name") String name) {
        try{
            String stringMap = g.getStringMapByGameName(name);
            return Response.status(200).entity(stringMap).build();
        }
        catch (Exception e){

            return Response.status(503).build();
        }
    }
}
