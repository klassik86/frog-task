package kk;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractFrogWorldTest {

    protected String convertHistory(List<Cell> jumpHistoryCells) {
        return jumpHistoryCells.stream()
                .map(Cell::toString)
                .collect(Collectors.joining(","));
    }

}
