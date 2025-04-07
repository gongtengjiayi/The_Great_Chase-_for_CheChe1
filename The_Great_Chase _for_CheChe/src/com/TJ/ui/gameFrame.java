package com.TJ.ui;

import com.TJ.ui.ending.EndingApplication;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class gameFrame extends JFrame {
    private JTextArea dialogText;//对话内容的JLabel
    private JLabel backgroundLabel; // 用于显示背景图的 JLabel
    private JPanel dialogPanel;
    private JLabel nameLabel; // 用于显示人物名称的 JLabel
    private JButton nextButton; // 下一步按钮
    private final JButton[] optionButtons=new JButton[3]; // 选项按钮
    private int time=0;//时间轴
    private int questionIndex=0;
    private int optionIndex=0;
    private int score=0;
    private Clip backgroundMusic; // 背景音乐
    private Clip voiceClip;//人物配音

    private final int [][]locations={
            {196, 96},  // 第一个坐标 (x, y)
            {196, 356}, // 第二个坐标 (x, y)
            {196, 616}  // 第三个坐标 (x, y)
    };

    private final  String[] backGround={
            "Resource/Ima/Dialog/normal.png",
            "Resource/Ima/other/normal.png",
            "Resource/Ima/Dialog/normal.png",
            "Resource/Ima/other/normal.png",
            "Resource/Ima/Dialog/normal.png",
            "Resource/Ima/other/normal.png",
            "Resource/Ima/Dialog/normal.png",
            "Resource/Ima/other/normal.png",
            "Resource/Ima/Dialog/normal.png",
            "Resource/Ima/other/normal.png",
            "Resource/Ima/Dialog/normal.png",
            "Resource/Ima/other/normal.png",
            "Resource/Ima/Dialog/normal.png",
            "Resource/Ima/other/normal.png",
            "Resource/Ima/Dialog/normal.png",
            "Resource/Ima/other/normal.png",
            "Resource/Ima/Dialog/normal.png",
            "Resource/Ima/other/normal.png",
            "Resource/Ima/Dialog/normal.png",
            "Resource/Ima/other/normal.png",
            "Resource/Ima/Dialog/normal.png",
            "Resource/Ima/other/normal.png",
            "Resource/Ima/Dialog/normal.png",
            "Resource/Ima/other/normal.png",
            "Resource/Ima/Dialog/normal.png",
            "Resource/Ima/other/normal.png",
            "Resource/Ima/Dialog/normal.png",
            "Resource/Ima/other/normal.png",
            "Resource/Ima/Dialog/normal.png",
            "Resource/Ima/other/normal.png",
            "Resource/Ima/Dialog/normal.png"
    };

    //问题文本
    private final String[][] question = {
            {"“首先我先考考你的智商：x*sinx 的不定积分是多少？”", "车车","Resource/default.wav"},
            {"“假设某疾病的发病率为1%，有一种检测它的手段准确率为99%，如果一个人被检测患了这种疾病，那么他实际上生病的概率是多少？”","车车","Resource/default.wav"},
            {"“lush的中文翻译是什么”","车车","Resource/default.wav"},
            {"“已知传输的数据为1110011，生成多项式为1101，那么他的CRC码为多少？”","车车","Resource/default.wav"},
            {"“对N个记录的文件进行快速排序，所需要的辅助存储空间大致为多少？”","车车","Resource/default.wav"},
            {"“看来你的智商不错，但是你的情商又如何呢？”","车车","Resource/default.wav"},
            {"“如果我在社团活动中认识了一个男生，我们聊得比较投机，想把他当男闺蜜，你怎么做。”","车车","Resource/default.wav"},
            {"“我比较喜欢参加志愿活动，你会跟我一起去吗？”","车车","Resource/default.wav"},
            {"“那我跟你妈同时掉水里，你先救谁？”","车车","Resource/default.wav"},
            {"“最后一个问题，也是最重要的问题：你是真的喜欢我吗？”","车车","Resource/default.wav"},
            {"总算，我回答完了十个问题。","我","Resource/default.wav"},
    };

    //问题以及选项文本
    private final String[][] options = {
            {"sinx-x*cosx+C", "x*sinx-cosx+C","不是，怎么会问我高数题啊？"},
            {"50%","99%","不是，为什么会问我概率论的题目啊？"},
            {"茂盛的","废弃物","你还洋气起来了？"},
            {"11100110101","10010110010","CRC码是什么东东？"},
            {"O(log2N)","O(1)","你要我算这种东西干什么？"},
            {"我的情商一般吧","我的情商当然很高啦！","反正比你高。"},
            {"我不允许有别的男生出现在你身边！","只是社团的朋友而已，把握好分寸就行了。","你们聊的好关我什么事？"},
            {"有你，我哪里都愿意去。","可以呀！我也喜欢做志愿活动。","不去不去，我又不保研，我要志愿时长有什么用？"},
            {"当然先救你了！","当然先救我妈了","我……我不会游泳，救不了一点。"},
            {"是！是！是！","真的假不了，假的真不了","额……你这么说的话…………"}
    };

    public gameFrame() {
        initJFrame();
        initDialogPanel();
        initNextButton();
        initOptionButtons();
        initBackground();
        initMusic();
        this.setVisible(true);
    }

    private void initBackground() {
        backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0, 0, 1600, 900);
        this.add(backgroundLabel);
        backgroundLabel.setIcon(Tool.createImageIcon(backGround[0], 1600, 900));
    }

    //下一步按钮
    private void initNextButton() {
        nextButton =Tool.creatNextButton();
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tool.playClickSound("Resource/audio/Game/click.wav");
                if (time<19) {
                    time++;
                    questionIndex++;
                    showOptions();
                }
                else {
                    gameFrame.this.dispose();
                    stopBackgroundMusic();
                    EndingApplication.initialize(score);
                }
            }
        });
        this.add(nextButton);
    }

    //初始化选项按钮
    private void initOptionButtons() {
        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i] = createButton();
            optionButtons[i].setBounds(locations[i][0], locations[i][1], 1300, 180);
            optionButtons[i].setText(options[0][i]);
            this.add(optionButtons[i]);
            optionButtons[i].setVisible(false);
        }
        initButtonFunction();
    }

    private void initButtonFunction() {
        for (int i = 0; i < optionButtons.length; i++) {
            int finalI = i;
            optionButtons[i].addActionListener(e -> {
                time++;
                gameFrame.this.setVisible(false);
                switch (finalI) {
                    case 0:{
                        score+=10;
                        Tool.playClickSound("Resource/audio/Game/good.wav");
                        new reactionFrame(optionIndex,() -> gameFrame.this.setVisible(true),4);
                        break;}
                    case 1:{
                        score+=5;
                        Tool.playClickSound("Resource/audio/Game/click.wav");
                        new reactionFrame(optionIndex,() -> gameFrame.this.setVisible(true),5);
                        break;}
                    case 2:{
                        Tool.playClickSound("Resource/audio/Game/bad.wav");
                        new reactionFrame(optionIndex,() -> gameFrame.this.setVisible(true),6);
                        break;}
                }
                optionIndex++;
                hideOptions();
            });
        }
    }

    //隐藏“下一步”，显示选项
    private void showOptions() {
        stopVoice();//停止配音
        randomizeButtonPositions();//随机选项位置
        loadOptionButtons();//更新选项内容
        nextButton.setVisible(false);// 隐藏下一步按钮
        dialogPanel.setVisible(false);//隐藏对话框
        for (JButton optionButton : optionButtons) {
            optionButton.setVisible(true);
        }
        loadBackground();
    }

    private void hideOptions() {
        playVoice(question[questionIndex][2]);//更新并启动配音
        loadQuestion();//更新对话内容
        dialogPanel.setVisible(true);//对话框可见
        nextButton.setVisible(true); // 显示下一步按钮
        for (JButton button : optionButtons) {
            button.setVisible(false); // 隐藏选项按钮
        }
        loadBackground();
    }

    //更改选项文本
    private void loadOptionButtons() {
        optionButtons[0].setText(options[optionIndex][0]);
        optionButtons[1].setText(options[optionIndex][1]);
        optionButtons[2].setText(options[optionIndex][2]);
    }

    //更改问题文本
    private void loadQuestion() {
            // 更新对话内容
            dialogText.setText(question[questionIndex][0]);
            // 更新人物名称
            nameLabel.setText(question[questionIndex][1]);
    }


    private  void loadBackground(){
        backgroundLabel.setIcon(Tool.createImageIcon(backGround[time], 1600, 900));
        this.repaint();
    }

    //将按钮随机摆放
    private void randomizeButtonPositions() {
        java.util.List<Point> points = new ArrayList<>();
        for (int[] location : locations) {
            points.add(new Point(location[0], location[1]));
        }
        Collections.shuffle(points);

        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i].setBounds(points.get(i).x, points.get(i).y, 1300, 180);
        }
    }

    private  JButton createButton() {
        ImageIcon originalIcon = new ImageIcon("Resource/optionbutton.png");
        Image originalImage = originalIcon.getImage();

        // 使用 BufferedImage 缩放图片
        BufferedImage scaledImage = new BufferedImage(1300, 180, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = scaledImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(originalImage, 0, 0, 1300, 180, null);
        g2d.dispose();


        // 创建按钮并设置缩放后的图片
        JButton button = new JButton(new ImageIcon(scaledImage));
        button.setBorderPainted(false); // 移除按钮边框
        button.setContentAreaFilled(false); // 设置按钮背景透明
        button.setFont(new Font("微软雅黑", Font.BOLD, 36)); // 设置字体
        button.setForeground(Color.BLACK); // 设置文本颜色
        button.setHorizontalTextPosition(SwingConstants.CENTER); // 水平居中
        button.setVerticalTextPosition(SwingConstants.CENTER); // 垂直居中
        button.setFocusPainted(false);
        return button;
    }

    //初始化背景音乐
    private void initMusic() {
        backgroundMusic=Tool.playBackgroundMusic("Resource/audio/Game/_～モデラート 2001-杉森雅和.wav");
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

    //初始化界面
    private void initJFrame() {
        this.setTitle("先别管那么多了，赶紧答题吧！");
        this.setSize(1600, 900);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        // 禁用窗口手动调整大小
        this.setResizable(false);
    }

    private void initDialogPanel(){
        // 创建人物名称标签
        dialogPanel = new JPanel();
        dialogPanel.setBounds(0, 520, 1590, 340); // 设置位置和大小
        dialogPanel.setLayout(new BorderLayout());
        dialogPanel.setOpaque(false);

        nameLabel = new JLabel();
        nameLabel.setForeground(Color.black); // 设置文本颜色
        nameLabel.setFont(new Font("微软雅黑", Font.BOLD, 48)); // 设置字体
        nameLabel.setHorizontalAlignment(SwingConstants.LEFT); // 左对齐
        nameLabel.setText(question[0][1]); // 设置初始文本
        nameLabel.setOpaque(false); // 设置标签背景为透明
        nameLabel.setBorder(BorderFactory.createEmptyBorder(2, 65, 20, 10)); // 设置边距
        nameLabel.setFocusable(false); // 禁止获取焦点

        // 初始化对话文本区域
        dialogText = new JTextArea() {
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
        dialogText.setText(question[0][0]); // 设置初始文本
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
}
