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
            if (source.getX() - dest.getX() < 0 && source.getY() - dest.getY() < 0) {
                for (int i = 1; i <= passingCells.length; i++) {
                    passingCells[i - 1] = new Cell(source.getX() + i, source.getY() + i);
                }
            } else if (source.getX() - dest.getX() > 0 && source.getY() - dest.getY() < 0) {
                for (int i = 1; i <= passingCells.length; i++) {
                    passingCells[i - 1] = new Cell(source.getX() - i, source.getY() + i);
                }
            } else if (source.getX() - dest.getX() < 0 && source.getY() - dest.getY() > 0) {
                for (int i = 1; i <= passingCells.length; i++) {
                    passingCells[i - 1] = new Cell(source.getX() + i, source.getY() - i);
                }
            } else if (source.getX() - dest.getX() > 0 && source.getY() - dest.getY() > 0) {
                for (int i = 1; i <= passingCells.length; i++) {
                    passingCells[i - 1] = new Cell(source.getX() - i, source.getY() - i);
                }
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
