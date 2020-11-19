package com.nsu.game.view;

import com.nsu.game.model.GameServer;

import javax.swing.*;
import java.awt.*;

public class FrameGame extends JFrame {
    public FrameGame(GameServer server){
        this.setTitle("【=推箱子=】");
//        this.setResizable(false);
        PanelGame canvas = new PanelGame(server);
        this.add(canvas, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
