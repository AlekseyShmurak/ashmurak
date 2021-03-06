package ru.job4j.chess;

public class Cell {
    private int x;
    private int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return this.x;
    }

    public int getY() {
        return y;
    }

    public boolean eql(Cell cell) {
        return this.getY() == cell.getY() && this.getX() == cell.getX() ? true : false;
    }
}
