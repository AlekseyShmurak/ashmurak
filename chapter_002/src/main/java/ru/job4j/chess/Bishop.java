package ru.job4j.chess;

import ru.job4j.chess.exceptions.*;
public class Bishop extends Figure {
    public Bishop(Cell position) {
        super(position);
    }

    @Override
    Cell[] way(Cell source, Cell dest)  throws ImposibleMoveException {
        Cell[] passingCells = new Cell[Math.abs(source.getX() - dest.getX())];
        if (source.getX() - dest.getX() == source.getY() - dest.getY() || source.getX() + source.getY() == dest.getX() + dest.getY()) {
            int deltaX = (source.getX() - dest.getX()) / Math.abs(source.getX() - dest.getX());
            int deltaY = (source.getY() - dest.getY()) / Math.abs(source.getY() - dest.getY());
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
        return new Bishop(dest);
    }
}
