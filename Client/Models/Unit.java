package Client.Models;

import java.awt.Point;

public class Unit {

    private String name;
    private int hp;
    private int maxHp;
    private int attack;
    private Point movement;
    private int rangemovement;
    private Point attackRange;
    private String image;
    private String sound;

    public Unit(String name, int hp, int maxHp, int attack, Point movement, int rangemovement, Point attackRange, String image, String sound) {

        this.name = name;
        this.hp = hp;
        this.maxHp = maxHp;
        this.attack = attack;
        this.movement = movement;
        this.rangemovement = rangemovement;
        this.attackRange = attackRange;
        this.image = image;
        this.sound = sound;
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

}
