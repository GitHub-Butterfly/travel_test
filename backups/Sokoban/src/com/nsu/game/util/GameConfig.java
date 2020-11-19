package com.nsu.game.util;

public class GameConfig {
    private static int cellSize;//细胞大小
    private static int rows;//行数
    private static int cols;//列数column
    static{
        cellSize = 30;
        rows = 20;
        cols = 30;
    }

    //得到游戏画布的宽度，即列数x细胞大小
    public static int getCanvasWidth(){
        return cols * cellSize;
    }

    //得到游戏画布的宽度，即行数x细胞大小
    public static int getCanvasHeight(){
        return rows * cellSize;
    }

    public static int getCellSize() {
        return cellSize;
    }

    public static int getRows() {
        return rows;
    }

    public static int getCols() {
        return cols;
    }
}
