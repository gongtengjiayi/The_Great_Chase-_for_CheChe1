package com.TJ.ui;

import com.TJ.ui.ending.EndingApplication;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class ScenesPanel extends JPanel {
    private int sceneKind;
    private JTextArea dialogText;//对话内容的JLabel
    private JLabel backgroundLabel; // 用于显示背景图的 JLabel
    private JLabel nameLabel; // 用于显示人物名称的 JLabel
    private int sceneIndex = 0; // 当前场景索引
    private Clip backgroundMusic; // 背景音乐
    private Clip voiceClip;//人物配音
    private String[][] scenes;

    public ScenesPanel() {}
    public ScenesPanel(int sceneKind) {
        this.sceneKind = sceneKind;
        this.scenes=Tool.getScenes(sceneKind);
        setLayout(null);
        setSize(1600, 900);

        initDialogPanel();
        initNextButton();
        initImage();
        initMusic();
    }

    private void initDialogPanel() {
        nameLabel=Tool.createNameLabel();
        dialogText=Tool.createDialogText(scenes[sceneIndex][1]);
        JPanel dialogPanel = Tool.createDialogPanel(nameLabel, dialogText);
        this.add(dialogPanel);
    }

    // 初始化图片
    private void initImage() {
        // 背景
        backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0, 0, 1600, 900);
        this.add(backgroundLabel);
        // 加载初始场景
        loadScene(sceneIndex);
    }

    //加载场景（背景图，对话内容，人物名称，配音）
    private void loadScene(int index) {
        if (index >= 0 && index < scenes.length) {
            // 更新背景图
            backgroundLabel.setIcon(Tool.createImageIcon(scenes[index][0], 1600, 900));
            // 更新对话内容
            dialogText.setText(scenes[index][1]);
            // 更新人物名称
            nameLabel.setText(scenes[index][2]);
            //更新配音
            playVoice(scenes[index][3]);
            this.repaint();

        }
    }

    private void initNextButton() {
        JButton nextButton = Tool.creatNextButton();
        nextButton.addActionListener(e -> {
            // 切换到下一个场景
            if (sceneIndex < scenes.length - 1) {
                sceneIndex++;
                Tool.playClickSound("Resource/audio/Game/click.wav");
                loadScene(sceneIndex); // 加载新场景
            } else {
                stopVoice();
                stopBackgroundMusic();
                switch (sceneKind){
                    case Tool.Prologue:{
                        MainApplication.Close();
                        new gameFrame();
                        break;
                    }
                    default:{
                        EndingApplication.switchToPanel("endingFrame");
                        break;
                    }
                }

            }
        });
        this.add(nextButton);
    }


    private void initMusic() {
        backgroundMusic=Tool.playBackgroundMusic("Resource/default.wav");
    }
    //停止背景音乐
    private void stopBackgroundMusic() {
        if (backgroundMusic != null && backgroundMusic.isRunning()) {
            backgroundMusic.stop();
        }
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



}
