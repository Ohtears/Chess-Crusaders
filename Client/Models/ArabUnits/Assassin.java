package Client.Models.ArabUnits;

import Client.Models.Unit;

public class Assassin implements Unit  {
    private String name;
    private int hp;
    private int maxHp;
    private int strength;
    private int rangemovement;
    private String image;
    private String sound;
    private String side;
    private int[][] direction;

    public Assassin() {
        this.name = "Assassin";
        this.hp = 50;
        this.maxHp = 50;
        this.strength = 0;
        this.direction = new int[][]{{-1, 0}, {-1,-1}, {-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
        this.rangemovement = 1;
        this.image = "Client\\Assets\\Images\\ArabUnits\\Assassin.png";
        this.sound = "Client\\Assets\\Audios\\ArabUnits\\Assassin\\milspeech_assasin [2].wav";
        this.side = "Islamic";

    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }
    public int getRange() {
        return rangemovement;
    }
    public String getImage() {
        return image;
    }

    public String getSound() {
        return sound;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
    public String getSide() {
        return side;
    }
    public int[][] getDirections() {
        return direction;
    }
    public int getStrength() {
        return strength;
    }
}
