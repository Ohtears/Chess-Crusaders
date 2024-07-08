package Client.Controllers;

import Client.Models.Tile;
import Client.Models.Unit;
import Client.Models.LayoutGame.Layout;
import Client.Models.LayoutGame;

public class GameBoard {

    public static Tile[][] board = new Tile[8][8];

    public void initializeBoard(Layout layout, boolean isPlayer1) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Tile(i, j, Tile.Type.EMPTY);
            }
        }
        new LayoutGame(layout, this, isPlayer1);
    }

    public void placeUnit(Unit unit, int x, int y) {
        if (x < 0 || x >= 8 || y < 0 || y >= 8) {
            throw new IllegalArgumentException("Invalid board coordinates.");
        }

        Tile tile = board[x][y];
        if (!tile.isOccupied()) {
            tile.setUnit(unit);
        } else {
            System.out.println("Tile is already occupied.");
        }
    }

    public void moveUnit(int startX, int startY, int endX, int endY) {
        if (startX < 0 || startX >= 8 || startY < 0 || startY >= 8 || endX < 0 || endX >= 8 || endY < 0 || endY >= 8) {
            throw new IllegalArgumentException("Invalid board coordinates.");
        }

        Tile startTile = board[startX][startY];
        Tile endTile = board[endX][endY];

        if (startTile.isOccupied()) {
            Unit unit = startTile.getUnit();
            startTile.setUnit(null);
            if (endTile.isOccupied()) {
                removeUnit(endX, endY);
            }
            endTile.setUnit(unit);
        } else {
            System.out.println("Invalid move.");
        }
    }

    public boolean isOccupied(int x, int y) {
        return board[x][y].isOccupied();
    }

    public void removeUnit(int x, int y) {
        board[x][y].setUnit(null);
    }
}
