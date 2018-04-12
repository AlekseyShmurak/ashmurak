package ru.job4j.threads.bomberman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Bomberman {
    private volatile boolean isActive = false;
    private final ReentrantLock[][] board;
    private final Random rndm = new Random();
    private final List<Thread> monsters = new ArrayList<>();
    private Player player;

    public Bomberman(int size) {
        this.board = new ReentrantLock[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = new ReentrantLock();
            }
        }
    }

    public void activate() {
        this.isActive = true;
        for (Thread monsret : monsters) {
            monsret.start();
        }
    }

    public void deactivate() {
        this.isActive = false;
    }

    public void lockField(int x, int y) {
        board[x][y].lock();
    }

    public void setPlayer(int x, int y) {
        this.player = new Player(x, y);
        board[x][y].lock();
    }

    public void addMonster(int x, int y) {
        monsters.add(new Thread(new Runnable() {
            String name = "Монстр " + monsters.size();
            @Override
            public void run() {
                randomMove(x, y, name);
            }
        }));
    }


    private void randomMove(int startX, int startY, String name) {
        int x = startX; int y = startY;
        int directionX = rndm.nextInt(3) - 1;
        int directionY = directionX == 0 ? 1 - 2 * (rndm.nextInt(2)) : 0;
        while (isActive) {
            if (x + directionX < 0 || y + directionY < 0 || x + directionX == board.length || y + directionY == board.length) {
                directionX = rndm.nextInt(3) - 1;
                directionY = directionX == 0 ? 1 - 2 * (rndm.nextInt(2)) : 0;
            } else {
                if (replace(board[x][y], board[x + directionX][y + directionY])) {
                    x = x + directionX;
                    y = y + directionY;
                    System.out.println(name + " переходит на поле " + x + " " + y);
                } else {
                    System.out.println("Поле " + x + " " + y + " занято");
                    directionX = rndm.nextInt(3) - 1;
                    directionY = directionX == 0 ? 1 - 2 * (rndm.nextInt(2)) : 0;
                }
            }
        }
    }


    public void moveUp() {
        if (isActive) {
            if (replace(board[this.player.x][this.player.y], board[this.player.x][this.player.y + 1])) {
               this.player.y = this.player.y + 1;
                System.out.println("Игрок переходит на поле " + this.player.x + " " + this.player.y);
            }
        }
    }

    public void moveDown() {
        if (isActive) {
            if (replace(board[this.player.x][this.player.y], board[this.player.x][this.player.y - 1])) {
                this.player.y = this.player.y - 1;
                System.out.println("Игрок переходит на поле " + this.player.x + " " + this.player.y);
            }
        }
    }

    public void moveLeft() {
        if (isActive) {
            if (replace(board[this.player.x][this.player.y], board[this.player.x - 1][this.player.y])) {
                this.player.x = this.player.x - 1;
                System.out.println("Игрок переходит на поле " + this.player.x + " " + this.player.y);
            }
        }
    }

    public void moveRight() {
        if (isActive) {
            if (replace(board[this.player.x][this.player.y], board[this.player.x + 1][this.player.y])) {
                this.player.x = this.player.x + 1;
                System.out.println("Игрок переходит на поле " + this.player.x + " " + this.player.y);
            }
        }
    }

    private boolean replace(ReentrantLock from, ReentrantLock to) {
        boolean rslt = false;
        from.lock();
        try {
            if (!to.isLocked()) {
                TimeUnit.MILLISECONDS.sleep(1000);
                to.lock();
                from.unlock();
                rslt = true;
            } else if (to.tryLock(1000, TimeUnit.MILLISECONDS)) {
                from.unlock();
                rslt = true;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return rslt;
    }

    private class Player {
        int x;
        int y;

        public Player(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
