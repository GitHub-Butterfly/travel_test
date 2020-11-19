package com.nsu.game.util;

import javax.swing.*;
import java.awt.*;

/**
 * @author Thinkpad
 * 集中管理游戏中所有图片，对外提供get访问器
 */

public class GameImage {
    //节点类型图片数组，蛇，食物，障碍物
    private static Image[] nodes;
    private static Image wolf;
    private static Image tree;
    private static Image box;
    private static Image background;
    private static Image sheep;
    private static Image destination;

    static{
        nodes = new Image[4];
//        nodes[0] = new ImageIcon("imgs/snake.png").getImage();
        nodes[1] = new ImageIcon("D:\\intelliJ IDEA\\Java\\test1\\src\\imgs\\food.png").getImage();
        wolf = new ImageIcon("D:\\intelliJ IDEA\\Java\\test1\\src\\imgs\\wolf-zb.png").getImage();
        tree = new ImageIcon("D:\\intelliJ IDEA\\Java\\test1\\src\\imgs\\tree.png").getImage();
        box = new ImageIcon("D:\\intelliJ IDEA\\Java\\test1\\src\\imgs\\box.png").getImage();
        background = new ImageIcon("D:\\intelliJ IDEA\\Java\\test1\\src\\imgs\\q1.png").getImage();
        sheep = new ImageIcon("D:\\intelliJ IDEA\\Java\\test1\\src\\imgs\\sheep-no.png").getImage();
        destination = new ImageIcon("D:\\intelliJ IDEA\\Java\\test1\\src\\imgs\\target.png").getImage();

        /**
         * 问题：有些图片不能导入，还有imgs的那个文件夹下的snake，start，numbers，food四张图片能用绘图进行显示
         */
//        nodes[2] = new ImageIcon("imgs/food.png").getImage();//“树”——食物节点
//        nodes[3] = new ImageIcon("imgs/snake.png").getImage();//“狼”——蛇节点
    }

    //获取对应索引号的节点类型的图片
    public static Image getNode(int index){
        return nodes[index];
    }
    public static Image getWolf(){
        return wolf;
    }

    public static Image getTree() {
        return tree;
    }

    public static Image getBox() {
        return box;
    }
    public static Image getBackground() {
        return background;
    }
    public static Image getSheep() {
        return sheep;
    }
    public static Image getDestination() {
        return destination;
    }


}
