package Client.Connections;

import java.util.ArrayList;
import java.util.List;


import org.json.JSONArray;
import org.json.JSONObject;

import Client.Controllers.GameBoard;
import Client.Models.Tile;
import Client.Models.Unit;
import Client.Models.User;
public class JsonConvertor {
    
    public static JSONObject createGameStateJSON() {
        Tile board[][] = GameBoard.board;

        JSONObject boardJSON = new JSONObject();
        boardJSON.put("RequestType", RequestType.GAMESTATE);

        JSONArray tilesArray = new JSONArray();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JSONObject tileJSON = new JSONObject();
                tileJSON.put("x", i);
                tileJSON.put("y", j);
                tileJSON.put("type", board[i][j].getType().toString()); 
                if (board[i][j].isOccupied()) {
                    Unit unit = board[i][j].getUnit();
                    JSONObject unitJSON = convertUnitToJSON(unit);
                    tileJSON.put("unit", unitJSON);
                }
                tilesArray.put(tileJSON);
            }
        }

        boardJSON.put("tiles", tilesArray);       

        return boardJSON;
    }

    private static JSONObject convertUnitToJSON(Unit unit) {
        JSONObject unitJSON = new JSONObject();
        unitJSON.put("type", unit.getName()); 
        return unitJSON;
    }



    public static JSONObject createLobby(int port, User player){

        JSONObject Lobby = new JSONObject();

        Lobby.put("RequestType", RequestType.CREATELOBBY);

        Lobby.put("lobbyPort", port);

        JSONArray playersarray = new JSONArray();

        JSONObject playerjson = new JSONObject();

        playerjson.put("userId", player.getUserId());
        playerjson.put("playername", player.getUsername());
        playerjson.put("role", player.getRole());

        playersarray.put(playerjson);
        

        Lobby.put("player1", playersarray);

        return Lobby;

    }

    public static JSONObject joinLobby(int port, User player){

        JSONObject Lobby = new JSONObject();

        Lobby.put("RequestType", RequestType.JOINLOBBY);

        Lobby.put("lobbyPort", port);

        JSONArray playersarray = new JSONArray();


        JSONObject playerjson = new JSONObject();

        playerjson.put("userId", player.getUserId());
        playerjson.put("playername", player.getUsername());
        playerjson.put("role", player.getRole());

        playersarray.put(playerjson);
    

        Lobby.put("player2", playersarray);

        return Lobby;

    }

    

}
