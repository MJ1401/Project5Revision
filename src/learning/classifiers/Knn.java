package learning.classifiers;

import core.Duple;
import learning.core.Classifier;
import learning.core.Histogram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.function.ToDoubleBiFunction;

// KnnTest.test() should pass once this is finished.
public class Knn<V, L> implements Classifier<V, L> {
    private ArrayList<Duple<V, L>> data = new ArrayList<>();
    private ToDoubleBiFunction<V, V> distance;
    private int k;

    public Knn(int k, ToDoubleBiFunction<V, V> distance) {
        this.k = k;
        this.distance = distance;
    }

    @Override
    public L classify(V value) {
        // TODO: Find the distance from value to each element of data. Use Histogram.getPluralityWinner()
        //  to find the most popular label.
        ArrayList<Duple<Double, L>> dist = new ArrayList<>();
        Histogram<L> hist = new Histogram<>();
        for (Duple<V, L> d : data) {
            dist.add(new Duple<>(distance.applyAsDouble(value, d.getFirst()), d.getSecond()));
        }
        dist.sort(Comparator.comparingDouble(Duple::getFirst));
        for (int i = 0; i < k; i++) {
            hist.bump(dist.get(i).getSecond());
        }
        return hist.getPluralityWinner();
    }

    @Override
    public void train(ArrayList<Duple<V, L>> training) {
        // TODO: Add all elements of training to data.
        for (Duple<V, L> t : training) {
            data.add(t);
        }
    }
}
