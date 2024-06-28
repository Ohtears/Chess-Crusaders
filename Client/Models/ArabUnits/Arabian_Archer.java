package Client.Models.ArabUnits;

import Client.Models.Unit;

public class Arabian_Archer implements Unit  {

    private String name;
    private int hp;
    private int maxHp;
    private int strength;
    private int rangemovement;
    private String image;
    private String sound;
    private String side;
    private int[][] direction;

    public Arabian_Archer() {
        this.name = "Arabian_Archer";
        this.hp = 50;
        this.maxHp = 50;
        this.strength = 2;
        this.direction = new int[][]{{-1, 0}, {-1,-1}, {-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
        this.rangemovement = 1;
        this.image = "Client\\Assets\\Images\\ArabUnits\\ArabianArcher.png";
        this.sound = "Client\\Assets\\Audios\\ArabUnits\\Arabian_Archer\\milspeech_arabarch [3].wav";
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




