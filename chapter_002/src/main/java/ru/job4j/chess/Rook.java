package ru.job4j.chess;

import ru.job4j.chess.exceptions.*;

public class Rook extends Figure {
    public Rook( Cell position) {
        super(position);
    }

    @Override
    Cell[] way(Cell source, Cell dest)  throws ImposibleMoveException {
        Cell[] passingCells;
        if ( source.getX() == dest.getX()) {
            passingCells = new Cell[Math.abs(source.getY() - dest.getY())];
            if ( source.getY() < dest.getY() ) {
                for (int i = 1; i <= passingCells.length; i++) {
                    passingCells[i-1] = new Cell(source.getX(), source.getY() + i);
                }
            } else {
                for (int i = 1; i <= passingCells.length; i++) {
                    passingCells[i - 1] = new Cell(source.getX(), source.getY() - i);
                }
            }
        } else if ( source.getY() == dest.getY()) {
            passingCells = new Cell[Math.abs(source.getX() - dest.getX())];
            if ( source.getX() < dest.getX() ) {
                for (int i = 1; i <= passingCells.length; i++) {
                    passingCells[i-1] = new Cell(source.getX() + i, source.getY());
                }
            } else {
                for (int i = 1; i <= passingCells.length; i++) {
                    passingCells[i - 1] = new Cell(source.getX() - i, source.getY());
                }
            }
        }else {
            throw new ImposibleMoveException("Фигура так не ходит");
        }
        return passingCells;
    }

    @Override
    Figure copy(Cell dest) {
        return new Rook(dest);
    }
}