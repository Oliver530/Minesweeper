package model.cell;

/**
 * Created by olivergerhardt on 28.08.17.
 */
public class CellInfo {

    private final Cell cell;
    private final int countOfNeighbourMines;

    public CellInfo(Cell cell, int countOfNeighbourMines) {
        this.cell = cell;
        this.countOfNeighbourMines = countOfNeighbourMines;
    }

    public CellInfo(Cell cell) {
        this(cell, 0);
    }

    public boolean isVisited() {
        return cell.isVisited();
    }

    public int getCountOfNeighbourMines() {
        return this.countOfNeighbourMines;
    }

    public boolean isMarkedAsBomb() {
        return cell.isMarkedAsBomb();
    }

    public boolean isMine() {
        if (!isVisited()) {
            throw new IllegalStateException("isMine() check is only valid on visited cells!");
        }
        return cell.isMine();
    }
}
