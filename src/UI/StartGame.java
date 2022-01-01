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

    // ��ȡ��Գ���
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
        frame.setResizable(false);//ÿ���϶������ػ����Թ̶�

        // ��Ϸ����
        panelSnake = new SnakePanel();
        panelSnake.setBackground(Color.white);
        panelSnake.setBounds(getRelativeWeight(0.01), getRelativeHeight(0.05), getRelativeWeight(0.97), getRelativeHeight(0.75));


        // �Ի���
        panelTalk = new TalkPanel();
        panelTalk.setBackground(Color.white);
        panelTalk.setBounds(getRelativeWeight(0.01), getRelativeHeight(0.83), getRelativeWeight(0.86), getRelativeHeight(0.1));


        //���½��Ǹ�
        panelFace = new FacePanel();
        panelFace.setBackground(new Color(146, 208, 255));
        panelFace.setBounds(getRelativeWeight(0.9), getRelativeHeight(0.81),getRelativeWeight(0.07),getRelativeWeight(0.07));
        JLabel jl = new JLabel();
        jl.setSize(panelFace.getWidth(), panelFace.getHeight());
        ImageIcon image = new ImageIcon("img/sendbutton.png");
        image.setImage(image.getImage().getScaledInstance(jl.getWidth(), jl.getHeight(),Image.SCALE_DEFAULT ));
        jl.setIcon(image);
        panelFace.add(jl);


        // ��������
        Container con = frame.getContentPane();
        con.setBackground(new Color(146, 208, 255));
        con.add(panelSnake);
        con.add(panelTalk);
        con.add(panelFace);


        frame.setSize(DT.frameWeight, DT.frameHeight);//����С
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        new Thread(panelSnake).start();//�����߳�

//        MusicPlayer threadMusic = new MusicPlayer();//��Ч�߳�
//        threadMusic.start();
    }

}
