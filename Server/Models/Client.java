package Server.Models;

public class Client extends User {

    private int port;


    public Client(int userId, String username, String role, int port) {
        super(userId, username, role);
        this.port = port;

    }
    
    public int getport(){
        return port;

    }
    public User getUser(){
        return new User(getUserId(), getUsername(), getRole());
    }
}
