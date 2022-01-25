package maze.heuristics;

import core.Pos;
import maze.core.MazeExplorer;

import java.util.ArrayList;
import java.util.function.ToIntFunction;

public class NotMonoTwo implements ToIntFunction<MazeExplorer> {
    @Override
    public int applyAsInt(MazeExplorer value) {
        ArrayList<Integer> holdX = new ArrayList<>();
        ArrayList<Integer> holdY = new ArrayList<>();
        if (!(value.getAllTreasureFromMaze().size() == value.getNumTreasuresFound())) {
            for (Pos t:value.getAllTreasureFromMaze()) {
                holdX.add(t.getX());
                holdY.add(t.getY());
            }
        } else {
            int x = value.getLocation().getX();
            int y = value.getLocation().getY();
            if (y < x) {
                return y;
            }
            return x;
        }
        int bigX = 0;
        for (Integer i:holdX) {
            if (i > bigX) {
                bigX = i;
            }
        }
        int bigY = 0;
        for (Integer i:holdY) {
            if (i > bigY) {
                bigY = i;
            }
        }
        if (bigX < bigY) {
            return bigY;
        }
        return bigX;
    }
}
