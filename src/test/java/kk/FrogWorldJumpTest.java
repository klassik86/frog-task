package kk;

import org.junit.Assert;
import org.junit.Test;

public class FrogWorldJumpTest {

    @Test
    public void testJumpType0() {
        FrogWorld frogWorld = new FrogWorld();
        Assert.assertEquals(
                new Cell(2, 0),
                frogWorld.reverseJump(0, new Cell(0, 1))
        );
    }

    @Test
    public void testJumpType1() {
        FrogWorld frogWorld = new FrogWorld();
        Assert.assertEquals(
                new Cell(2, 0),
                frogWorld.reverseJump(1, new Cell(1, 2))
        );
    }

    @Test
    public void testJumpType2() {
        FrogWorld frogWorld = new FrogWorld();
        Assert.assertEquals(
                new Cell(2, 0),
                frogWorld.reverseJump(2, new Cell(2, 3))
        );
    }

    @Test
    public void testJumpType3() {
        FrogWorld frogWorld = new FrogWorld();
        Assert.assertEquals(
                new Cell(2, 0),
                frogWorld.reverseJump(3, new Cell(3, 2))
        );
    }

    @Test
    public void testJumpType4() {
        FrogWorld frogWorld = new FrogWorld();
        Assert.assertEquals(
                new Cell(2, 0),
                frogWorld.reverseJump(4, new Cell(4, 1))
        );
    }

    @Test
    public void testJumpOverTopBoundary() {
        FrogWorld frogWorld = new FrogWorld();
        Assert.assertNull(frogWorld.reverseJump(4, new Cell(1, 0)));
    }

    @Test
    public void testJumpOverLowBoundary() {
        FrogWorld frogWorld = new FrogWorld();
        Assert.assertNull(frogWorld.reverseJump(0, new Cell(8, 0)));
    }

    @Test
    public void testJumpOverLastColumn() {
        FrogWorld frogWorld = new FrogWorld();
        Assert.assertEquals(
                new Cell(1, 13),
                frogWorld.reverseJump(2, new Cell(1, 0))
        );
    }

    @Test
    public void testJumpOnTree() {
        FrogWorld frogWorld = new FrogWorld();
        frogWorld.init(new Cell(0, 0), new Cell(9, 15),
                new Cell(0, 3));
        Assert.assertNull(frogWorld.reverseJump(2, new Cell(0, 6)));
    }

}
