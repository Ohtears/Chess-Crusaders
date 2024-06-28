package Client.Models;

public interface Unit {
    String getName();
    int getHp();
    int getMaxHp();
    int getStrength();
    int getRange();
    String getImage();
    String getSound();
    void setHp(int hp);
    String getSide();
    int[][] getDirections();
}
