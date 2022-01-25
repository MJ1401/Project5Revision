package search.bestfirst;

import core.Pos;
import maze.core.MazeExplorer;
import search.SearchNode;
import search.SearchQueue;

import javax.swing.text.html.Option;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.function.ToIntFunction;

public class BestFirstQueue<T> implements SearchQueue<T> {
    // TODO: Implement this class
    private java.util.PriorityQueue<SearchNode<T>> queue = new PriorityQueue<>();
    private HashMap<T, SearchNode<T>> visited = new HashMap();
    // "store total estimate of the node of that value"
    // HINT: Use java.util.PriorityQueue

    public BestFirstQueue(ToIntFunction<T> heuristic) {
        // TODO: Your code here
        this.queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.getDepth() + heuristic.applyAsInt(n.getValue())));
    }

    @Override
    public void enqueue(SearchNode<T> node) {
        // TODO: Your code here
        if (!visited.containsKey(node.getValue())) {
            queue.add(node);
            visited.put(node.getValue(), node);
        }
    }

    @Override
    public Optional<SearchNode<T>> dequeue() {
        // TODO: Your code here
        if (queue.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(queue.remove());
        }
    }
}
