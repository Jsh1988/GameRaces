package com.gameraces.object;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {

    public static final int MAX_SPEED = 50;
    public static final int MAX_TOP = 70;
    public static final int MAX_BOTTOM = 560;

    Image image_centr = new ImageIcon("resources/carone.png").getImage();
    Image image_left = new ImageIcon("resources/carjneleft.png").getImage();
    Image image_right = new ImageIcon("resources/caroneright.png").getImage();
    Image image = image_centr;

    protected int speed = 0;
    protected int acceleration = 0;
    protected int way = 0;

    protected int x = 30;
    protected int y = 100;
    protected int dy = 0;

    protected int layer1 = 0;
    protected int layer2 = 900;

    public Rectangle getRect(){
        return new Rectangle(x,y,150,53);
    }

    public void move(){
        way += speed;
        speed += acceleration;
        if(speed <= 0) speed = 0;
        if(speed >= MAX_SPEED) speed = MAX_SPEED;
        y -= dy;
        if(y <= MAX_TOP) y = MAX_TOP;
        if(y >= MAX_BOTTOM) y = MAX_BOTTOM;
        if(layer2 - speed <= 0) {
            layer1 = 0;
            layer2 = 900;
        }else{
            layer1 -= speed;
            layer2 -= speed;
        }
    }

    public void keyPressed(KeyEvent event){
        //JOptionPane.showMessageDialog(null,"Pressed");
        int key = event.getKeyCode();
        if(key == KeyEvent.VK_RIGHT){
            acceleration = 1;
        }
        if(key == KeyEvent.VK_LEFT){
            acceleration = -1;
        }
        if(key == KeyEvent.VK_UP){
            dy = 5;
            image = image_left;
        }
        if(key == KeyEvent.VK_DOWN){
            dy = -5;
            image = image_right;
        }
    }
    public void keyReleased(KeyEvent event){
        int key = event.getKeyCode();
        if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT ){
            acceleration = 0;
        }
        if(key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN ){
            dy = 0;
            image = image_centr;
        }
    }
}
