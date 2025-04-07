package com.TJ.ui.ending;
import com.TJ.ui.Tool;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class FinalPanel extends JPanel {
    private JLabel backgroundLabel; // 用于显示背景图的 JLabel
    private Clip backgroundMusic; // 背景音乐
    private  final String[] scenes={
            "Resource/jiewei/0.png",
            "Resource/jiewei/1.png",
            "Resource/jiewei/2.png",
            "Resource/jiewei/3.png",
            "Resource/jiewei/4.png",
            "Resource/jiewei/5.png",
            "Resource/jiewei/6.png",
            "Resource/jiewei/7.png",
            "Resource/jiewei/8.png",
    };

    private BufferedImage[] images;
    private int currentImageIndex = 0;
    private float alpha = 0.0f;
    private Timer animationTimer;
    private long lastSwitchTime;
    private static final int TOTAL_DURATION = 88000; // 88秒
    private static final int IMAGE_COUNT = 8; // 实际使用的图片数量
    private static final int FADE_DURATION = 1500; // 每张图片淡入淡出总时间2秒
    private JButton exitButton;//退出按钮

    FinalPanel(){
        setLayout(null);
        setSize(1600, 900);

        initExitButton();
        initImages();
        initMusic();
        startAnimation();
        this.setVisible(true);
    }

    private void initExitButton() {
        exitButton =createButton("Resource/0zhujiemian/exitbutton.png",400,595,820,191);
        exitButton.setVisible(false);
        exitButton.addActionListener(e -> {
            EndingApplication.Close();
        });
        this.add(exitButton);
    }
    private  JButton createButton(String filename,int x,int y, int width,int height) {
        // 创建按钮并设置缩放后的图片
        JButton button = new JButton(Tool.createImageIcon(filename,width,height));
        button.setBounds(x, y, width, height);
        button.setBorderPainted(false); // 移除按钮边框
        button.setContentAreaFilled(false); // 设置按钮背景透明
        button.setFocusPainted(false);
        return button;
    }

    private void initImages() {
        images = new BufferedImage[scenes.length];
        for (int i = 0; i < scenes.length; i++) {
            images[i] = Tool.createBufferedImage(Tool.createImageIcon(scenes[i], 1600, 900));
        }

        backgroundLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();

                // 设置透明度
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

                // 绘制当前图片(保持宽高比)
                if (currentImageIndex < images.length && images[currentImageIndex] != null) {
                    Image img = images[currentImageIndex];
                    Dimension imgSize = new Dimension(img.getWidth(null), img.getHeight(null));
                    Dimension boundary = getSize();
                    Rectangle destRect = getScaledDimension(imgSize, boundary);
                    g2d.drawImage(img, destRect.x, destRect.y, destRect.width, destRect.height, null);
                }

                g2d.dispose();
            }
        };
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        this.add(backgroundLabel);
    }

    private void startAnimation() {
        int imageDuration = TOTAL_DURATION / IMAGE_COUNT; // 每张图片总显示时间
        final int[] playCount = {0}; // 计数器，跟踪已播放的图片数量
        animationTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long currentTime = System.currentTimeMillis();
                long elapsed = currentTime - lastSwitchTime;
                if (elapsed >= imageDuration) {
                    playCount[0]++;
                    // 检查是否播放完所有图片
                    if (playCount[0] >= IMAGE_COUNT-1) {
                        // 停留在最后一张图片
                        animationTimer.stop(); // 停止动画
                        backgroundLabel.setIcon(Tool.createImageIcon(scenes[7], 1600, 900));
                        exitButton.setVisible(true);
                        return;
                    } else {
                        // 继续播放下一张
                        currentImageIndex = (currentImageIndex + 1) % IMAGE_COUNT;
                        lastSwitchTime = currentTime;
                        alpha = 0.0f;
                    }
                } else if (elapsed < FADE_DURATION / 2) {
                    // 淡入阶段
                    alpha = (float) (elapsed / (FADE_DURATION / 2.0));
                } else if (elapsed > imageDuration - FADE_DURATION / 2) {
                    // 淡出阶段
                    alpha = (float) ((imageDuration - elapsed) / (FADE_DURATION / 2.0));
                } else {
                    // 完全显示阶段
                    alpha = 1.0f;
                }

                backgroundLabel.repaint();
            }
        });

        lastSwitchTime = System.currentTimeMillis();
        animationTimer.start();
    }

    private Rectangle getScaledDimension(Dimension imgSize, Dimension boundary) {
        double widthRatio = boundary.getWidth() / imgSize.getWidth();
        double heightRatio = boundary.getHeight() / imgSize.getHeight();
        double ratio = Math.min(widthRatio, heightRatio);

        int newWidth = (int) (imgSize.width * ratio);
        int newHeight = (int) (imgSize.height * ratio);
        int x = (boundary.width - newWidth) / 2;
        int y = (boundary.height - newHeight) / 2;

        return new Rectangle(x, y, newWidth, newHeight);
    }


    private void initMusic() {
        backgroundMusic=Tool.playBackgroundMusic("Resource/jiewei/Sunflower Feelings.wav");
        backgroundMusic.loop(0); // 参数0表示只播放一次，不循环
    }

}
