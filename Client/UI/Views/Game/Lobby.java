package Client.UI.Views.Game;

import javax.swing.*;
import java.awt.*;

public class Lobby extends JPanel {

    public Lobby() {
        setLayout(new BorderLayout());
        
        // Top left panel for modifiers
        JPanel modifiersPanel = createModifiersPanel();
        add(modifiersPanel, BorderLayout.WEST);
        
        // Bottom left panel for players involved
        JPanel playersPanel = createPlayersPanel();
        add(playersPanel, BorderLayout.SOUTH);
        
        // Right panel for possible game layouts
        JPanel layoutsPanel = createLayoutsPanel();
        add(layoutsPanel, BorderLayout.CENTER);
    }

    private JPanel createModifiersPanel() {
        JPanel modifiersPanel = new JPanel();
        modifiersPanel.setBackground(Color.LIGHT_GRAY);
        modifiersPanel.setPreferredSize(new Dimension(200, getHeight()));
        // Add components and logic for modifiers panel here
        
        return modifiersPanel;
    }

    private JPanel createPlayersPanel() {
        JPanel playersPanel = new JPanel();
        playersPanel.setBackground(Color.WHITE);
        playersPanel.setPreferredSize(new Dimension(getWidth(), 200));
        // Add components and logic for players panel here
        
        return playersPanel;
    }

    private JPanel createLayoutsPanel() {
        JPanel layoutsPanel = new JPanel();
        layoutsPanel.setBackground(Color.WHITE);
        // Add components and logic for layouts panel here
        
        return layoutsPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Lobby");
            frame.setSize(960, 540);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            Lobby lobby = new Lobby();
            frame.add(lobby);
            
            frame.setVisible(true);
        });
    }
}
