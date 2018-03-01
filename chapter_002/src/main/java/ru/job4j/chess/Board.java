package ru.job4j.chess;

import ru.job4j.chess.exceptions.*;

public class Board {
    Figure[] figures = new Figure[32];
    int indexFigures = 0;

    public void add( Figure figure) {
        figures[indexFigures] = figure;
        indexFigures++;
    }

    public boolean move(Cell source, Cell dest) throws ImposibleMoveException, FigureNotFoundException, OccupiedWayException{
        boolean result = false;
        for (int i = 0; i < this.figures.length; i++) {
            if (this.figures[i] == null) {
                break;
            } else if (this.figures[i].position.eql(source)) {
                result = true;
                Cell[] passingCells = this.figures[i].way(source,dest);
                for (Cell cell : passingCells) {
                    for (Figure figure : this.figures) {
                        if (figure == null){
                            break;
                        }else if (figure.position.eql(cell)) {
                            throw new OccupiedWayException("На пути стоит другая фигура");
                        }
                    }
                }
            }
            if (result) {
                figures[i] = figures[i].copy(dest);
                break;
            }
        }
        if(!result) {
            throw new FigureNotFoundException ("Поле пустое");
        }
        return result;
    }

}
