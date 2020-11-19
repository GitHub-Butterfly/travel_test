package com.nsu.game.control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PlayerController extends KeyAdapter {
    private GameController game;

    public PlayerController(GameController game) {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        String[] dirs = {"Left", "Up", "Right", "Down"};
//            System.out.println(e.getKeyCode());//测试：37-40
        int code = e.getKeyCode();
        if(code >= 37 && code <= 40){
//            model.turnTo(dirs[code - 37]);
            //用控制器GameController进行传参
            game.turnTo(dirs[code - 37]);
        }
    }
}
