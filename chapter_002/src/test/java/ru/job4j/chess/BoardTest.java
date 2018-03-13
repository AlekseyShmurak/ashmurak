package ru.job4j.chess;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.chess.exceptions.FigureNotFoundException;
import ru.job4j.chess.exceptions.ImposibleMoveException;
import ru.job4j.chess.exceptions.OccupiedWayException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class BoardTest {

    private final ByteArrayOutputStream mem = new ByteArrayOutputStream();
    private final PrintStream out = System.out;

    @Before
    public void loadMem() {
        System.setOut(new PrintStream(this.mem));
    }

    @After
    public void loadSys() {
        System.setOut(this.out);
    }

    @Test
    public void figureNotFoundTest() {
        Board board = new Board();
        board.add(new Bishop(new Cell(3, 3)));
        try {
            board.move(new Cell(2, 2), new Cell(2, 4));
        } catch (ImposibleMoveException e) {
            System.out.println("Фигура так не ходит");
        } catch (FigureNotFoundException e) {
            System.out.print("Поле пустое");
        } catch (OccupiedWayException e) {
            System.out.println("На пути стоит другая фигура");
        }
        assertThat(this.mem.toString(), is(String.format("Поле пустое")));
    }

    @Test
    public void bishopImposibleMoveTest() {
        Board board = new Board();
        board.add(new Bishop(new Cell(3, 3)));
        try {
            board.move(new Cell(3, 3), new Cell(6, 5));
        } catch (ImposibleMoveException e) {
            System.out.print("Фигура так не ходит");
        } catch (FigureNotFoundException e) {
            System.out.println("Поле пустое");
        } catch (OccupiedWayException e) {
            System.out.println("На пути стоит другая фигура");
        }
        assertThat(this.mem.toString(), is(String.format("Фигура так не ходит")));
    }

    @Test
    public void bishopOccupiedWayTest() {
        Board board = new Board();
        board.add(new Bishop(new Cell(1, 1)));
        board.add(new Bishop(new Cell(4, 4)));
        try {
            board.move(new Cell(1, 1), new Cell(8, 8));
        } catch (ImposibleMoveException e) {
            System.out.println("Фигура так не ходит");
        } catch (FigureNotFoundException e) {
            System.out.println("Поле пустое");
        } catch (OccupiedWayException e) {
            System.out.print("На пути стоит другая фигура");
        }
        assertThat(this.mem.toString(), is(String.format("На пути стоит другая фигура")));
    }

    @Test
    public void bishopMoveTest() {
        Board board = new Board();
        board.add(new Bishop(new Cell(3, 3)));
        try {
            board.move(new Cell(3, 3), new Cell(6, 6));
            board.move(new Cell(6, 6), new Cell(4, 8));
            board.move(new Cell(4, 8), new Cell(1, 5));
        } catch (ImposibleMoveException e) {
            System.out.println("Фигура так не ходит");
        } catch (FigureNotFoundException e) {
            System.out.println("Поле пустое");
        } catch (OccupiedWayException e) {
            System.out.println("На пути стоит другая фигура");
        }
        assertThat(this.mem.toString(), is(String.format("")));
    }

    @Test
    public void rookImposibleMoveTest() {
        Board board = new Board();
        board.add(new Rook(new Cell(3, 3)));
        try {
            board.move(new Cell(3, 3), new Cell(6, 5));
        } catch (ImposibleMoveException e) {
            System.out.print("Фигура так не ходит");
        } catch (FigureNotFoundException e) {
            System.out.println("Поле пустое");
        } catch (OccupiedWayException e) {
            System.out.println("На пути стоит другая фигура");
        }
        assertThat(this.mem.toString(), is(String.format("Фигура так не ходит")));
    }

    @Test
    public void rookOccupiedWayTest() {
        Board board = new Board();
        board.add(new Rook(new Cell(1, 1)));
        board.add(new Bishop(new Cell(1, 4)));
        try {
            board.move(new Cell(1, 1), new Cell(1, 6));
        } catch (ImposibleMoveException e) {
            System.out.println("Фигура так не ходит");
        } catch (FigureNotFoundException e) {
            System.out.println("Поле пустое");
        } catch (OccupiedWayException e) {
            System.out.print("На пути стоит другая фигура");
        }
        assertThat(this.mem.toString(), is(String.format("На пути стоит другая фигура")));
    }

    @Test
    public void rookMoveTest() {
        Board board = new Board();
        board.add(new Rook(new Cell(1, 1)));
        try {
            board.move(new Cell(1, 1), new Cell(1, 6));
            board.move(new Cell(1, 6), new Cell(5, 6));
            board.move(new Cell(5, 6), new Cell(5, 3));
        } catch (ImposibleMoveException e) {
            System.out.println("Фигура так не ходит");
        } catch (FigureNotFoundException e) {
            System.out.println("Поле пустое");
        } catch (OccupiedWayException e) {
            System.out.println("На пути стоит другая фигура");
        }
        assertThat(this.mem.toString(), is(String.format("")));
    }

    @Test
    public void queenImposibleMoveTest() {
        Board board = new Board();
        board.add(new Queen(new Cell(3, 3)));
        try {
            board.move(new Cell(3, 3), new Cell(6, 5));
        } catch (ImposibleMoveException e) {
            System.out.print("Фигура так не ходит");
        } catch (FigureNotFoundException e) {
            System.out.println("Поле пустое");
        } catch (OccupiedWayException e) {
            System.out.println("На пути стоит другая фигура");
        }
        assertThat(this.mem.toString(), is(String.format("Фигура так не ходит")));
    }

    @Test
    public void queenOccupiedWayTest() {
        Board board = new Board();
        board.add(new Queen(new Cell(1, 1)));
        board.add(new Bishop(new Cell(1, 4)));
        try {
            board.move(new Cell(1, 1), new Cell(1, 6));
        } catch (ImposibleMoveException e) {
            System.out.println("Фигура так не ходит");
        } catch (FigureNotFoundException e) {
            System.out.println("Поле пустое");
        } catch (OccupiedWayException e) {
            System.out.print("На пути стоит другая фигура");
        }
        assertThat(this.mem.toString(), is(String.format("На пути стоит другая фигура")));
    }

    @Test
    public void queenMoveTest() {
        Board board = new Board();
        board.add(new Queen(new Cell(1, 1)));
        try {
            board.move(new Cell(1, 1), new Cell(3, 3));
            board.move(new Cell(3, 3), new Cell(3, 1));
            board.move(new Cell(3, 1), new Cell(1, 3));
        } catch (ImposibleMoveException e) {
            System.out.println("Фигура так не ходит");
        } catch (FigureNotFoundException e) {
            System.out.println("Поле пустое");
        } catch (OccupiedWayException e) {
            System.out.println("На пути стоит другая фигура");
        }
        assertThat(this.mem.toString(), is(String.format("")));
    }

    @Test
    public void knightImposibleMoveTest() {
        Board board = new Board();
        board.add(new Knight(new Cell(3, 3)));
        try {
            board.move(new Cell(3, 3), new Cell(6, 5));
        } catch (ImposibleMoveException e) {
            System.out.print("Фигура так не ходит");
        } catch (FigureNotFoundException e) {
            System.out.println("Поле пустое");
        } catch (OccupiedWayException e) {
            System.out.println("На пути стоит другая фигура");
        }
        assertThat(this.mem.toString(), is(String.format("Фигура так не ходит")));
    }

    @Test
    public void knightOccupiedWayTest() {
        Board board = new Board();
        board.add(new Knight(new Cell(1, 1)));
        board.add(new Bishop(new Cell(2, 3)));
        try {
            board.move(new Cell(1, 1), new Cell(2, 3));
        } catch (ImposibleMoveException e) {
            System.out.println("Фигура так не ходит");
        } catch (FigureNotFoundException e) {
            System.out.println("Поле пустое");
        } catch (OccupiedWayException e) {
            System.out.print("На пути стоит другая фигура");
        }
        assertThat(this.mem.toString(), is(String.format("На пути стоит другая фигура")));
    }

    @Test
    public void knightMoveTest() {
        Board board = new Board();
        board.add(new Knight(new Cell(1, 1)));
        try {
            board.move(new Cell(1, 1), new Cell(2, 3));
            board.move(new Cell(2, 3), new Cell(4, 4));
            board.move(new Cell(4, 4), new Cell(3, 2));
        } catch (ImposibleMoveException e) {
            System.out.println("Фигура так не ходит");
        } catch (FigureNotFoundException e) {
            System.out.println("Поле пустое");
        } catch (OccupiedWayException e) {
            System.out.println("На пути стоит другая фигура");
        }
        assertThat(this.mem.toString(), is(String.format("")));
    }

    @Test
    public void kingImposibleMoveTest() {
        Board board = new Board();
        board.add(new King(new Cell(3, 3)));
        try {
            board.move(new Cell(3, 3), new Cell(6, 5));
        } catch (ImposibleMoveException e) {
            System.out.print("Фигура так не ходит");
        } catch (FigureNotFoundException e) {
            System.out.println("Поле пустое");
        } catch (OccupiedWayException e) {
            System.out.println("На пути стоит другая фигура");
        }
        assertThat(this.mem.toString(), is(String.format("Фигура так не ходит")));
    }

    @Test
    public void kingOccupiedWayTest() {
        Board board = new Board();
        board.add(new King(new Cell(1, 1)));
        board.add(new Bishop(new Cell(2, 2)));
        try {
            board.move(new Cell(1, 1), new Cell(2, 2));
        } catch (ImposibleMoveException e) {
            System.out.println("Фигура так не ходит");
        } catch (FigureNotFoundException e) {
            System.out.println("Поле пустое");
        } catch (OccupiedWayException e) {
            System.out.print("На пути стоит другая фигура");
        }
        assertThat(this.mem.toString(), is(String.format("На пути стоит другая фигура")));
    }

    @Test
    public void kingMoveTest() {
        Board board = new Board();
        board.add(new King(new Cell(2, 2)));
        try {
            board.move(new Cell(2, 2), new Cell(3, 3));
            board.move(new Cell(3, 3), new Cell(2, 3));
            board.move(new Cell(2, 3), new Cell(1, 4));
        } catch (ImposibleMoveException e) {
            System.out.println("Фигура так не ходит");
        } catch (FigureNotFoundException e) {
            System.out.println("Поле пустое");
        } catch (OccupiedWayException e) {
            System.out.println("На пути стоит другая фигура");
        }
        assertThat(this.mem.toString(), is(String.format("")));
    }

    @Test
    public void pawnImposibleMoveTest() {
        Board board = new Board();
        board.add(new Pawn(new Cell(3, 3)));
        try {
            board.move(new Cell(3, 3), new Cell(4, 3));
        } catch (ImposibleMoveException e) {
            System.out.print("Фигура так не ходит");
        } catch (FigureNotFoundException e) {
            System.out.println("Поле пустое");
        } catch (OccupiedWayException e) {
            System.out.println("На пути стоит другая фигура");
        }
        assertThat(this.mem.toString(), is(String.format("Фигура так не ходит")));
    }

    @Test
    public void pawnOccupiedWayTest() {
        Board board = new Board();
        board.add(new Pawn(new Cell(1, 1)));
        board.add(new Bishop(new Cell(1, 2)));
        try {
            board.move(new Cell(1, 1), new Cell(1, 2));
        } catch (ImposibleMoveException e) {
            System.out.println("Фигура так не ходит");
        } catch (FigureNotFoundException e) {
            System.out.println("Поле пустое");
        } catch (OccupiedWayException e) {
            System.out.print("На пути стоит другая фигура");
        }
        assertThat(this.mem.toString(), is(String.format("На пути стоит другая фигура")));
    }

    @Test
    public void pawnMoveTest() {
        Board board = new Board();
        board.add(new Pawn(new Cell(2, 2)));
        try {
            board.move(new Cell(2, 2), new Cell(2, 3));
            board.move(new Cell(2, 3), new Cell(2, 4));
            board.move(new Cell(2, 4), new Cell(2, 5));
        } catch (ImposibleMoveException e) {
            System.out.println("Фигура так не ходит");
        } catch (FigureNotFoundException e) {
            System.out.println("Поле пустое");
        } catch (OccupiedWayException e) {
            System.out.println("На пути стоит другая фигура");
        }
        assertThat(this.mem.toString(), is(String.format("")));
    }

}