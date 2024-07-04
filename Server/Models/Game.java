package Server.Models;

import Client.Models.User;

public class Game {
    
    private int gameId;
    private int turn;
    private User CrusaderPlayer;
    private User MuslimPlayer;
    private int CrusaderPlayerScore;
    private int MuslimPlayerScore;
    

    public Game(int turn, int gameId, User CrusaderPlayer, User MuslimPlayer, int CrusaderPlayerScore, int MuslimPlayerScore) {

        this.turn = turn;
        this.gameId = gameId;
        this.CrusaderPlayer = CrusaderPlayer;
        this.MuslimPlayer = MuslimPlayer;
        this.CrusaderPlayerScore = CrusaderPlayerScore;
        this.MuslimPlayerScore = MuslimPlayerScore;

    }

    public int getTurn() {
        return turn;
    }

    public int getGameId() {
        return gameId;
    }

    public User getCrusaderPlayer() {
        return CrusaderPlayer;
    }

    public User getMuslimPlayer() {
        return MuslimPlayer;
    }

    public int getCrusaderPlayerScore() {
        return CrusaderPlayerScore;
    }

    public int getMuslimPlayerScore() {
        return MuslimPlayerScore;
    }




}
