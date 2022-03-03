package learning.sentiment.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BagOfWordsFuncs {
    public static <K, N extends Number> double cosineDistance(Map<K,N> a, Map<K, N> b) {
        return 1.0 - cosineSimilarity(a, b);
    }

    public static <K, N extends Number> double cosineSimilarity(Map<K,N> a, Map<K, N> b) {
        return dotProduct(a, b) / (magnitude(a) * magnitude(b));
    }

    public static <K, N extends Number> double magnitude(Map<K,N> a) {
        return Math.sqrt(dotProduct(a, a));
    }

    public static <K, N extends Number> double dotProduct(Map<K, N> a, Map<K, N> b) {
        double result = 0.0;
        for (K key: keysFromBoth(a, b)) {
            result += a.get(key).doubleValue() * b.get(key).doubleValue();
        }
        return result;
    }

    public static <K, N extends Number> HashMap<K,Double> weightedAverage(Map<K,N> a, Map<K, N> b, double aWeight) {
        // TODO: Find the weighted average of Maps a and b. Multiply the value for each key in a with aWeight,
        //  then multiply the corresponding value from b by (1 - aWeight), and add them.
        //  Since N extends Number, you can use the Number.doubleValue() method to get a concrete value.
        Set<K> keysA = a.keySet();
        Set<K> keysB = b.keySet();
        HashMap<K, Double> rMap = new HashMap<>();
        for (K k : keysA) {
            double holdB = 0;
            double holdA = a.get(k).doubleValue() * aWeight;
            if (keysB.contains(k)) {
                holdB = b.get(k).doubleValue() * (1-aWeight);
            }
            rMap.put(k, holdA + holdB);
        }
//        for (K k : keysB) {
//            if (!keysA.contains(k)) {
//                rMap.put(k, 0.0);
//            }
//        }
        return rMap;
    }

    public static <K, N> HashSet<K> allKeysFrom(Map<K,N> a, Map<K, N> b) {
        HashSet<K> all = new HashSet<>(a.keySet());
        all.addAll(b.keySet());
        return all;
    }

    public static <K, N> HashSet<K> keysFromBoth(Map<K, N> a, Map<K, N> b) {
        HashSet<K> both = new HashSet<>(a.keySet());
        both.retainAll(b.keySet());
        return both;
    }
}
