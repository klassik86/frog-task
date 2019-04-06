package kk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FrogWorld {

    public static final int MAX_ROWS = 10;
    public static final int MAX_COLUMNS = 16; /*sectors*/

    public static final int FROG_CELL = 1;
    public static final int TREE_CELL = 2;
    public static final int FINISH_CELL = 3;
    public static final int USED_CELL = 4;

    private Cell finishCell;
    private int minJumpCount;
    private Cell minJumpCell;
    private boolean terminate;
    private int[][] fields;

    public FrogWorld() {
        this.fields = new int[MAX_ROWS][MAX_COLUMNS];
    }

    public int getMinJumpCount() {
        return minJumpCount;
    }

    public Cell getMinJumpCell() {
        return minJumpCell;
    }

    public int[][] getFields() {
        return fields;
    }

    public void init(Cell frogCell, Cell finishCell, Cell... treeCells) {
        if (frogCell != null) {
            this.fields[frogCell.getRow()][frogCell.getCol()] = FROG_CELL;
        }

        this.finishCell = finishCell;
        this.fields[finishCell.getRow()][finishCell.getCol()] = FINISH_CELL;

        for (Cell treeCell : treeCells) {
            this.fields[treeCell.getRow()][treeCell.getCol()] = TREE_CELL;
        }
    }

    public void startSearch() {
        reverseSearch(0, Collections.singletonList(finishCell));
    }

    private void reverseSearch(int jumpCount, List<Cell> parentCells) {
        ArrayList<Cell> resultCells = new ArrayList<>();
        for (Cell parentCell : parentCells) {
            if (terminate) {
                break;
            }
            for (int jumpType = 0; jumpType < 5; jumpType++) {
                int curJumpCount = jumpCount + 1;

                Cell curCell = reverseJump(jumpType, parentCell);

                if (curCell == null) {
                    /*wrong way*/
                    continue;
                }
                if (fields[curCell.getRow()][curCell.getCol()] == USED_CELL
                        || fields[curCell.getRow()][curCell.getCol()] == FINISH_CELL) {
                    continue; /*we were here early, so this cell belongs to shorter way*/
                }

                if (fields[curCell.getRow()][curCell.getCol()] == FROG_CELL) {
                    minJumpCount = curJumpCount;
                    minJumpCell = curCell;
                    terminate = true;
                    break;
                }
                fields[curCell.getRow()][curCell.getCol()] = USED_CELL;
                resultCells.add(curCell);
            }
        }

        if (!resultCells.isEmpty() && !terminate) {
            reverseSearch(jumpCount + 1, resultCells);
        }
    }

    /**
     * Frog reverse jumping. Calculating a frog cell to reach a cell from parameter.
     *
     * @param jumpType jump type
     *      There are 5 jump types, but we find reverse of it.
     *      0 - one cell ahead, two cells to the left
     *      1 - two cells ahead, one cell to the left
     *      2 - three cells ahead
     *      3 - two cells ahead, one cell to the right
     *      4 - one cell ahead, two cells to the right
     * @return a frog cell; null if this jump is prohibited (trees or boundaries)
     */
    Cell reverseJump(int jumpType, Cell parentCell) {
        int curRow = parentCell.getRow();
        int curCol = parentCell.getCol();
        Cell cell;
        if (jumpType == 0) {
            cell = new Cell(curRow + 2, curCol - 1, parentCell);
        } else if (jumpType == 1) {
            cell = new Cell(curRow + 1, curCol - 2, parentCell);
        } else if (jumpType == 2) {
            cell = new Cell(curRow, curCol - 3, parentCell);
        } else if (jumpType == 3) {
            cell = new Cell(curRow - 1, curCol - 2, parentCell);
        } else {
            cell = new Cell(curRow - 2, curCol - 1, parentCell);
        }
        if (cell.getCol() < 0) {
            cell.normilize(MAX_COLUMNS);
        }

        if (cell.getRow() < 0 || cell.getRow() >= MAX_ROWS || /*if the frog jumps over boundaries, */
                fields[cell.getRow()][cell.getCol()] == TREE_CELL) { /*or jumps on a tree*/
            return null;
        }
        return cell;
    }

}
