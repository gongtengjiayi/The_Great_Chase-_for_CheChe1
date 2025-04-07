package com.TJ.ui.ending;

import com.TJ.ui.ScenesPanel;
import com.TJ.ui.Tool;

import javax.swing.*;
import java.awt.*;

public class EndingApplication {
    private EndingApplication() {}
    private static JFrame endingFrame;
    private static CardLayout cardLayout;
    private static JPanel cardPanel;
    public static void initialize(int score) {
        endingFrame = new JFrame("结束了！");
        endingFrame.setSize(1600, 900);
        endingFrame.setLocationRelativeTo(null);
        endingFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        endingFrame.setResizable(false);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        if (score>=80){
            cardPanel.add(new ScenesPanel(Tool.GoodEnd),"good");
            cardLayout.show(cardPanel, "good");
        }
        else if (score>=60){
            cardPanel.add(new ScenesPanel(Tool.NormalEnd),"Normal");
            cardLayout.show(cardPanel, "Normal");
        }
        else {
            cardPanel.add(new ScenesPanel(Tool.BadEnd),"Bad");
            cardLayout.show(cardPanel, "Bad");
        }
        endingFrame.add(cardPanel);
        endingFrame.setVisible(true);
    }

    public static void switchToPanel(String panelName) {
        cardPanel.add(new FinalPanel(), "endingFrame");
        cardLayout.show(cardPanel, panelName);
    }

    public static void Close(){
        endingFrame.dispose();
    }
}
