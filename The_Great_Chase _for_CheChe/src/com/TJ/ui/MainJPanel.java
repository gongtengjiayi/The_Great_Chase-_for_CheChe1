package com.TJ.ui;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class MainJPanel extends JPanel {

    private final Clip clips;//背景音乐
    //主界面
    public MainJPanel()  {
        setLayout(null);
        setSize(1600, 900);
        initButton();
        initImage();
        clips = initMusic();
    }

    //初始化按钮
    private void initButton() {
        JButton startButton =createButton("Resource/0zhujiemian/startbutton.png",683,235,820,191);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tool.playClickSound("Resource/0zhujiemian/startbutton.wav");
                clips.close();
                MainApplication.switchToPanel("Prologue");
            }
        });
        this.add(startButton);

        //关闭游戏
        JButton exitButton=createButton("Resource/0zhujiemian/exitbutton.png",683,555,820,191);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tool.playClickSound("Resource/0zhujiemian/startbutton.wav");
                System.exit(0);
            }
        });
        this.add(exitButton);
    }

    //创建按钮 参数为路径、位置、宽高
    private  JButton createButton(String filename,int x,int y, int width,int height) {
        // 创建按钮并设置缩放后的图片
        JButton button = new JButton(Tool.createImageIcon(filename,width,height));
        button.setBounds(x, y, width, height);
        button.setBorderPainted(false); // 移除按钮边框
        button.setContentAreaFilled(false); // 设置按钮背景透明
        button.setFocusPainted(false);
        return button;
    }

    //初始化图片
    private void initImage() {
        //人物立绘
        JLabel label = new JLabel(Tool.createImageIcon("Resource/0zhujiemian/happy.png",1380,1950));;
        label.setBounds(-334, 80,1380,1950);
        this.add(label);

        //标题
        label=new JLabel(Tool.createImageIcon("Resource/0zhujiemian/title.png",1600,800));
        label.setBounds(-17, -290,1600,800);
        this.add(label);

        //背景
        label= new JLabel(Tool.createImageIcon("Resource/0zhujiemian/0.jpg",this.getWidth(),this.getHeight()));;
        label.setBounds(0, 0, this.getWidth(), this.getHeight());
        this.add(label);


    }

    //初始化音乐
    private Clip initMusic() {
        Clip clip=null;
        try {
            // 加载音频文件
            FileInputStream fileInputStream = new FileInputStream("Resource/0zhujiemian/Blush - Fusq MYLK.wav");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedInputStream);

            // 获取音频格式
            AudioFormat format = audioInputStream.getFormat();

            // 获取音频行
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);

            // 打开音频行并开始播放
            clip.open(audioInputStream);
            clip.start();

            // 循环播放
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return clip;
    }
    public void cleanup() {
        clips.stop();
        // 移除所有组件帮助GC
        removeAll();
    }
}
