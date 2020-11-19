package com.nsu.game.model;

import com.nsu.game.util.GameConfig;
import com.nsu.game.util.GameImage;

import java.awt.*;

/**
 * @author Thinkpad
 * 游戏当中最小的组成单元，可以表示食物节点，蛇节点，障碍物
 */

public class Node {
    private int row;
    private int col;
    private NodeType type;


    /*private Direction direction = Direction.Right;
    private int[][] offsets;

    public Node() {
        offsets = new int[][] {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    }*/

    public Node(int r, int c, NodeType t){
        row = r;
        col = c;
        type = t;
    }

    //蛇构造方法：因为社节点的出现频率较高，
    //          所以单独使用一个构造方法构造一个节点
    public Node(int r, int c){
        this(r, c, NodeType.Snake);
    }

    public void draw(Graphics g){
        Image img = GameImage.getBackground();
        g.drawImage(img, 0, 0, GameConfig.getCanvasWidth(), GameConfig.getCanvasHeight(), null);
        int size = GameConfig.getCellSize();
        int x = col * size;
        int y = row * size;
        g.setColor(Color.black);
        g.fillRect(x, y, size, size);
        Image img1 = GameImage.getDestination();
        g.drawImage(img1, x, y, size, size, null);
    }
    //==================================

    public void drawBox(Graphics g){
        int size = GameConfig.getCellSize();
        int x = col * size;
        int y = row * size;
//        g.setColor(Color.white);
//        g.fillRect(x, y, size, size);
        //??????????/不知道type.ordinal()的原理
//        Image img = GameImage.getNode(type.ordinal());
//        g.drawImage(img, x, y, size, size, null);
        Image img = GameImage.getSheep();
        g.drawImage(img, x, y, size, size, null);
    }
    public void drawTree(Graphics g){
        int size = GameConfig.getCellSize();
        int x = col * size;
        int y = row * size;
        //画纯色方块
//        g.setColor(Color.green);
//        g.fillRect(x, y, size, size);
        //使用图片
        Image img = GameImage.getTree();
        g.drawImage(img, x, y, size, size, null);
    }
    public void drawWolf(Graphics g){
        int size = GameConfig.getCellSize();
        int x = col * size;
        int y = row * size;
       /* g.setColor(Color.red);
        g.fillRoundRect(x, y, size, size, 50, 50);*/
        Image img = GameImage.getWolf();
        g.drawImage(img, x, y, size, size, null);
    }
    //==================================



    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public NodeType getType() {
        return type;
    }

    public void setType(NodeType type) {
        this.type = type;
    }

    public boolean checkBox(Node box, Wolf wolf) {
        return wolf.checkBox(box);
    }

    public boolean checkBox_Destination(Node box) {
        boolean flag = false;
        int r = box.getRow();
        int c = box.getCol();

        if(r == 3 && c == 0){
            flag = true;
        }

        return flag;
    }

   /* public void move(Node box) {
        box.setRow(box.getRow() + offsets[direction.ordinal()][0]);
        box.setCol(box.getCol() + offsets[direction.ordinal()][1]);
    }*/
}
