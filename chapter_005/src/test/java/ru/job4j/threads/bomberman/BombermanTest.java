package ru.job4j.threads.bomberman;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class BombermanTest {

    @Test
    public void randomMoveTest() throws InterruptedException {
        Bomberman bomberman = new Bomberman(15);
        bomberman.addMonster(5, 5);
        bomberman.addMonster(9, 9);
        bomberman.setPlayer(3, 3);
        bomberman.lockField(6, 8);
        bomberman.activate();
        bomberman.moveUp();
        bomberman.moveUp();
        bomberman.moveDown();
        bomberman.moveRight();
        bomberman.moveLeft();
        TimeUnit.SECONDS.sleep(30);
    }

}