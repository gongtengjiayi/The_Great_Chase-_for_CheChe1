package com.TJ.ui;
import javax.swing.*;
import java.awt.*;
public class MainApplication {
    private static JFrame mainFrame;
    private static CardLayout cardLayout;
    private static JPanel cardPanel;
    public static void initialize() {
        mainFrame = new JFrame("表白车车大作战！");
        mainFrame.setSize(1600, 900);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add(new MainJPanel(), "Main");
        cardPanel.add(new ScenesPanel(Tool.Prologue), "Prologue");

        mainFrame.add(cardPanel);
        mainFrame.setVisible(true);
    }

    public static void switchToPanel(String panelName) {
        cardLayout.show(cardPanel, panelName);
    }

    public static void Close(){
        mainFrame.dispose();
    }
}
