package mechanics;

import mechanics.ui.*;
import mechanics.ui.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

public class Main {

    private static void startDefault() {
        Window window = new Window("Oscilador Bidimensional", 900, 600);
        JRootPane rootPane = window.getRootPane();
        rootPane.setLayout(new BorderLayout());

        MainPanel mainPanel = new MainPanel();
        SidePanel sidePanel = new SidePanel();
        TimePanel timePanel = new TimePanel();
        ViewPanel viewPanel = new ViewPanel();
        sidePanel.setMainPanel(mainPanel);
        timePanel.setMainPanel(mainPanel);
        viewPanel.setMainPanel(mainPanel);
        mainPanel.setTimePanel(timePanel);

        rootPane.add(sidePanel, BorderLayout.EAST);
        rootPane.add(timePanel, BorderLayout.NORTH);
        rootPane.add(mainPanel, BorderLayout.CENTER);
        rootPane.add(viewPanel, BorderLayout.WEST);
        window.display();

        mainPanel.begin();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                     UnsupportedLookAndFeelException e) {
                throw new RuntimeException(e);
            }
            startDefault();
        });
        Path2D path2D = new Path2D.Double();
        path2D.moveTo(2, 1);
        path2D.lineTo(3.0, 1.0);
        System.out.println(path2D.getBounds2D());
        AffineTransform at = new AffineTransform();
        at.scale(0.9, 1.0);
        at.translate(path2D.getBounds2D().getX() * (1/0.9 - 1), 1.0);
        System.out.println(at.toString());
        path2D.transform(at);
        System.out.println(path2D.getBounds2D());
    }
}
