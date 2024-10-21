package com.osmig;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    // SCREEEN SETTINGS
    final int originalTileSize = 16; // 16x16 px tile size
    final int scale = 3; // helps to scale our original size tile

    final int tileSize = originalTileSize * scale; // 48x48 px tile
    // decide how many tiles horizontally and verticall
    final int maxTileCol = 16;
    final int getMaxTileRow = 12;
    final  int screenWidth = tileSize * maxTileCol; // 768px
    final int screenHeight = tileSize * getMaxTileRow; // 576px

    Thread gameThread;

    // constructor
    public  GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }
    public  void  startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        while(gameThread != null){ // game loop
            //System.out.println("the game loop is running");
            // 1. UPDATE: update information such as character position
            update();

            repaint();


            // 2. DRAW: draw the screen  with updated information
        }
    }

    public void update(){

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g); // super means parent class, jpanel

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);
        g2.fillRect(100, 100, tileSize, tileSize);

        g2.dispose();

    }


}//end of class