package maze.heuristics;

import core.Pos;
import maze.core.MazeExplorer;

import java.util.ArrayList;
import java.util.function.ToIntFunction;

public class MonotonicTwo implements ToIntFunction<MazeExplorer> {
    @Override
    public int applyAsInt(MazeExplorer value) {
        ArrayList<Integer> hold = new ArrayList<>();
        if (!(value.getAllTreasureFromMaze().size() == value.getNumTreasuresFound())) {
            for (Pos t:value.getAllTreasureFromMaze()) {
                hold.add(Math.abs(t.getX() - value.getLocation().getX()));
            }
        } else {
            return value.getLocation().getManhattanDist(value.getGoal().getLocation());
        }
        int small = 99999999;
        for (Integer i:hold) {
            if (i < small) {
                small = i;
            }
        }
        return small;
    }
}
