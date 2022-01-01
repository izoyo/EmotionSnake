package Mode;

import UI.SnakePanel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Snake {
    /**
     * 计数器防死循环的
     */
    public int c = 0;
    /**
     * 蛇身大小
     */
    public final static int size = 50;
    /**
     * 地图大小  一定要是蛇身大小的倍数
     */
    public final static int map_sizeX = 1250;
    public final static int map_sizeY = 500;
    /**
     * 蛇头
     */
    private Node first;
    /**
     * 蛇的尾巴，实际是尾巴走的上一个节点
     */
    private Node tail;
    /**
     * 蛇尾
     */
    private Node last;
    /**
     * 蛇的数据结构
     */
    private ArrayList<Node> s = new ArrayList<Node>();
    /**
     * 地图上已有蛇的节点,蛇的String存储
     */
    private HashSet<String> map = new HashSet<String>();

    /**
     * 方向
     */
    private int dir;// 8 6 2 4  上 右 下 左
    /**
     * 食物
     */
    private Node food;

    private boolean isTrueSnake = false;

    private SnakePanel snakePanel;

    public Snake(SnakePanel sp) {
        isTrueSnake = true;
        snakePanel = sp;
    }

    public Snake(Node first, Node last, Node food, Node tail) {
        this.first = first;
        this.last = last;
        this.food = food;
        this.tail = tail;
    }

    public void printS(String p) {
        System.out.print(p + "：   ");
        for (int i = 0; i < s.size(); i++) {
            System.out.print(s.get(i).getFace() + " - ");
        }
        System.out.println();
    }

    /**
     * 把n添加到s中
     *
     * @param n
     */
    private void add_Node(Node n) {

        //如果添加的节点不是食物，去掉尾巴
        if (!n.toString().equals(food.toString())) {
            s.add(0, n);

            for (int i = 0; i < s.size(); i++) {
                if (i > 0) {
                    s.get(i - 1).setFace(s.get(i).getFace());
                }
            }

            if (s.get(0).getFace() == Face.angry) {
                s.get(0).setFace(Face.normal);
                DT.speed = DT.rate;
            }

            tail = s.get(s.size() - 1);//记录尾巴
            s.remove(s.size() - 1);
            last = s.get(s.size() - 1);

        } else {//如果是,随机食物，
            //吃到了
            s.add(0, food);
            if (isTrueSnake) {
                System.out.println("\neat food:" + s.get(0).getFace());
//                printS("eat  ");

                DT.musicFace = food.getFace();

                switch (food.getFace()) {

                    case sad:
                        DT.speed = DT.rate * 2;
                        s.get(1).setFace(Face.sad);
                        break;
                    case angry:
                        DT.speed = DT.rate * 5;

                        for (int i = 1; i <= 3 && s.size() > i; i++) {
                            s.get(i).setFace(Face.angry);
                        }

                        //立即重画
//                        snakePanel.repaint();

                        snakePanel.paintSnake(snakePanel.getGraphics(), this);

                        try {
                            Thread.sleep(2000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        for (int i = s.size() - 1; i > 3; i--) {
                            s.get(i).setX(s.get(i - 3).getX());
                            s.get(i).setY(s.get(i - 3).getY());
                        }

                        Iterator<Node> itr = s.iterator();
                        for (int i = 0; i <= 3 && itr.hasNext(); i++) {
                            Node next = itr.next();
                            if (i > 0) {
                                System.out.println("del:" + next.getFace());
                                itr.remove();
                            }
                        }

                        break;
                    case happy:
                        DT.speed = (int) (DT.rate * 0.5);
                        for (int i = 1; i <= 2 && s.size() > i; i++) {
                            s.get(i).setFace(Face.happy);
                        }
                        break;
                    case normal:
                        DT.speed = DT.rate;
                        break;
                }
//                printS("now   ");
            }
            food = RandomFood();

        }

        first = s.get(0);
    }

    /**
     * 移动
     */
    public void move() {
        if (dir == 8) {
            Node n = new Node(first.getX(), first.getY() - Snake.size);
            add_Node(n);
        }
        if (dir == 6) {
            Node n = new Node(first.getX() + Snake.size, first.getY());
            add_Node(n);
        }
        if (dir == 2) {
            Node n = new Node(first.getX(), first.getY() + Snake.size);
            add_Node(n);
        }
        if (dir == 4) {
            Node n = new Node(first.getX() - Snake.size, first.getY());
            add_Node(n);
        }

        updataMap(s);
    }

    /**
     * 可以设置方向的move
     *
     * @param dir
     */

    public void move(int dir) {
        this.dir = dir;
        move();
    }

    /**
     * 判断dir方向能不能走
     *
     * @param dir
     * @return
     */
    public boolean canMove(int dir) {
        if (dir == 8) {
            int X = first.getX();
            int Y = first.getY() - Snake.size;
            if (Y < Snake.size || map.contains(X + "-" + Y)) {
                return false;
            } else return true;
        }
        if (dir == 6) {
            int X = first.getX() + Snake.size;
            int Y = first.getY();
            if (X > Snake.map_sizeX || map.contains(X + "-" + Y)) {
                return false;
            } else return true;
        }
        if (dir == 2) {
            int X = first.getX();
            int Y = first.getY() + Snake.size;
            if (Y > Snake.map_sizeX || map.contains(X + "-" + Y)) {
                return false;
            } else return true;
        }
        if (dir == 4) {
            int X = first.getX() - Snake.size;
            int Y = first.getY();
            if (X < Snake.size || map.contains(X + "-" + Y)) {
                return false;
            } else return true;
        }
        return false;
    }

    /**
     * String转Node
     *
     * @param s
     * @return
     */
    public Node StringToNode(String s) {
        String[] str = s.split("-");
        int x = Integer.parseInt(str[0]);
        int y = Integer.parseInt(str[1]);
        return new Node(x, y);
    }
    /**
     * Node转String,突然想起可以直接写个toString。。
     * @param n
     * @return
     */
//	public String NodeToString(Node n){
//		return n.getX()+"-"+n.getY();
//	}

    /**
     * 更新地图上访问过的位置
     *
     * @param s
     */
    public void updataMap(ArrayList<Node> s) {
        map.clear();//先移除旧的站位点
        for (Node n : s) {
            map.add(n.toString());
        }

    }

    /**
     * 食物的随机出现
     */
    public Node RandomFood() {
        c = 0;
        while (true) {
            int x = 0, y;
            x = Snake.size * (int) (Math.random() * Snake.map_sizeX / Snake.size) + Snake.size;
            y = Snake.size * (int) (Math.random() * Snake.map_sizeY / Snake.size) + Snake.size;

//            Face rf = Face.angry;
            Face rf = Face.randomFace();
            Node n = new Node(x, y, rf);
            //食物不能出现在蛇身体,s.contains居然检查不到Node,
            //突然想到是不是Node类没重写equals方法。。
            if (!s.contains(n)) {
//                System.out.println("\nrandom face:" + rf);
                return n;
            }
        }
    }


    /**
     * @return 蛇长
     */
    public int getLen() {
        return s.size();
    }

    /**
     * @return 尾巴lsat的后一个节点
     */
    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    public HashSet<String> getMap() {
        return map;
    }

    public Node getFirst() {
        return first;
    }

    public Node getLast() {
        return last;
    }

    public ArrayList<Node> getS() {
        return s;
    }

    public void setFirst(Node first) {
        this.first = first;
    }

    public void setLast(Node last) {
        this.last = last;
    }

    public void setS(ArrayList<Node> s) {
        this.s = s;
    }

    public void setMap(HashSet<String> map) {
        this.map = map;
    }

    public void setFood(Node food) {
        this.food = food;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public Node getFood() {
        return food;
    }
}
