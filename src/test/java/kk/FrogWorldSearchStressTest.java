package kk;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Stress test for application.
 * Check that application is not crashed and get result
 */
public class FrogWorldSearchStressTest extends AbstractFrogWorldTest {

    private static final int EXP_COUNT = 30;
    private static final int TREE_COUNT = 100;
    private static final Random RANDOM = new Random();

    @Test
    public void testSearching() {
        for (int exp = 0; exp < EXP_COUNT; exp++) {
            System.out.println("iteration = " + (exp + 1));
            runIteration();
            System.out.println();
        }
    }

    private void runIteration() {
        FrogWorld frogWorld = new FrogWorld();

        Cell frogCell = null;
        Cell finishCell = null;
        List<Cell> treeCells = new ArrayList<>();
        boolean success = false;

        List<Cell> availableCells = new ArrayList<>();
        for (int i = 0; i < FrogWorld.MAX_ROWS; i++) {
            for (int j = 0; j < FrogWorld.MAX_COLUMNS; j++) {
                 availableCells.add(new Cell(i, j));
            }
        }

        frogCell = availableCells.remove(RANDOM.nextInt(availableCells.size()));
        finishCell = availableCells.remove(RANDOM.nextInt(availableCells.size()));
        for (int i = 0; i < TREE_COUNT; i++) {
            treeCells.add(availableCells.remove(RANDOM.nextInt(availableCells.size())));
        }

        frogWorld.init(frogCell, finishCell, treeCells.toArray(new Cell[]{}));
        System.out.println("fields before searching = " + Arrays.deepToString(frogWorld.getFields()));

        frogWorld.startSearch();

        System.out.println("SEARCHING has finished");
        System.out.println("jumpCount = " + frogWorld.getMinJumpCount());
        if (frogWorld.getMinJumpCount() > 0) {
            System.out.println("jumpPath = " + convertHistory(frogWorld.getMinJumpCell().getCellHistory()));
        }
        System.out.println("fields = " + Arrays.deepToString(frogWorld.getFields()));
    }

}
