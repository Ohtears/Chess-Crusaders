package Client.Models;

import java.awt.Point;

public interface Unit {
    String getName();
    int getHp();
    int getMaxHp();
    int getAttack();
    Point getMovement();
    int getRange();
    Point getAttackRange();
    String getImage();
    String getSound();
    void setHp(int hp);
    String getSide();
}
