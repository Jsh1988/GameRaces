package com.gameraces.object;

import javax.swing.*;
import java.awt.*;

public class Enemy {
    public int speed;
    public int x;
    public int y;

    Image image = new ImageIcon("resources/rival.png").getImage();
    Road road;

    public Rectangle getRect(){
        return new Rectangle(x,y,150,63);
    }

    public Enemy(int x, int y, int speed, Road road){
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.road = road;
    }
    public void move(){
        x = x - road.player.speed + speed;
    }
}
