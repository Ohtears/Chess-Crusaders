package Server.Models;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class LobbyInstance {
    
    private int lobbyid;
    private List<User> players = new ArrayList<>();
    private int port;


    public LobbyInstance(List<User> players, int port){

        this.players = players;
        this.port = port;
        Random random = new Random();

        this.lobbyid = random.nextInt(1000);
    }
    
    public int getport(){
        return port;
    }

    public void setPlayer(User player){

        players.add(player);

    }

    public User getplayer1(){
        return players.get(0);
    }
    public User getplayer2(){
        return players.get(1);
    }

    public int getLobbyId(){
        return lobbyid;
    }

}
