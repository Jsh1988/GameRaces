package com.gameraces;

import com.gameraces.object.Road;

import javax.swing.*;

public class GameRaces {

    public static final int WIDTH = 890;
    public static final int HEIGHT = 700;

    public static void main(String[] args) {
        JFrame frame = new JFrame("GameRaces");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(WIDTH,HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.add(new Road());
        frame.setVisible(true);
    }

}
