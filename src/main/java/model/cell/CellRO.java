package model.cell;

/**
 * Created by olivergerhardt on 01.09.17.
 */
public interface CellRO {

    boolean isVisited();
    boolean isMine();
    boolean isMarkedAsBomb();
    int getCountOfNeighbourMines();

}
