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
            board.move(new Cell(2, 2), new Cell(2,4));
        } catch (ImposibleMoveException e) {
            System.out.println("Фигура так не ходит");
        } catch (FigureNotFoundException e) {
            System.out.println("Поле пустое");
        } catch (OccupiedWayException e) {
            System.out.println("На пути стоит другая фигура");
        }
        assertThat(this.mem.toString(),is(String.format("Поле пустое\r\n")));
    }

    @Test
    public void bishopImposibleMoveTest() {
        Board board = new Board();
        board.add(new Bishop(new Cell(3, 3)));
        try {
            board.move(new Cell(3, 3), new Cell(6,5));
        } catch (ImposibleMoveException e) {
            System.out.println("Фигура так не ходит");
        } catch (FigureNotFoundException e) {
            System.out.println("Поле пустое");
        } catch (OccupiedWayException e) {
            System.out.println("На пути стоит другая фигура");
        }
        assertThat(this.mem.toString(),is(String.format("Фигура так не ходит\r\n")));
    }

    @Test
    public void bishopOccupiedWayTest() {
        Board board = new Board();
        board.add(new Bishop(new Cell(1, 1)));
        board.add(new Bishop(new Cell(4, 4)));
        try {
            board.move(new Cell(1, 1), new Cell(8,8));
        } catch (ImposibleMoveException e) {
            System.out.println("Фигура так не ходит");
        } catch (FigureNotFoundException e) {
            System.out.println("Поле пустое");
        } catch (OccupiedWayException e) {
            System.out.println("На пути стоит другая фигура");
        }
        assertThat(this.mem.toString(),is(String.format("На пути стоит другая фигура\r\n")));
    }

    @Test
    public void BishopMoveTest() {
        Board board = new Board();
        board.add(new Bishop(new Cell(3, 3)));
        try {
            board.move(new Cell(3, 3), new Cell(6,6));
            board.move(new Cell(6, 6), new Cell(4, 8));
            board.move(new Cell(4, 8), new Cell(1, 5));
        } catch (ImposibleMoveException e) {
            System.out.println("Фигура так не ходит");
        } catch (FigureNotFoundException e) {
            System.out.println("Поле пустое");
        } catch (OccupiedWayException e) {
            System.out.println("На пути стоит другая фигура");
        }
    }


}