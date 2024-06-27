package Client.UI.Views.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameBoard extends JFrame {

    private JPanel boardPanel;

    public GameBoard() {
        setTitle("Game Board Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);

        ImageIcon backgroundImage = new ImageIcon("Client\\Assets\\Images\\GameBoard\\gameboard.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);

        boardPanel = new JPanel(new GridLayout(8, 8));
        boardPanel.setOpaque(false);
        boardPanel.setPreferredSize(new Dimension(800, 800));

        addUnits();

        boardPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Component component = boardPanel.getComponentAt(e.getPoint());
                if (component instanceof JLabel) {
                    // Handle click on unit
                    JLabel clickedLabel = (JLabel) component;
                    // Show valid moves or perform action
                    // Example: showValidMoves(clickedLabel);
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
        for (int i = 0; i < 64; i++) {
            JLabel unitLabel = new JLabel(new ImageIcon("path/to/unit/image.png"));
            unitLabel.setSize(100, 100);
            boardPanel.add(unitLabel);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameBoard());
    }
}
