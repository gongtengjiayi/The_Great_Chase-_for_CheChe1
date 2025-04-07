package com.TJ.ui;


import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public final class Tool {
    public final static int Prologue=0;
    public final static int GoodEnd=1;
    public final static int NormalEnd=2;
    public final static int BadEnd=3;
    public final static int GoodReaction=4;
    public final static int NormalReaction=5;
    public final static int BadReaction=6;
    private Tool() {}

    public static ImageIcon createImageIcon(String path,int width,int height){
        ImageIcon originalIcon = new ImageIcon(path);
        Image originalImage = originalIcon.getImage();
        BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = scaledImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(originalImage, 0, 0, width, height, null);
        g2d.dispose();
        return new ImageIcon(scaledImage);
    }

    public static BufferedImage createBufferedImage(ImageIcon icon){
        if (icon == null) {
            return null;
        }

        BufferedImage bufferedImage = new BufferedImage(
                icon.getIconWidth(),
                icon.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB
        );

        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(
                icon.getImage(),
                0, 0,
                icon.getIconWidth(),
                icon.getIconHeight(),
                null
        );
        g2d.dispose();

        return bufferedImage;
    }

    public static JButton creatNextButton(){
        JButton nextButton = new JButton("==>");
        nextButton.setBounds(1450, 543, 150, 50); // 设置按钮位置和大小
        nextButton.setFont(new Font("宋体", Font.BOLD, 18));
        return nextButton;
    }

    public static Clip playBackgroundMusic(String musicPath) {
        try {
            // 加载音频文件
            File musicFile = new File(musicPath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicFile);

            // 创建 Clip 对象
            Clip backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioInputStream);

            // 设置循环播放
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);

            // 开始播放
            backgroundMusic.start();
            return backgroundMusic;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            System.out.println("无法打开音频！");
            return null;
        }
    }

    public static void playClickSound(String fileName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new File(fileName));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JPanel createDialogPanel (JLabel nameLabel,JTextArea dialogText){
        JPanel dialogPanel = new JPanel();
        dialogPanel.setBounds(0, 520, 1590, 340); // 设置位置和大小
        dialogPanel.setLayout(new BorderLayout());
        dialogPanel.setOpaque(false);

        dialogPanel.add(nameLabel, BorderLayout.NORTH); // 人物名称放在顶部
        dialogPanel.add(dialogText, BorderLayout.CENTER); // 文本区域放在中心
        return dialogPanel;
    }

    public static JLabel createNameLabel(){
        JLabel nameLabel=new JLabel();
        nameLabel.setForeground(Color.black); // 设置文本颜色
        nameLabel.setFont(new Font("微软雅黑", Font.BOLD, 48)); // 设置字体
        nameLabel.setHorizontalAlignment(SwingConstants.LEFT); // 左对齐
        nameLabel.setOpaque(false); // 设置标签背景为透明
        nameLabel.setBorder(BorderFactory.createEmptyBorder(2, 65, 20, 10)); // 设置边距
        nameLabel.setFocusable(false); // 禁止获取焦点
        return nameLabel;
    }

    public static JTextArea createDialogText(String text){
        JTextArea dialogText = new JTextArea(){
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
        dialogText.setText(text); // 设置初始文本
        dialogText.setForeground(Color.black);
        dialogText.setBackground(new Color(0, 0, 0, 0)); // 设置文本区域背景为透明
        dialogText.setBorder(BorderFactory.createEmptyBorder(5, 50, 0, 0));
        dialogText.setEditable(false);
        dialogText.setLineWrap(true);
        dialogText.setWrapStyleWord(true);
        dialogText.setFont(new Font("微软雅黑", Font.PLAIN, 38));
        return dialogText;
    }

    public static String[][] getScenes(int x){
        switch (x){
            case Prologue:{
                return new String[][]{
                        {"Resource/Ima/Dialog/other/Prologue0.png", "这一天终于还是到来了", "我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/other/Prologue0.png", "我独自站在林之心外面，等待着那个时刻的到来", "我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/other/Prologue1.png", "就在昨天，我终于下定了决心，在手机里发出了那句话：", "我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/other/Prologue1.png", "“明天，能在林之心见一面吗？我有些话想要跟你说。”", "我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/other/Prologue1.png", "“可以呀。”", "车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/other/Prologue0.png", "………………………………", "我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/other/Prologue0.png", "远方，一个熟悉的身影随着我心跳的加速而逐渐靠近。", "我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/happy.png", "“哈喽！”", "车车","Resource/audio/Prologue/0.wav"},//0
                        {"Resource/Ima/Dialog/happy.png", "当我缓过神来时，她已经走到我的面前了", "我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png", "“所以说…………你要跟我说些什么呢？”", "车车","Resource/audio/Prologue/1.wav"},//1
                        {"Resource/Ima/Dialog/normal.png", "我咽下口水，握紧拳头，今天，一定要，向我面前这个女生（外号车车），表白！", "我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png", "“我想说：我…………我…………”", "我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png", "“我喜欢你！”", "我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png", "…………………", "车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png", "………………………………", "车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/shock.png", "“什……什么！？”", "车车","Resource/audio/Prologue/2.wav"},//2
                        {"Resource/Ima/Dialog/shock.png", "“你、你不是认真的吧？”", "车车","Resource/audio/Prologue/3.wav"},//3
                        {"Resource/Ima/Dialog/shock.png", "我顶着停不下来的心跳，轻轻地点了点头", "我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png", "………………………………", "车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png", "“那好吧，如果你能正确回答下面这十个问题，我就答应做你的女朋友”", "车车","Resource/audio/Prologue/4.wav"},//4
                        {"Resource/Ima/Dialog/normal.png", "这是什么鬼？怎么还会有十个问题？", "我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png", "“怎么了，有什么问题吗？”", "车车","Resource/audio/Prologue/5.wav"},//5
                        {"Resource/Ima/Dialog/normal.png", "“没有没有，我愿意接受这样的考验！”", "我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png", "“好，那我们开始吧！”", "车车","Resource/audio/Prologue/6.wav"}//6
                };
            }
            case GoodEnd:{
                return new String[][]{
                        {"Resource/Ima/Dialog/shy2.png","我………………","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/shy2.png","………………我…………","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/shy2.png","（车车好像在犹豫些什么）","我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/shy2.png","（不行！我必须要主动出击！）","我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/shy2.png","你掏了掏口袋，果然，你准备的东西终于要派上用场了。","旁白","Resource/default.wav"},
                        {"Resource/Ima/Dialog/other/ending0.png","你直接从口袋里拿出了并西西9.9买的一个项链。","旁白","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","二话不说把它戴在了车车的脖子上。","旁白","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","！！！","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","……………………","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","你看着车车笑得越来越开心。","旁白","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","“我同意啦！”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","（好耶！）","我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","“现在天气正好！我们顺便去阳光长跑吧！”","我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","“阳光长跑，作为第一次约会可能不是最好的地点，不过…………”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","“我们走吧！”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","车车突然牵起你的手，向着体育场将你拉去，你也顺着这份温柔的力量前进。","旁白","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","“该死。”","？？？","Resource/default.wav"},
                        {"Resource/Ima/Dialog/other/suspense2.png","“！？”","我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/other/suspense2.png","你回过头顺着声音看去，却什么都没有看到。","旁白","Resource/default.wav"},
                        {"Resource/Ima/Dialog/other/suspense2.png","“怎么了？”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","“刚才好像听见后面有什么动静…………”","我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","“没事，走吧！”","我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","就这样，我终于跟车车在了一起，","我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","过上了幸福快乐的生活…………………………","我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","表白车车大作战，成功！","旁白","Resource/default.wav"},

                };
            }
            case NormalEnd:{
                return new String[][]{
                        {"Resource/Ima/Dialog/normal.png","……………………","我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","……………………","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","“所以呢？”","我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","“所以什么？”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","“我不是回答完了10个问题吗？”","我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/awkward.png","“你都答对了吗？”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/awkward.png","“额…………没有”","我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","“那不就是了吗？”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","……………………","我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","（不行啊，我不能就这样结束啊。）","我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","你紧张得掏了掏口袋，突然摸到了一个东西。","旁白","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","（有了！有了这个东西，或许………………）","我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/other/ending0.png","“你从口袋里拿出了你在并西西9.9买的一个项链，并展现给了车车看。”","旁白","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","……………………","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/awkward.png","“这是什么？”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/awkward.png","“我、我给你买的项链，好看吗？”","我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/happy.png","“哈哈哈，你真把现实当游戏了吗，以为给别人送东西就能加好感，这项链你就先留着吧。”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/happy.png","“呜呜呜…………”","我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/happy.png","“好啦，我虽然没有同意作你的女朋友，但是我也没有拒绝呀。”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/happy.png","“什么？”","我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","“我的意思是，从今天开始，我们可以多认识一些先呐，毕竟是你突然找我表白，我们还没有更多的了解对方呢。”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","“说的也是这么回事。”","我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","“首先，就让我看看你的耐力如何先吧。走！去阳光长跑吧！”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","“好！”","我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","车车转过身去，你也跟着她的背影踏出步伐","旁白","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","切！","？？？","Resource/default.wav"},
                        {"Resource/Ima/Dialog/other/suspense2.png","！？","我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/other/suspense2.png","你回过头顺着声音看去，却什么都没有看到。","旁白","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","“喂！你还去不去了？”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","“来了，来了！”","我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","你不管刚才仿佛幻觉般的声音，跟着车车一起走向了田径场………………","旁白","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","虽然你现在没有拿下车车，但或许未来你有可能再次向车车表白，结果或许就与现在不同了………………","旁白","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","表白车车大作战成功？","旁白","Resource/default.wav"},
                };
            }
            case BadEnd:{
                return new String[][]{
                        {"Resource/Ima/Dialog/angry.png","……………………","我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/angry.png","（该死，车车看起来非常的生气，看来我回答错的太多了。）","我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/angry.png","（我必须要做点什么，不然就寄了！！）","我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/angry.png","你把手伸进口袋，果然找到了它。","旁白","Resource/default.wav"},
                        {"Resource/Ima/Dialog/angry.png","（对了！我还有它，有了它的话，指不定一切都可以挽回。）","我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/other/ending0.png","就这样，你突然拿出一个你在并西西9.9买的一条项链，展示给了车车。","旁白","Resource/default.wav"},
                        {"Resource/Ima/Dialog/angry.png","啪！","旁白","Resource/default.wav"},
                        {"Resource/Ima/Dialog/angry.png","你的廉价项链被车车路边一条一样打飞出去，最后狠狠地落在了地上……","旁白","Resource/default.wav"},
                        {"Resource/Ima/Dialog/angry.png","“你都惹我这么生气了！你觉得一条项链会有用吗！？”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/angry.png","“可是，我…………我…………”","我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/angry.png","“够了！我们直接是不会有任何结果的！”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/angry.png","“不要啊！我是真的喜欢你的！”","我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/angry.png","“哼！”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/angry.png","就这样，车车拒绝了你的表白，迅速地离开了，只剩下呆在原地的你…………","旁白","Resource/default.wav"},
                        {"Resource/Ima/Dialog/angry.png","“如我所愿……”","？？？","Resource/default.wav"},
                        {"Resource/Ima/Dialog/other/suspense2.png","！？","我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/other/suspense2.png","你回过头顺着声音看去，却什么都没有看到。","旁白","Resource/default.wav"},
                        {"Resource/Ima/Dialog/angry.png","你伤心欲绝，也没有心情再管太多，直愣愣地向宿舍走去…………","旁白","Resource/default.wav"},
                        {"Resource/Ima/Dialog/angry.png","“呜呜呜…………”","我","Resource/default.wav"},
                        {"Resource/Ima/Dialog/angry.png","表白车车大作战失败…………………………","旁白","Resource/default.wav"},
                        {"Resource/Ima/Dialog/angry.png","在床上偷偷抹眼泪的你，心里不自觉地想着，如果成功了，那将是多好的一件事情……","旁白","Resource/default.wav"},
                };
            }
            default:return null;
        }
    }

    public static String[][] getReaction(int x) {
        switch(x) {
            case GoodReaction:{
                return new String[][]{
                        {"Resource/Ima/Dialog/happy.png","“可以嘛，这都口算得出来。”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/happy.png","“哎呦，不错喔”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/happy.png","“那以后我们出国旅行应该没问题了！”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/happy.png","“哇，你真是太聪明了！”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/happy.png","“可以，数据结构学的很稳固。”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/happy.png","“别急，等下几个问题你就懂了。”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/shy1.png","“这就是占有欲吗………………”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/shy1.png","“有你真好…………”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/shy1.png","“哇！”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/shy2.png","“够了，别说了…………”","车车","Resource/default.wav"}
                };
            }
            case NormalReaction:{
                return new String[][]{
                        {"Resource/Ima/Dialog/normal.png","“答错了……这不是高数学过吗？”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/awkward.png","“这也不会嘛…………”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","“NONONO,you are wrong!”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/awkward.png","“…………………………（车车没反应，是不是我算错了？）”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","“这么简单都不记得吗？”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","“有情商的第一标准——谦虚。”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","“说的也有道理。”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","“只是为了志愿时长吗…………”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/normal.png","“也算你孝顺了…………”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/awkward.png","“你这是什么回答…………”","车车","Resource/default.wav"}
                };
            }
            case BadReaction:{
                return new String[][]{
                        {"Resource/Ima/Dialog/angry.png","“就是要用高数题考你的智商啊！”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/angry.png","“你连概率都不会算，怎么算得我们的未来！”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/angry.png","“你怎么能这么说！！！”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/angry.png","“计算机组成原理的知识你都不懂吗？”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/angry.png","“哼！”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/angry.png","“你！！！”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/angry.png","“你一点都不在乎我吗？那我当别人女朋友算了！”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/angry.png","“想让你陪陪我有必要这么凶吗？”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/angry.png","“你是龟男吗？这么蠕。”","车车","Resource/default.wav"},
                        {"Resource/Ima/Dialog/angry.png","“事到如今了还想着逃避吗？”","车车","Resource/default.wav"}
                };
            }
            default:return null;
        }
    }
}
