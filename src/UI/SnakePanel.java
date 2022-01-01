package UI;

import java.awt.*;

import AI.*;
import Mode.*;


/**
 * ʳ�ﲻӦ�ó�����ֻ��һ�������
 */

public class SnakePanel extends PartPanel implements Runnable {

    Snake snake;
    SnakeAi ai;

    Image img_none,img_sad,img_angry,img_happy,img_normal;

    /**
     * Create the panel.
     */
    public SnakePanel() {
        snake = new Snake(this);
        Node n = new Node(Snake.size, Snake.size, Face.randomFace());//�ߵĳ�ʼλ��
        snake.getS().add(n);
        snake.setFirst(n);
        snake.setLast(n);
        snake.setTail(new Node(0, Snake.size));//last�ĺ�һ���ڵ�
        snake.setFood(new Node(8 * Snake.size, 8 * Snake.size, Face.randomFace()));//ʳ���ʼλ��
        ai = new SnakeAi();

        //����ͼƬ
        Toolkit toolkit;
        toolkit = Toolkit.getDefaultToolkit();

        img_none = toolkit.getImage("img/none.png");
        img_sad = toolkit.getImage("img/sad.png");
        img_angry = toolkit.getImage("img/angry.png");
        img_happy = toolkit.getImage("img/happy.png");
        img_normal = toolkit.getImage("img/normal.png");

    }

    @Override
    public void paint(Graphics g) {
        // TODO Auto-generated method stub
        super.paint(g);
//        g.setColor(Color.orange);
//        g.drawRect(Snake.size, Snake.size, Snake.map_sizeX, Snake.map_sizeY);//��ͼ��Χ

        paintSnake(g, snake);

        paintFood(g, snake.getFood());


        int dir = ai.play2(snake, snake.getFood());//ѡ����ԣ�play ,play1,play2

        if (dir == -1) {
//			System.out.println("GG");
        } else {
            snake.move(dir);
        }
    }

    /**
     * ����
     *
     * @param g
     * @param snake
     */
    public void paintSnake(Graphics g, Snake snake) {
        for (Node n : snake.getS()) {
            if (n.toString().equals(snake.getFirst().toString())) {
                //��ͷ
                g.setColor(new Color(146, 208, 255));
                g.fillOval( n.getX()-2,  n.getY()-2, Snake.size+4, Snake.size+4);
            }
            if (n.toString().equals(snake.getLast().toString()) && !snake.getFirst().toString().equals(snake.getLast().toString())) {
                //��β
                g.setColor(new Color(146, 208, 255));
                g.fillOval( n.getX()-2,  n.getY()-2, Snake.size+4, Snake.size+4);
            }

            g.drawImage(faceToEntity(n.getFace()), n.getX(), n.getY(), Snake.size, Snake.size, this);
//            g.drawString(n.getFace().toString(),n.getX(), n.getY());
//            g.fillRect(n.getX(), n.getY(), Snake.size, Snake.size);
//            g.setColor(n.faceToEntity());//����
        }
    }

    /**
     * ��ʳ��
     *
     * @param g
     * @param food
     */
    public void paintFood(Graphics g, Node food) {
//        g.setColor(food.faceToEntity());
//        g.fillOval(food.getX(), food.getY(), snake.size, snake.size);

        g.drawImage(faceToEntity(food.getFace()), food.getX(), food.getY(), Snake.size, Snake.size, this);
//        g.drawString(food.getFace().toString(),food.getX(), food.getY());
    }

    @Override
    public void run() {
        while (true) {
            // TODO Auto-generated method stub
            try {
                Thread.sleep(DT.speed);//�ӳ��ٶ�
                this.repaint();
//                System.out.println(snake.getFood().getFace());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Image faceToEntity(Face face) {

        Image myImage = img_none;
        switch (face) {
            case sad:
                myImage = img_sad;
                break;
            case angry:
                myImage = img_angry;
                break;
            case happy:
                myImage = img_happy;
                break;
            case normal:
                myImage = img_normal;
                break;
        }
        return myImage;
    }
}
