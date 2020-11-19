package com.nsu.game.model;

import com.nsu.game.control.GameController;
import com.nsu.game.util.GameConfig;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameServer {
    private GameController game;
    //蛇对象
    private Wolf wolf;
    //食物对象
    private Node food1;
    public GameServer(GameController game){
        this.game = game;
        //蛇的实例化
        wolf = new Wolf();
    }
//=================【自行添加的第二个食物节点对象】================
    private Node box;
    //==================================
    private Node tree1;
    private Node tree2;
    private Node tree3;
    private Node destination;
    //==================================
    public void start(){
        //随机位置生成食物
        createFood();
    }

    //制作食物
    private void createFood() {
        Random ran = new Random();
        while(true){
            int r1 = ran.nextInt(GameConfig.getRows());
            int c1 = ran.nextInt(GameConfig.getCols());
            Node n1 = new Node(r1, c1, NodeType.Food);

//=================【自行添加的第二个食物节点坐标】================
            /*int r2 = ran.nextInt(GameConfig.getRows());
            int c2 = ran.nextInt(GameConfig.getCols());
            Node n2 = new Node(r2, c2, NodeType.Food);*/
            Node n2 = new Node(12, 2, NodeType.Food);


            //判断食物和蛇有没有重叠
            if(true){
                food1 = n1;
                box = n2;

                break;
            }
        }
        //==================================
        tree1 = new Node(11, 4, NodeType.Tree);
        tree2 = new Node(11, 5, NodeType.Tree);
        tree3 = new Node(11, 6, NodeType.Tree);
        destination = new Node(11, 0, NodeType.Tree);//目的地
        //==================================
    }

    //游戏绘制————绘制食物和蛇
//=================【自行绘制的第二个食物】================
    public void draw(Graphics g){
        destination.draw(g);//花画目的地
        //绘制蛇
        wolf.draw(g);
        //绘制食物
//        food1.draw(g);
        box.drawBox(g);
        //==================================
        //绘制树
        tree1.drawTree(g);
        tree2.drawTree(g);
        tree3.drawTree(g);
        //==================================
    }
    //蛇移动的方法
    public void move(){
        checkeMove(wolf);
//        snake.move();
    }

    private void checkeMove(Wolf snake) {
        if(!snake.checkBoundary_wolf()){
            /*if(snake.checkBox()){
//                蛇和箱子一起移动
                snake.moveBox(box);
                snake.move();
            }else{
//                蛇移动
                snake.move();
            }*/
            //检查是否有数
            if(!checkTree()){
                /*if(!wolf.checkWolf_box(box)){
                }*/
                if(!wolf.checkBoundary_box(box, tree1, tree2, tree3)){
                    snake.move();
                }else if(wolf.checkWolf_Box(box, tree1, tree2, tree3)){//检查箱子与狼是否重合，不重合则让狼移动
                    snake.move();
                }
            }

            if(box.checkBox(box, wolf)){
                if(!wolf.checkBoundary_box(box, tree1, tree2, tree3)){
                    wolf.moveBox(box);
                    /*if(!wolf.checkWolf_box(box)){
                    }*/
                }
                //检查箱子是否到达指定位置？？？？？？？？？？？？？？？？？？？？、
                if(box.checkBox_Destination(box)){
                    JOptionPane.showMessageDialog(null, "Congratulations on the game!");
                    game.start();
                    wolf = new Wolf();
                }
            }
        }
        //检查是否有盒子
        /*if(true){
            snake.move();
        }*/
    }

    private boolean checkTree() {
        boolean flag = false;
        if(wolf.checkTree(tree1, tree2, tree3)){
            flag = true;
        }
        return flag;
    }

    //转向的方法
    public void turnTo(String name){
        //返回带指定名称的枚举类型，示例如下：
        Direction dir = Direction.valueOf(name);
        wolf.turnTo(dir);
        move();
    }
}
