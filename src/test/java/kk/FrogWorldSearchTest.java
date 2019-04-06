package kk;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class FrogWorldSearchTest {

    @Test
    public void testOneJump() {
        FrogWorld frogWorld = new FrogWorld();
        frogWorld.init(new Cell(9, 0), new Cell(9, 3));
        frogWorld.startSearch();
        Assert.assertEquals(1, frogWorld.getMinJumpCount());
        Assert.assertEquals("(9,0),(9,3)", convertHistory(frogWorld.getMinJumpCell().getCellHistory()));
    }

    @Test
    public void testOneJump2() {
        FrogWorld frogWorld = new FrogWorld();
        frogWorld.init(new Cell(6, 1), new Cell(8, 2));
        frogWorld.startSearch();
        Assert.assertEquals(1, frogWorld.getMinJumpCount());
        Assert.assertEquals("(6,1),(8,2)", convertHistory(frogWorld.getMinJumpCell().getCellHistory()));


        /*check that application stops when find result (not all cells are used)*/
        int[][] expFields = new int[FrogWorld.MAX_ROWS][FrogWorld.MAX_COLUMNS];
        expFields[6][1] = FrogWorld.FROG_CELL;
        expFields[8][2] = FrogWorld.FINISH_CELL;
        expFields[9][0] = FrogWorld.USED_CELL;
        expFields[8][15] = FrogWorld.USED_CELL;
        expFields[7][0] = FrogWorld.USED_CELL;

        Assert.assertArrayEquals(expFields, frogWorld.getFields());
    }

    @Test
    public void testCase1() {
        FrogWorld frogWorld = new FrogWorld();
        frogWorld.init(new Cell(1, 2), new Cell(1, 8));
        frogWorld.startSearch();
        System.out.println(convertHistory(frogWorld.getMinJumpCell().getCellHistory()));
        Assert.assertEquals(2, frogWorld.getMinJumpCount());
        Assert.assertEquals("(1,2),(1,5),(1,8)", convertHistory(frogWorld.getMinJumpCell().getCellHistory()));
    }

    @Test
    public void testCase2() {
        FrogWorld frogWorld = new FrogWorld();
        frogWorld.init(new Cell(6, 10), new Cell(9, 8),
                new Cell(8, 13), new Cell(7, 4)
        );
        frogWorld.startSearch();
        Assert.assertEquals(7, frogWorld.getMinJumpCount());
        Assert.assertEquals("(6,10),(8,11),(9,13),(7,14),(9,15),(9,2),(9,5),(9,8)",
                convertHistory(frogWorld.getMinJumpCell().getCellHistory()));
    }

    @Test
    public void testCase3() {
        FrogWorld frogWorld = new FrogWorld();
        frogWorld.init(new Cell(6, 10), new Cell(9, 8),
                new Cell(8, 13), new Cell(7, 4), new Cell(5, 1),
                new Cell(9, 7), new Cell(9, 6), new Cell(9, 5)
        );
        frogWorld.startSearch();
        Assert.assertEquals(7, frogWorld.getMinJumpCount());
        Assert.assertEquals("(6,10),(8,11),(7,13),(9,14),(9,1),(9,4),(8,6),(9,8)",
                convertHistory(frogWorld.getMinJumpCell().getCellHistory()));
    }

    @Test
    public void testManyTreesNearFinish() {
        FrogWorld frogWorld = new FrogWorld();
        frogWorld.init(
                new Cell(6, 10), new Cell(6, 9),
                new Cell(4, 8), new Cell(4, 7), new Cell(4, 6),
                new Cell(5, 8), new Cell(5, 7), new Cell(5, 6),
                new Cell(6, 8), new Cell(6, 7), new Cell(6, 6),
                new Cell(7, 8), new Cell(7, 7), new Cell(7, 6),
                new Cell(8, 8), new Cell(8, 7), new Cell(8, 6)
        );
        frogWorld.startSearch();
        Assert.assertEquals(0, frogWorld.getMinJumpCount());
    }

    @Test
    public void testWhithoutFinish() {
        FrogWorld frogWorld = new FrogWorld();
        frogWorld.init(null, new Cell(0, 0));
        frogWorld.startSearch();
        Assert.assertEquals(0, frogWorld.getMinJumpCount());
    }

    private String convertHistory(List<Cell> jumpHistoryCells) {
        return jumpHistoryCells.stream()
                .map(Cell::toString)
                .collect(Collectors.joining(","));
    }
}
