package Client.Models;

public class Tile {

    public enum Type {
        EMPTY, NONEMPTY //FOR future improvment just in case we want to add obstacles and terrain
    }


    @SuppressWarnings("unused")
    private int x;
    @SuppressWarnings("unused")
    private int y;

    private boolean occupied;
    private Unit unit;
    private Type type;
    
    public Tile(int x, int y, Type type) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.occupied = false;
        this.unit = null;
    }

    public Tile.Type getType() {
        return type;
    }

    public void setteroccupation(boolean occupation){
        this.occupied = occupation;
    }
    public boolean isOccupied() {
        return this.occupied;
    }
    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
        this.occupied = (unit != null); 
    }
}