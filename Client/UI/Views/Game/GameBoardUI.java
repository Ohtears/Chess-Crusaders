package Client.UI.Views.Game;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
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

    public GameBoardUI() {
        setTitle("Chess-Crusader");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);

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

                    if (tile.isOccupied()) {
                        Unit unit = tile.getUnit();
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
            highlight.setBackground(new Color(0, 255, 0, 128)); // Semi-transparent green
            highlightedSquares.add(highlight);
        }
    
        boardPanel.revalidate();
        boardPanel.repaint();
    }

    private List<Point> getValidMoves(Unit unit, int x, int y) {
        List<Point> validMoves = new ArrayList<>();
    
        int[][] directions;
        if (unit.getSide().equals("Crusader")) {
            directions = new int[][]{{-1, 0}};
        } else if (unit.getSide().equals("Islamic")) {
            directions = new int[][]{{1, 0}};
        } else {
            directions = new int[][]{};
        }
    
        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            
            if (newX >= 0 && newX < 8 && newY >= 0 && newY < 8 && !GameBoard.board[newX][newY].isOccupied()) {
                validMoves.add(new Point(newX, newY));
            }
        }
    
        return validMoves;
    }

    private void clearHighlights() {
        for (JLabel square : highlightedSquares) {
            square.setBackground(null);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameBoardUI());
    }
}
