package com.nsu.game.control;

import com.nsu.game.model.GameServer;
import com.nsu.game.util.GameConfig;
import com.nsu.game.view.FrameGame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController {
    private FrameGame view;
    private GameServer model;
    private PlayerController player;
    private Timer gameTimer;//游戏线程

    public GameController(){
        model = new GameServer(this);
        view = new FrameGame(model);
        player = new PlayerController(this);

        //绑定时间监听对象
        gameTimer = new Timer(200, new GameHandler());
//        view.addKeyListener(new PlayerController());//绑定键盘监听
        view.addKeyListener(player);//绑定键盘监听

        //用于测试绘制，只绘制一次
        /*model.move();
        view.repaint();*/
    }

    public void start(){
        model.start();//随机生成食物
        gameTimer.start();
    }

    public void show(){
        view.setVisible(true);
        view.setSize(GameConfig.getCanvasWidth(),
                GameConfig.getCanvasHeight());
//        view.setResizable(false);
        view.setLocationRelativeTo(null);
    }

    public void turnTo(String name){
        model.turnTo(name);
    }

    //实现时间监听的内部类
    private class GameHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            //==================================
            //如果没有改变方向，蛇会一直向前移动
            //==================================
//======【推箱子测试，，，，，，，，，未完成】=============
//            model.move();
            view.repaint();
        }
    }
/*
    private class PlayerController extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            String[] dirs = {"Left", "Up", "Right", "Down"};
//            System.out.println(e.getKeyCode());//测试：37-40
            int code = e.getKeyCode();
            if(code >= 37 && code <= 40){
                model.turnTo(dirs[code - 37]);
            }
        }
    }
*/

}
