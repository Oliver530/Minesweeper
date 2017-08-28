package minesweeper4java;

/**
 * Created by olivergerhardt on 28.08.17.
 */
public class CellInfo {

    private final Cell cell;

    public CellInfo(Cell cell) {
        this.cell = cell;
    }

    public boolean isVisited() {
        return cell.isVisited();
    }
}
