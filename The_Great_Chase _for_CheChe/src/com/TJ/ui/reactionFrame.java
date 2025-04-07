package com.TJ.ui;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

public class reactionFrame extends JFrame{
    private  String[][] reaction;
    private JButton nextButton; // 下一步按钮
    private Clip voiceClip;//人物配音
    private int reactionIndex;

    public reactionFrame() throws HeadlessException {}

    public reactionFrame(int reactionIndex, Runnable onCloseCallback,int reactionType) throws HeadlessException{
        this.reactionIndex = reactionIndex;
        reaction=Tool.getReaction(reactionType);
        initJFrame();
        initNextButton();
        initDialogPanel();
        initImage();
        initVoice();
        ifClose(onCloseCallback);
        this.setVisible(true);
    }
    private void ifClose(Runnable onCloseCallback) {
        // 设置关闭事件监听器
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                // 在界面关闭时执行回调函数
                onCloseCallback.run();
            }
        });
    }
    private void initVoice() {
        playVoice(reaction[reactionIndex][3]);
    }
    //对话框
    private void initDialogPanel() {
        JPanel dialogPanel = new JPanel();
        dialogPanel.setBounds(0, 520, 1590, 340); // 设置位置和大小
        dialogPanel.setLayout(new BorderLayout());
        dialogPanel.setOpaque(false);

        // 创建人物名称标签
        JLabel nameLabel = new JLabel();
        nameLabel.setForeground(Color.black); // 设置文本颜色
        nameLabel.setFont(new Font("微软雅黑", Font.BOLD, 48)); // 设置字体
        nameLabel.setText(reaction[reactionIndex][2]);//设置初始文本
        nameLabel.setHorizontalAlignment(SwingConstants.LEFT); // 左对齐
        nameLabel.setOpaque(false); // 设置标签背景为透明
        nameLabel.setBorder(BorderFactory.createEmptyBorder(2, 65, 20, 10)); // 设置边距
        nameLabel.setFocusable(false); // 禁止获取焦点

        // 初始化对话文本区域
        JTextArea dialogText = new JTextArea() {
            @Override
            public void setSelectionStart(int selectionStart) {
                // 禁止设置选择起始位置
            }

            @Override
            public void setSelectionEnd(int selectionEnd) {
                // 禁止设置选择结束位置
            }

            @Override
            public void select(int selectionStart, int selectionEnd) {
                // 禁止选择文本
            }
        };
        dialogText.setText(reaction[reactionIndex][1]); // 设置初始文本
        dialogText.setForeground(Color.black);
        dialogText.setBackground(new Color(0, 0, 0, 0)); // 设置文本区域背景为透明
        dialogText.setBorder(BorderFactory.createEmptyBorder(5, 50, 0, 0));
        dialogText.setEditable(false);
        dialogText.setLineWrap(true);
        dialogText.setWrapStyleWord(true);
        dialogText.setFont(new Font("微软雅黑", Font.PLAIN, 38));

        // 将人物名称标签和文本区域添加到对话框面板
        dialogPanel.add(nameLabel, BorderLayout.NORTH); // 人物名称放在顶部
        dialogPanel.add(dialogText, BorderLayout.CENTER); // 文本区域放在中心
        this.add(dialogPanel);
    }

    //设置背景
    private void initImage() {
        JLabel label = new JLabel(Tool.createImageIcon(reaction[reactionIndex][0],1600,900));;
        label.setBounds(0, 0,1600,900);
        this.add(label);
    }

    //下一步按钮
    private void initNextButton() {
        nextButton =Tool.creatNextButton();
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tool.playClickSound("Resource/audio/Game/click.wav");
                stopVoice();
                reactionFrame.this.dispose();
            }
        });
        this.add(nextButton);
    }

    //播放配音
    private void playVoice(String voicePath) {
        // 停止当前配音
        stopVoice();

        try {
            // 加载音频文件
            File voiceFile = new File(voicePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(voiceFile);

            // 创建 Clip 对象
            voiceClip = AudioSystem.getClip();
            voiceClip.open(audioInputStream);

            // 开始播放
            voiceClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "无法加载配音文件！");
        }
    }
    //停止配音
    private void stopVoice() {
        if (voiceClip != null && voiceClip.isRunning()) {
            voiceClip.stop();
        }
    }

    private void initJFrame() {
        this.setTitle("车车表现得很开心");
        this.setSize(1600, 900);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        // 禁用窗口手动调整大小
        this.setResizable(false);
    }
}
