package Client.UI.Views.Game;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Client.Controllers.GameBoard;
import Client.Models.*;

public class GameBoardUI extends JFrame {

    private JPanel boardPanel;
    private GameBoard gameBoard;
    private List<JLabel> highlightedSquares = new ArrayList<>();
    private Clip currentClip;
    private Unit selectedUnit;
    private int selectedX, selectedY;

    public GameBoardUI() {
        setTitle("Chess-Crusader");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);

        // Set the background image
        ImageIcon backgroundImage = new ImageIcon("Client/Assets/Images/GameBoard/gameboard.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);

        boardPanel = new JPanel(new GridLayout(8, 8));
        boardPanel.setOpaque(false);
        boardPanel.setPreferredSize(new Dimension(800, 800));

        gameBoard = new GameBoard();
        gameBoard.initializeBoard(LayoutGame.Layout.DEFAULT);

        addUnits();

        boardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Component component = boardPanel.getComponentAt(e.getPoint());
                if (component instanceof JLabel) {
                    JLabel clickedLabel = (JLabel) component;
                    int index = boardPanel.getComponentZOrder(clickedLabel);
                    int x = index / 8;
                    int y = index % 8;
                    Tile tile = GameBoard.board[x][y];

                    if (highlightedSquares.contains(clickedLabel)) {
                        moveUnit(selectedUnit, selectedX, selectedY, x, y);
                    } else if (tile.isOccupied()) {
                        Unit unit = tile.getUnit();
                        selectedUnit = unit;
                        selectedX = x;
                        selectedY = y;
                        showValidMoves(unit, x, y);
                        playUnitSound(unit.getSound());
                    }
                }
            }
        });

        setLayout(new BorderLayout());
        add(backgroundLabel, BorderLayout.CENTER);
        backgroundLabel.setLayout(new BorderLayout());
        backgroundLabel.add(boardPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void addUnits() {
        Tile[][] board = GameBoard.board;
        boardPanel.removeAll();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                JLabel unitLabel = new JLabel();
                Tile tile = board[i][j];
                if (tile.isOccupied()) {
                    Unit unit = tile.getUnit();
                    ImageIcon unitIcon = new ImageIcon(unit.getImage());
                    unitLabel.setIcon(unitIcon);
                }
                unitLabel.setSize(100, 100);
                unitLabel.setHorizontalAlignment(SwingConstants.CENTER);
                unitLabel.setVerticalAlignment(SwingConstants.CENTER);
                unitLabel.setOpaque(true);
                unitLabel.setBorder(new LineBorder(Color.BLACK)); 
                unitLabel.setBackground(new Color(255, 255, 255, 128)); 
                boardPanel.add(unitLabel);
            }
        }
        boardPanel.revalidate();
        boardPanel.repaint();
    }

    private void showValidMoves(Unit unit, int x, int y) {
        clearHighlights();
    
        List<Point> validMoves = getValidMoves(unit, x, y);
    
        for (Point move : validMoves) {
            int newX = move.x;
            int newY = move.y;
            int index = newX * 8 + newY;
            
            JLabel highlight = (JLabel) boardPanel.getComponent(index);
            if (gameBoard.isOccupied(newX, newY)) {
                Unit targetUnit = GameBoard.board[newX][newY].getUnit();

                if (unit.getStrength() >= targetUnit.getStrength() && !unit.getSide().equals(targetUnit.getSide()) ) {
                    highlight.setBackground(new Color(255, 255, 0, 109)); 
                } else if (!unit.getSide().equals(targetUnit.getSide())) {
                    highlight.setBackground(new Color(255, 0, 0, 109)); 
                }
            } else {
                highlight.setBackground(new Color(209, 176, 81, 109)); 
            }
            highlightedSquares.add(highlight);
        }
    
        boardPanel.revalidate();
        boardPanel.repaint();
    }
    
    private List<Point> getValidMoves(Unit unit, int x, int y) {
        List<Point> validMoves = new ArrayList<>();

        int[][] directions = unit.getDirections();
        int rangeMovement = unit.getRange();

        for (int[] dir : directions) {
            for (int i = 1; i <= rangeMovement; i++) {
                int newX = x + dir[0] * i;
                int newY = y + dir[1] * i;

                if (newX >= 0 && newX < 8 && newY >= 0 && newY < 8) {
                    validMoves.add(new Point(newX, newY));
                    if (gameBoard.isOccupied(newX, newY)) {
                        break; 
                    }
                } else {
                    break; 
                }
            }
        }

        return validMoves;
    }

    private void clearHighlights() {
        for (JLabel square : highlightedSquares) {
            square.setBackground(new Color(255, 255, 255, 128));
        }
        highlightedSquares.clear();
    }

    private void playUnitSound(String soundFile) {
        try {
            if (currentClip != null && currentClip.isRunning()) {
                currentClip.stop();
                currentClip.close();
            }

            File soundPath = new File(soundFile);
            currentClip = AudioSystem.getClip();
            currentClip.open(AudioSystem.getAudioInputStream(soundPath));
            currentClip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void moveUnit(Unit unit, int startX, int startY, int endX, int endY) {
        if (gameBoard.isOccupied(endX, endY)) {
            Tile endTile = GameBoard.board[endX][endY];
            Unit targetUnit = endTile.getUnit();
            if (unit.getStrength() >= targetUnit.getStrength() && !unit.getSide().equals(targetUnit.getSide())) {
                gameBoard.removeUnit(endX, endY);
            } else {
                return; 
            }
        }
        gameBoard.moveUnit(startX, startY, endX, endY);
        addUnits();
        clearHighlights();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameBoardUI());
    }
}
