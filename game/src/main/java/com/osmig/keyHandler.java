package com.osmig;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){

        }
        if(code == KeyEvent.VK_S){

        }
        if(code == KeyEvent.VK_A){

        }
        if(code == KeyEvent.VK_D){

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
