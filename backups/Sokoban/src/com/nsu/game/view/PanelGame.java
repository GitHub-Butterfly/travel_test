package com.nsu.game.view;

import com.nsu.game.model.GameServer;

import javax.swing.*;
import java.awt.*;

public class PanelGame extends JPanel {
    private GameServer server;

    public PanelGame(GameServer server) {
        this.server = server;
        this.setBackground(Color.black);
//        paintBackground();
    }


    //Java重绘时的固定写法！！！
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);//清屏
        server.draw(g);//画自己想要的图
    }
}
