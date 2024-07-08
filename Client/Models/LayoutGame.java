package Client.Models;

import Client.Controllers.GameBoard;
import Client.Models.ArabUnits.Arabian_Archer;
import Client.Models.ArabUnits.Arabian_Swordsman;
import Client.Models.ArabUnits.Assassin;
import Client.Models.ArabUnits.CastleKeepA;
import Client.Models.ArabUnits.HorseArcher;
import Client.Models.ArabUnits.SassanidKnight;
import Client.Models.ArabUnits.Slave;
import Client.Models.CrusaderUnits.CastleKeepC;
import Client.Models.CrusaderUnits.Conscript;
import Client.Models.CrusaderUnits.Crossbowman;
import Client.Models.CrusaderUnits.Maceman;
import Client.Models.CrusaderUnits.Ranger;
import Client.Models.CrusaderUnits.Swordsman;
import Client.Models.CrusaderUnits.TemplarKnight;

public class LayoutGame {

    public enum Layout {
        DEFAULT,
        CUSTOM
    }

    public LayoutGame(Layout layout, GameBoard gameBoard, boolean isPlayer1) {
        initializeUnitPlacements(layout, gameBoard, isPlayer1);
    }

    private void initializeUnitPlacements(Layout layout, GameBoard gameBoard, boolean isPlayer1) {
        switch (layout) {
            case DEFAULT:
                initializeDefaultLayout(gameBoard, isPlayer1);
                break;
            case CUSTOM:
                initializeCustomLayout(gameBoard, isPlayer1);
                break;
            default:
                throw new IllegalArgumentException("Unknown layout: " + layout);
        }
    }

    private void initializeDefaultLayout(GameBoard gameBoard, boolean isPlayer1) {
        // Crusader side units
        placeUnit(gameBoard, new Maceman(), 7, 0, isPlayer1);
        placeUnit(gameBoard, new Crossbowman(), 7, 1, isPlayer1);
        placeUnit(gameBoard, new Ranger(), 7, 2, isPlayer1);
        placeUnit(gameBoard, new TemplarKnight(), 7, 3, isPlayer1);
        placeUnit(gameBoard, new CastleKeepC(), 7, 4, isPlayer1);
        placeUnit(gameBoard, new Ranger(), 7, 5, isPlayer1);
        placeUnit(gameBoard, new Crossbowman(), 7, 6, isPlayer1);
        placeUnit(gameBoard, new Maceman(), 7, 7, isPlayer1);

        placeUnit(gameBoard, new Conscript(), 6, 0, isPlayer1);
        placeUnit(gameBoard, new Conscript(), 6, 1, isPlayer1);
        placeUnit(gameBoard, new Conscript(), 6, 2, isPlayer1);
        placeUnit(gameBoard, new Swordsman(), 6, 3, isPlayer1);
        placeUnit(gameBoard, new Swordsman(), 6, 4, isPlayer1);
        placeUnit(gameBoard, new Conscript(), 6, 5, isPlayer1);
        placeUnit(gameBoard, new Conscript(), 6, 6, isPlayer1);
        placeUnit(gameBoard, new Conscript(), 6, 7, isPlayer1);

        // Islamic side units
        placeUnit(gameBoard, new Assassin(), 0, 0, isPlayer1);
        placeUnit(gameBoard, new HorseArcher(), 0, 1, isPlayer1);
        placeUnit(gameBoard, new Arabian_Archer(), 0, 2, isPlayer1);
        placeUnit(gameBoard, new SassanidKnight(), 0, 3, isPlayer1);
        placeUnit(gameBoard, new CastleKeepA(), 0, 4, isPlayer1);
        placeUnit(gameBoard, new Arabian_Archer(), 0, 5, isPlayer1);
        placeUnit(gameBoard, new HorseArcher(), 0, 6, isPlayer1);
        placeUnit(gameBoard, new Assassin(), 0, 7, isPlayer1);

        placeUnit(gameBoard, new Slave(), 1, 0, isPlayer1);
        placeUnit(gameBoard, new Slave(), 1, 1, isPlayer1);
        placeUnit(gameBoard, new Slave(), 1, 2, isPlayer1);
        placeUnit(gameBoard, new Arabian_Swordsman(), 1, 3, isPlayer1);
        placeUnit(gameBoard, new Arabian_Swordsman(), 1, 4, isPlayer1);
        placeUnit(gameBoard, new Slave(), 1, 5, isPlayer1);
        placeUnit(gameBoard, new Slave(), 1, 6, isPlayer1);
        placeUnit(gameBoard, new Slave(), 1, 7, isPlayer1);
    }

    private void placeUnit(GameBoard gameBoard, Unit unit, int row, int col, boolean isPlayer1) {
        int finalRow = isPlayer1 ? row : 7 - row;
        gameBoard.placeUnit(unit, finalRow, col);
    }

    private void initializeCustomLayout(GameBoard gameBoard, boolean isPlayer1) {
        // Implement custom layout initialization here
    }
}
