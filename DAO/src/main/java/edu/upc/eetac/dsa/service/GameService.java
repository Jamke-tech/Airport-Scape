package edu.upc.eetac.dsa.service;

import edu.upc.eetac.dsa.DAO.GameDAOImpl;
import edu.upc.eetac.dsa.DAO.IGameDAO;
import edu.upc.eetac.dsa.model.Game;
import edu.upc.eetac.dsa.model.Map;
import edu.upc.eetac.dsa.model.Objects;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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
            @ApiResponse(code = 200, message = "OK", response = Map.class),
            @ApiResponse(code = 503, message = "BBDD Down")

    })

    @Path("/getStringMap/{id}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma string
    public Response getStringMap (@PathParam("id") int id) {
        try{
            Map map = g.getStringMap(id);
            return Response.status(200).entity(map).build();
        }
        catch (Exception e){

            return Response.status(503).build();
        }
    }

    @POST
    @ApiOperation(value = "Win GAME", notes = "Cuando ganas la partida se le suma el dinero al usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 503, message = "BBDD Down"),
            @ApiResponse(code = 400, message = "NICKNAME USED"),
    })
    @Path ("/win")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response winGame(Game game) {
        System.out.println("Win Game");
        try{
            int numException = g.winGame(game);
            if (numException==0) {
                return Response.status(200).build();
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
    @ApiOperation(value = "Lista Partidas de un Usuario", notes = "Nos devuelve todas los partidas de un user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Game.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "Error en los datos"),
            @ApiResponse(code = 503, message = "BBDD Down")

    })

    @Path("/getGames/{userName}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma Games in a List
    public Response ListAllGameOfUser(@PathParam("userName") String userName) {
        try {
            List<Game> gamesOfUser = this.g.getListUserGames(userName);
            if (gamesOfUser == null) {
                return Response.status(401).build();
            }
            else{
            GenericEntity<List<Game>> entity = new GenericEntity<List<Game>>(gamesOfUser) { };
            return Response.status(200).entity(entity).build();

            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(503).build();
        }

    }

    @GET
    @ApiOperation(value = "Lista partidas de User", notes = "Nos devuelve todas los partidas de un user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Game.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "Error en los datos"),
            @ApiResponse(code = 503, message = "BBDD Down")

    })

    @Path("/getList/{nickName}")
    @Produces(MediaType.APPLICATION_JSON)// nos devuelve JSON con forma Games in a List
    public Response listGames(@PathParam("nickName") String userName) {
        try {
            List<Game> gamesOfUser = this.g.getListUserGames(userName);
            if (gamesOfUser == null) {
                return Response.status(401).build();
            }
            GenericEntity<List<Game>> entity = new GenericEntity<List<Game>>(gamesOfUser) {
            };
            return Response.status(200).entity(entity).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(503).build();
        }

    }
}
