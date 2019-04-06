package kk;

import java.util.ArrayList;
import java.util.List;

/**
 * Cell with coordinates. Also it uses for constructing a path so has pointer to the next cell.
 */
public class Cell {

    private int row;
    private int col;
    private Cell linkCell; /*the pointer to the next cell in a path*/

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Cell(int row, int col, Cell linkCell) {
        this.row = row;
        this.col = col;
        this.linkCell = linkCell;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void normilize(int maxCols) {
        if (col < 0) {
            col = maxCols + col;
        }
    }

    public List<Cell> getCellHistory() {
        List<Cell> result = new ArrayList<>();

        Cell cur = this;
        result.add(cur);
        while (cur.linkCell != null) {
            cur = cur.linkCell;
            result.add(cur);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Cell that = (Cell) o;

        if (row != that.row) {
            return false;
        }
        return col == that.col;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + col;
        return result;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)", row, col);
    }

}
