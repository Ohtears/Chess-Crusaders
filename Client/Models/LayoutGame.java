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

    public LayoutGame(Layout layout, GameBoard gameBoard) {
        initializeUnitPlacements(layout, gameBoard);
    }

    private void initializeUnitPlacements(Layout layout, GameBoard gameBoard) {
        switch (layout) {
            case DEFAULT:
                initializeDefaultLayout(gameBoard);
                break;
            case CUSTOM:
                initializeCustomLayout(gameBoard);
                break;
            default:
                throw new IllegalArgumentException("Unknown layout: " + layout);
        }
    }

    private void initializeDefaultLayout(GameBoard gameBoard) {
        // Crusader side (bottom)
        gameBoard.placeUnit(new Maceman(), 7, 0);
        gameBoard.placeUnit(new Crossbowman(), 7, 1);
        gameBoard.placeUnit(new Ranger(), 7, 2);
        gameBoard.placeUnit(new TemplarKnight(), 7, 3);
        gameBoard.placeUnit(new CastleKeepC(), 7, 4);
        gameBoard.placeUnit(new Ranger(), 7, 5);
        gameBoard.placeUnit(new Crossbowman(), 7, 6);
        gameBoard.placeUnit(new Maceman(), 7, 7);

        gameBoard.placeUnit(new Conscript(), 6, 0);
        gameBoard.placeUnit(new Conscript(), 6, 1);
        gameBoard.placeUnit(new Conscript(), 6, 2);
        gameBoard.placeUnit(new Swordsman(), 6, 3);
        gameBoard.placeUnit(new Swordsman(), 6, 4);
        gameBoard.placeUnit(new Conscript(), 6, 5);
        gameBoard.placeUnit(new Conscript(), 6, 6);
        gameBoard.placeUnit(new Conscript(), 6, 7);

        // Islamic side (top)
        gameBoard.placeUnit(new Assassin(), 0, 0);
        gameBoard.placeUnit(new HorseArcher(), 0, 1);
        gameBoard.placeUnit(new Arabian_Archer(), 0, 2);
        gameBoard.placeUnit(new SassanidKnight(), 0, 3);
        gameBoard.placeUnit(new CastleKeepA(), 0, 4);
        gameBoard.placeUnit(new Arabian_Archer(), 0, 5);
        gameBoard.placeUnit(new HorseArcher(), 0, 6);
        gameBoard.placeUnit(new Assassin(), 0, 7);

        gameBoard.placeUnit(new Slave(), 1, 0);
        gameBoard.placeUnit(new Slave(), 1, 1);
        gameBoard.placeUnit(new Slave(), 1, 2);
        gameBoard.placeUnit(new Arabian_Swordsman(), 1, 3);
        gameBoard.placeUnit(new Arabian_Swordsman(), 1, 4);
        gameBoard.placeUnit(new Slave(), 1, 5);
        gameBoard.placeUnit(new Slave(), 1, 6);
        gameBoard.placeUnit(new Slave(), 1, 7);

    }

    private void initializeCustomLayout(GameBoard gameBoard) {

    }
}
