package Server.Connections;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import Server.Models.Client;
import Server.Models.LobbyInstance;
import Server.Models.User;


public class JsonConvertor {
    
    public static RequestType JsonParserRequest(JSONObject jsonObject){

        RequestType requestType = RequestType.valueOf(jsonObject.getString("RequestType"));

        return requestType;
    }

    public static LobbyInstance JsonParser(JSONObject jsonObject){

        List<User> players = new ArrayList<>();

        int port = jsonObject.getInt("lobbyPort");

        JSONArray jsonArray = jsonObject.getJSONArray("player");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject userJson = jsonArray.getJSONObject(i);
            
            int userId = userJson.getInt("userId");
            String username = userJson.getString("username");
            String role = userJson.getString("role");

            players.add(new User(userId, username, role));
            
        }
        return new LobbyInstance(players, port);

    }
    @SuppressWarnings("unused")
    public static Client JsonParserClient(JSONObject jsonObject){

        int port = jsonObject.getInt("lobbyPort");

        JSONArray jsonArray = jsonObject.getJSONArray("player");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject userJson = jsonArray.getJSONObject(i);
            
            int userId = userJson.getInt("userId");
            String username = userJson.getString("username");
            String role = userJson.getString("role");

            return new Client(userId, username, role, port);
            
        }
    
        return null;

    }    

}
