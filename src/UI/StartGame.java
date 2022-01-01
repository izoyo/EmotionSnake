package UI;

import AI.*;
import Mode.*;
import java.awt.*;
import javax.swing.*;



public class StartGame {

    private JFrame frame;
    public TalkPanel panelTalk;
    public SnakePanel panelSnake;
    public FacePanel panelFace;

    // 获取相对长度
    public static int getRelativeWeight(double p) {

        return (int) (p * DT.frameWeight);
    }

    public static int getRelativeHeight(double p) {
        return (int) (p * DT.frameHeight);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StartGame window = new StartGame();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public StartGame() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("\u8D2A\u5403\u86C7AI");
        frame.setLayout(null);
        frame.setResizable(false);//每次拖动都会重绘所以固定

        // 游戏窗口
        panelSnake = new SnakePanel();
        panelSnake.setBackground(Color.white);
        panelSnake.setBounds(getRelativeWeight(0.01), getRelativeHeight(0.05), getRelativeWeight(0.97), getRelativeHeight(0.75));


        // 对话框
        panelTalk = new TalkPanel();
        panelTalk.setBackground(Color.white);
        panelTalk.setBounds(getRelativeWeight(0.01), getRelativeHeight(0.83), getRelativeWeight(0.86), getRelativeHeight(0.1));


        //右下角那个
        panelFace = new FacePanel();
        panelFace.setBackground(new Color(146, 208, 255));
        panelFace.setBounds(getRelativeWeight(0.9), getRelativeHeight(0.81),getRelativeWeight(0.07),getRelativeWeight(0.07));
        JLabel jl = new JLabel();
        jl.setSize(panelFace.getWidth(), panelFace.getHeight());
        ImageIcon image = new ImageIcon("img/sendbutton.png");
        image.setImage(image.getImage().getScaledInstance(jl.getWidth(), jl.getHeight(),Image.SCALE_DEFAULT ));
        jl.setIcon(image);
        panelFace.add(jl);


        // 程序容器
        Container con = frame.getContentPane();
        con.setBackground(new Color(146, 208, 255));
        con.add(panelSnake);
        con.add(panelTalk);
        con.add(panelFace);


        frame.setSize(DT.frameWeight, DT.frameHeight);//外框大小
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        new Thread(panelSnake).start();//启动线程

//        MusicPlayer threadMusic = new MusicPlayer();//音效线程
//        threadMusic.start();
    }

}
