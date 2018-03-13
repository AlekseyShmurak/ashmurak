package ru.job4j.chess;

import ru.job4j.chess.exceptions.*;
public class Pawn extends Figure {
    public Pawn(Cell position) {
        super(position);
    }

    @Override
    Cell[] way(Cell source, Cell dest)  throws ImposibleMoveException {
        Cell[] passingCells = new Cell[1];
        if (source.getX() == dest.getX() && source.getY()  + 1 == dest.getY()) {
            passingCells[0] = dest;
        } else {
            throw new ImposibleMoveException("Фигура так не ходит");
        }
        return passingCells;
    }

    @Override
    Figure copy(Cell dest) {
        return new Pawn(dest);
    }
}
