package Client.Models.ArabUnits;

import java.awt.Point;

import Client.Models.Unit;

public class SassanidKnight implements Unit  {
    private String name;
    private int hp;
    private int maxHp;
    private int attack;
    private Point movement;
    private int rangemovement;
    private Point attackRange;
    private String image;
    private String sound;
    private String side;

    public SassanidKnight() {
        this.name = "SassanidKnight";
        this.hp = 50;
        this.maxHp = 50;
        this.attack = 10;
        this.movement = new Point(1, 1);
        this.rangemovement = 1;
        this.attackRange = new Point(1, 1);
        this.image = "Client\\Assets\\Images\\ArabUnits\\SassanidKnight.png";
        this.sound = "Client\\Assets\\Audios\\ArabUnits\\SassanidKnight\\milspeech_sassanidknight [3].wav";
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

    public int getAttack() {
        return attack;
    }

    public Point getMovement() {
        return movement;
    }

    public int getRange() {
        return rangemovement;
    }

    public Point getAttackRange() {
        return attackRange;
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
}
