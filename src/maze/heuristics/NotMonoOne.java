package maze.heuristics;

import maze.core.MazeExplorer;

import java.util.function.ToIntFunction;

public class NotMonoOne implements ToIntFunction<MazeExplorer> {
    @Override
    public int applyAsInt(MazeExplorer value) {
        int x = value.getLocation().getX();
        int y = value.getLocation().getY();
        if (y < x) {
            return x;
        }
        return y;
    }
}
