package ru.job4j.chess;

import ru.job4j.chess.exceptions.*;
public class Queen extends Figure {
    public Queen(Cell position) {
        super(position);
    }

    @Override
    Cell[] way(Cell source, Cell dest)  throws ImposibleMoveException {
        Cell[] passingCells;
        int deltaX = source.getX() - dest.getX() == 0 ? 0 : (source.getX() - dest.getX()) / Math.abs(source.getX() - dest.getX());
        int deltaY = source.getY() - dest.getY() == 0 ? 0 : (source.getY() - dest.getY()) / Math.abs(source.getY() - dest.getY());
        if (source.getX() - dest.getX() == source.getY() - dest.getY() || source.getX() + source.getY() == dest.getX() + dest.getY()) {
            passingCells = new Cell[Math.abs(source.getX() - dest.getX())];
            if (source.getX() - dest.getX() == source.getY() - dest.getY() || source.getX() + source.getY() == dest.getX() + dest.getY()) {
                for (int i = 1; i <= passingCells.length; i++) {
                    passingCells[i - 1] = new Cell(source.getX() - i * deltaX, source.getY() - i * deltaY);
                }
            }
        } else if (deltaX == 0 || deltaY == 0) {
            passingCells = new Cell[deltaX == 0 ? Math.abs(source.getY() - dest.getY()) : Math.abs(source.getX() - dest.getX())];
            for (int i = 1; i <= passingCells.length; i++) {
                passingCells[i - 1] = new Cell(source.getX() - i * deltaX, source.getY() - i * deltaY);
            }
        } else {
            throw new ImposibleMoveException("Фигура так не ходит");
        }
        return passingCells;
    }

    @Override
    Figure copy(Cell dest) {
        return new Queen(dest);
    }
}