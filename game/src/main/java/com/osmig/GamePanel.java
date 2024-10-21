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

    // FPS
    int FPS = 60;

    KeyHandler keyh = new KeyHandler();
    Thread gameThread;

    // set players default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    // constructor
    public  GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyh);
        this.setFocusable(true);

    }
    public  void  startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        double drawInterval = 1000000000/FPS; // 0.01666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){ // game loop

            update();  // 1. UPDATE: update information such as character position

            repaint(); // 2. DRAW: draw the screen  with updated information

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000; // sleep takes in milliseconds, so we converted to it to avoid issues
                if (remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long)remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void update(){
        if(keyh.upPressed == true){
            playerY -= playerSpeed;
        } else if (keyh.downPressed == true) {
            playerY += playerSpeed;
        } else if (keyh.leftPressed == true) {
            playerX -= playerSpeed;
        } else if (keyh.rightPressed == true) {
            playerX += playerSpeed;
        }

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g); // super means parent class, jpanel

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);

        g2.dispose();

    }


}//end of class