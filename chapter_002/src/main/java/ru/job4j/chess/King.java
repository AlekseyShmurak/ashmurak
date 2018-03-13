package ru.job4j.chess;

import ru.job4j.chess.exceptions.*;
public class King extends Figure {
    public King(Cell position) {
        super(position);
    }

    @Override
    Cell[] way(Cell source, Cell dest)  throws ImposibleMoveException {
        Cell[] passingCells = new Cell[1];
        if (Math.abs(source.getX() - dest.getX()) <= 1 && Math.abs(source.getY() - dest.getY()) <= 1) {
            passingCells[0] = dest;
        } else {
            throw new ImposibleMoveException("Фигура так не ходит");
        }
        return passingCells;
    }

    @Override
    Figure copy(Cell dest) {
        return new King(dest);
    }
}
