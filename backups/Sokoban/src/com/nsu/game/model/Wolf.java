package com.nsu.game.model;

import com.nsu.game.util.GameConfig;

import java.awt.*;
import java.lang.module.Configuration;
import java.util.ArrayList;

/**
 * @author Thinkpad
 * 蛇的数据和行为，
 * 信息：蛇的节点集
 * 功能：蛇的移动，转向，吃食物，增长，碰撞检测
 *
 * ======================
 * 狼移动，推箱子（只能推动一个），碰撞检测
 * ======================
 */

public class Wolf {
    private ArrayList<Node> nodes1 = new ArrayList<>();
    //==================================
    private ArrayList<Node> nodes2 = new ArrayList<>();
    //==================================
    private Direction direction = Direction.Right;
    //定义偏移数组，用来取消分支，简化程序结构
    private int[][] offsets;

    public ArrayList<Node> getNodes1() {
        return nodes1;
    }

    public void setNodes1(ArrayList<Node> nodes1) {
        this.nodes1 = nodes1;
    }

    public ArrayList<Node> getNodes2() {
        return nodes2;
    }

    public void setNodes2(ArrayList<Node> nodes2) {
        this.nodes2 = nodes2;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Wolf(){
        defaultInit();
        offsets = new int[][] {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    }

    //蛇的初始化，游戏开始就有条蛇
    private void defaultInit() {
        //==================================
        //nodes1代表蛇，nodes2代表狼
        //==================================

        //创造蛇的节点，并连接成一个节点集
        int r = GameConfig.getRows() / 2 - 1;
        nodes1.add(new Node(r, 0));
        nodes1.add(new Node(r, 1));
        nodes1.add(new Node(r, 2));
//        System.out.println(nodes1.size());
//        System.out.println("nodes1.get(nodes1.size() - 1 - i).getRow() = " + nodes1.get(nodes1.size() - 1).getRow());
//        System.out.println("nodes1.get(nodes1.size() - 1 - i).getCol() = " + nodes1.get(nodes1.size() - 1).getCol());

        //==================================
//===============【这是】
        nodes2.add(new Node(11, 7));
//        System.out.println(nodes2.size());
        //==================================
    }
    //绘制蛇
    public void draw(Graphics g){
//        for (Node n: nodes1 ) {
//            n.draw(g);
//        }

        //画灰太狼
        //==================================
        for (Node n: nodes2 ) {
            n.drawWolf(g);
        }
        //==================================

    }

    //蛇移动

    /**
     * 蛇头（是集合nodes尾部的节点，即索引号大的位置）：与前进方向有关
     * 后面一个节点一次前进为上一个节点
     * (参照的是蛇头的方向，实际上节点的索引号是增加1)
     */
    public void move(){
        //
        //===============？？？？？？？？？？？不太清楚这个for语句的作用
        for (int i = 0; i < nodes1.size() - 1; i++) {
            Node n = nodes1.get(i);
            Node next = nodes1.get(i + 1);//指向下一个节点
            n.setRow(next.getRow());
            n.setCol(next.getCol());
        }
        Node head1 = nodes1.get(nodes1.size() - 1);
        head1.setRow(head1.getRow() + offsets[direction.ordinal()][0]);
        head1.setCol(head1.getCol() + offsets[direction.ordinal()][1]);

        //??????????????????????????
        //==================================
//        for (int i = 0; i < nodes2.size() - 1; i++) {
//            Node n = nodes2.get(i);
//            Node next = nodes2.get(i + 1);//指向下一个节点
//            n.setRow(next.getRow());
//            n.setCol(next.getCol());
//        }
        Node head2 = nodes2.get(nodes2.size() - 1);
        head2.setRow(head2.getRow() + offsets[direction.ordinal()][0]);
        head2.setCol(head2.getCol() + offsets[direction.ordinal()][1]);
        //==================================
    }
    /*
        转向操作
        要求：同直线方向移动，反方向不能操作
     */
    //该逻辑不太清楚
    public void turnTo(Direction dir){
        /*int x = Math.abs(dir.ordinal() - direction.ordinal());
        if(x != 2){
            direction = dir;
        }*/
        direction = dir;
    }

    public boolean checkCollision() {

        boolean flag1 = true;
        boolean flag2 = true;
        //蛇头对象
        Node head = nodes2.get(nodes2.size() - 1);
        //蛇头的下一步的节点坐标
        int r = head.getRow() + offsets[direction.ordinal()][0];
        int c = head.getCol() + offsets[direction.ordinal()][1] ;
//        //测试灰太狼的下一个位置
        System.out.println("灰太狼的位置：");
        System.out.println("r = " + r + ", c = " + c);
//       检测蛇头是否撞上蛇身
        //蛇身坐标，除开蛇头及相邻两个的节点
        for (int i = 0; i < nodes1.size(); i++) {
            //测试大树nodes1的位置？？？？？？
//            System.out.println("nodes1.get(nodes1.size() - 1 - i).getRow() = " + nodes1.get(nodes1.size() - 1 - i).getRow());
//            System.out.println("nodes1.get(nodes1.size() - 1 - i).getCol() = " + nodes1.get(nodes1.size() - 1 - i).getCol());
            if(r == nodes1.get(nodes1.size() - i - 1).getRow() && c == nodes1.get(nodes1.size() - 1 - i).getCol()){
                flag1 = false;
            }
        }
        if(((r >= 0 && r < GameConfig.getRows()) && (c >= 0 && c < GameConfig.getCols()))){
            flag2 = true;
        }


/*
        if(((r >= 0 && r < GameConfig.getRows()) && (c >= 0 && c < GameConfig.getCols()))){
            flag2 = true;
        }
*/
        return flag1 && flag2;
    }

    public void moveBox(Node box) {
        box.setRow(box.getRow() + offsets[direction.ordinal()][0]);
        box.setCol(box.getCol() + offsets[direction.ordinal()][1]);
    }

    public boolean checkBox(Node box) {
        boolean flag;
        Node head = nodes2.get(nodes2.size() - 1);
        System.out.println("======================================================================");
        System.out.println("狼按下方向键之后的位置：");
        System.out.println("--------------- = " + head.getRow());
        System.out.println("--------------- = " + head.getCol());
        System.out.println("箱子按下方向键之后的位置：");
        System.out.println("--------------- = " + box.getRow());
        System.out.println("--------------- = " + box.getCol());
        if((box.getRow() == head.getRow()) && (box.getCol() == head.getCol())){
            flag = true;
        }else {
            flag = false;
        }
        return flag;
    }

    public boolean checkTree(Node tree1, Node tree2, Node tree3) {
        boolean flag = false;
        Node[] nodes = {tree1, tree2, tree3};
        Node head = nodes2.get(nodes2.size() - 1);
        int r = head.getRow() + offsets[direction.ordinal()][0];
        int c = head.getCol() + offsets[direction.ordinal()][1];

        //循环验证tree1, tree2, tree3
        for (int i = 0; i < nodes.length; i++) {
            if((nodes[i].getRow() == r) && (nodes[i].getCol() == c)){
                flag = true;
            }
        }
        //挨个验证tree1, tree2, tree3
//        if((tree1.getCol() == head.getCol()) && (tree1.getRow() == head.getRow())){
//            flag = true;
//        }
        return flag;
    }

    public boolean checkBoundary_wolf() {
        boolean flag = true;

        Node head = nodes2.get(nodes2.size() - 1);
        int r = head.getRow() + offsets[direction.ordinal()][0];
        int c = head.getCol() + offsets[direction.ordinal()][1] ;
        System.out.println("GameConfig.getRows() = " + GameConfig.getRows());
        System.out.println("GameConfig.getCols() =" + GameConfig.getCols());

        if((r >= 0) && (r <= 17) && (c >= 0) && (c <= 28)){
            flag = false;
        }

        return flag;
    }

    public boolean checkBoundary_box(Node box, Node tree1, Node tree2, Node tree3) {
        boolean flag = true;
        Node[] nodes = {tree1, tree2, tree3};
        int r = box.getRow() + offsets[direction.ordinal()][0];
        int c = box.getCol() + offsets[direction.ordinal()][1] ;

        //箱子不能跑出游戏边界
        if((r >= 0) && (r <= 17) && (c >= 0) && (c <= 28)){
            flag = false;
        }
        //箱子不能进入大树中
        for (int i = 0; i < nodes.length; i++) {
            if((nodes[i].getRow() == r) && (nodes[i].getCol() == c)){
                flag = true;
            }
        }

        return flag;
    }

    public boolean checkWolf_box(Node box) {
        boolean flag = false;


        Node head = nodes2.get(nodes2.size() - 1);
        int r = head.getRow() + offsets[direction.ordinal()][0];
        int c = head.getCol() + offsets[direction.ordinal()][1];

        int br0 = box.getRow();
        int bc0 = box.getCol();
        System.out.println("br0 = " + br0);
        System.out.println("bc0 = " + bc0);
        System.out.println("==========================");
        int br = box.getRow() + offsets[direction.ordinal()][0];
        int bc = box.getCol() + offsets[direction.ordinal()][1];
        System.out.println("br = " + br);
        System.out.println("bc = " + bc);
        System.out.println("==========================");
        if((br < 0) || (bc < 0)){
            flag = true;
        }
        /*if((r == br) && (c == bc) && ((br == 0) || (bc == 0))){
            flag = true;
        }*/
        /*if((br < 0) || br > 17 || bc < 0 || bc > 28){
            flag = true;
        }*/

        return flag;
    }

    //检查箱子与狼是否重合，不重合则让狼移动
    public boolean checkWolf_Box(Node box, Node tree1, Node tree2, Node tree3) {
        boolean flag = true;
        boolean flag_tree = false;
        //箱子的下一步
        int brn = box.getRow() + offsets[direction.ordinal()][0];
        int bcn = box.getCol() + offsets[direction.ordinal()][1];
        //狼的下一个位置
        Node wolf = nodes2.get(nodes2.size() - 1);
        int wrn = wolf.getRow() + offsets[direction.ordinal()][0];
        int wcn = wolf.getCol() + offsets[direction.ordinal()][1];

        Node[] nodes = {tree1, tree2, tree3};
        int r = box.getRow() + offsets[direction.ordinal()][0];
        int c = box.getCol() + offsets[direction.ordinal()][1] ;
        for (int i = 0; i < nodes.length; i++) {
            if((nodes[i].getRow() == r) && (nodes[i].getCol() == c)){
                flag_tree = true;
            }
        }


        //盒子的位置与狼的下一步位置重合，并且盒子的下一步位置超出游戏区域，或进入大树区域，则狼与盒子重合
        if(((box.getRow() == wrn) && (box.getCol() == wcn)) &&
                (brn > 17 || brn < 0 || bcn < 0 || bcn > 28 || flag_tree)){
            flag = false;
        }

        return flag;
    }

    /*public boolean checkBox() {
        boolean flag = true;
        Node head = nodes2.get(nodes2.size() - 1);
        //蛇头的下一步的节点坐标
        int r = head.getRow() + offsets[direction.ordinal()][0];
        int c = head.getCol() + offsets[direction.ordinal()][1] + 1;
        //如果圆球的下一个位置是箱子所在位置，且这个箱子四周没有箱子或墙，则：该箱子随着圆球一起向向下一个方向移动
        if(r == 2 && c == 3){
//            nodes1
//            【【【【【【【【【【【【【？？？】】】】】】】】】】】】】】】】】】】】
            flag = true;
        }
        return flag;
    }

    public void moveBox(Node box) {
        if(checkCollision()){
            box.setRow(box.getRow() + offsets[direction.ordinal()][0]);
            box.setCol(box.getCol() + offsets[direction.ordinal()][1]);
        }
    }*/
}
