package com.osmig;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    // SCREEEN SETTINGS
    final int originalTileSize = 16; // 16x16 px tile size
    final int scale = 3; // helps to scale our original size tile

    public final int tileSize = originalTileSize * scale; // 48x48 px tile
    // decide how many tiles horizontally and verticall
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final  int screenWidth = tileSize * maxScreenCol; // 768px
    public final int screenHeight = tileSize * maxScreenRow; // 576px

    // world settings
    public  final int maxWorldCol = 50;
    public  final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // FPS
    int FPS = 60;

    // Tile manager
    TileManager tileM = new TileManager(this);

    KeyHandler keyh = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this, keyh);


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
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){ // game loop
            currentTime = System.nanoTime();
//            System.out.println("some meme");
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if(delta > 1) {
                update();  // 1. UPDATE: update information such as character position
                repaint(); // 2. DRAW: draw the screen  with updated information
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000){
                //System.out.print("FPS: " + drawCount + "\n");
                drawCount = 0;
                timer = 0;
            }

        }
    }

    public void update(){
        player.update();

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g); // super means parent class, jpanel

        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2); // draw tile before player

        player.draw(g2);

        g2.dispose();

    }


}//end of class