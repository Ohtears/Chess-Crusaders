package Client.Models.ArabUnits;
import java.nio.file.Path;
import java.nio.file.Paths;
import Client.Models.Unit;

public class CastleKeepA implements Unit {

    private String name;
    private int hp;
    private int maxHp;
    private int strength;
    private int rangemovement;
    private String image;
    private String sound;
    private String side;
    private int[][] direction;

    public CastleKeepA() {
        this.name = "CastleKeep";
        this.hp = 50;
        this.maxHp = 50;
        this.strength = 1;
        this.direction = new int[][]{{-1, 0}, {-1,-1}, {-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
        this.rangemovement = 1;

        Path path_image = Paths.get("Client", "Assets", "Images", "GameBoard", "castlekeep.png");
        Path path_sound = Paths.get("Client", "Assets", "Audios", "CastleKeep", "castlekeep.wav");
        this.image = path_image.toString();
        this.sound = path_sound.toString();
    
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
