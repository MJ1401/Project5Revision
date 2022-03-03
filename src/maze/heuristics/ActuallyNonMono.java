package maze.heuristics;

import core.Pos;
import maze.core.MazeExplorer;

import java.util.ArrayList;
import java.util.function.ToIntFunction;

public class ActuallyNonMono implements ToIntFunction<MazeExplorer> {
    @Override
    public int applyAsInt(MazeExplorer value) {
        int toTreasure;
        int toGoal;
        ArrayList<Integer> hold = new ArrayList<>();
        for (Pos t:value.getAllTreasureFromMaze()) {
            hold.add(t.getManhattanDist(value.getLocation()) + t.getManhattanDist(value.getGoal().getLocation()));
        }
        if (!hold.isEmpty()) {
            int small = 99999999;
            for (Integer i:hold) {
                if (i < small) {
                    small = i;
                }
            }
            toGoal = 0;
            toTreasure = small;
        }
        else {
            toGoal = value.getLocation().getManhattanDist(value.getGoal().getLocation());
            toTreasure = 0;
        }
        return toGoal + toTreasure;
    }
}
