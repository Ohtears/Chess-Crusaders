package Client.Models;

import java.awt.Point;

public class Tile {

    public enum Type {
        EMPTY, NONEMPTY //FOR future improvment just in case we want to add obstacles and terrain
    }

    private Point point;

    private boolean occupied;

    private Type type;
    
    public Tile(Point point, Type type) {
        this.point = point;
        this.type = type;
        this.occupied = false;
    }

    public Point getPoint() {
        return point;
    }

    public Tile.Type getType() {
        return type;
    }

    public void setteroccupation(boolean occupation){
        this.occupied = occupation;
    }
    public boolean getteroccupation(){
        return this.occupied;
    }

}