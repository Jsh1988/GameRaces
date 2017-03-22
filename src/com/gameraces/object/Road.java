package com.gameraces.object;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Road extends JPanel implements ActionListener, Runnable {

    Timer timer = new Timer(20, this);

//    Image image = new ImageIcon(getClass().getClassLoader().getResource("resources/road.png")).getImage();
    Image image = new ImageIcon("resources/road.png").getImage();

    Player player = new Player();

    Thread enemiesFactory = new Thread(this);

    List<Enemy> enemies =  new ArrayList<Enemy>();

    public Road(){
        timer.start();
        enemiesFactory.start();
        addKeyListener(new MyKeyAdapter());
        setFocusable(true);
    }

    @Override
    public void run() {
        while(true){
            Random random = new Random();
            try {
                Thread.sleep(random.nextInt(5000));
                enemies.add(new Enemy(1000, random.nextInt(600), random.nextInt(40), this ) );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class MyKeyAdapter extends KeyAdapter{
        public void keyPressed(KeyEvent event){
            player.keyPressed(event);
        }
        public void keyReleased(KeyEvent event){
            player.keyReleased(event);
        }
    }

    public void paint(Graphics graphics){
        graphics = (Graphics2D) graphics;
        graphics.drawImage(image, player.layer1,0,null);
        graphics.drawImage(image, player.layer2,0,null);
        graphics.drawImage(player.image, player.x, player.y,null);

        double speed = (200/Player.MAX_SPEED) * player.speed;
        graphics.setColor(Color.WHITE);
        Font font = new Font("Arial", Font.ITALIC, 20);
        graphics.setFont(font);
        graphics.drawString("Скорость: "+ speed +" км/ч",680,130);

        Iterator<Enemy> iterator = enemies.iterator();
        while(iterator.hasNext()){
            Enemy enemy = iterator.next();
            if(enemy.x >= 2000 || enemy.x <= -2000){
                iterator.remove();
            }else{
                enemy.move();
                graphics.drawImage(enemy.image, enemy.x, enemy.y, null);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.move();
        repaint();
        testCollisionWithEnemies();
        testWin();
    }

    private void testWin() {
        if(player.way > 50000){
            JOptionPane.showMessageDialog(null,"Вы выиграли!");
            System.exit(0);
        }
    }

    private void testCollisionWithEnemies() {
        Iterator<Enemy> iterator = enemies.iterator();
        while(iterator.hasNext()){
            Enemy enemy = iterator.next();
            if(player.getRect().intersects(enemy.getRect())){
                //iterator.remove();
                JOptionPane.showMessageDialog(null,"Вы проиграли!");
                System.exit(1);
            }
        }

    }
}
