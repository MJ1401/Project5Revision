package robosim.core;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Histogram<T> implements Iterable<T> {
	private HashMap<T,Integer> counts = new HashMap<>();

	public Histogram() {}
	
	public Histogram(Histogram<T> other) {
		this.counts.putAll(other.counts);
		if (this.counts.size() != other.counts.size()) {
			throw new IllegalStateException("Huh? " + this.counts.size() + ": " + other.counts.size());
		}
	}

	public String toString() {
		ArrayList<String> entries = counts.entrySet().stream()
				.map(e -> "(" + e.getKey() + ":" + e.getValue() + ")")
				.collect(Collectors.toCollection(ArrayList::new));
		entries.sort(String::compareTo);
		return entries.stream().reduce("", String::concat);
	}

	public boolean equals(Object other) {
		return this.toString().equals(other.toString());
	}

	public int hashCode() {
		return this.toString().hashCode();
	}
	
	public void bump(T value) {
		bumpBy(value, 1);
	}
	
	public void bumpBy(T value, int numBumps) {
		counts.put(value, getCountFor(value) + numBumps);
	}
	
	public int getCountFor(T value) {
		return counts.getOrDefault(value, 0);
	}
	
	public int getTotalCounts() {
		int total = 0;
		for (Entry<T,Integer> entry: counts.entrySet()) {
			total += entry.getValue();
		}
		return total;
	}

	public double getPortionFor(T value) {
		return (double)getCountFor(value) / getTotalCounts();
	}
	
	@Override
	public Iterator<T> iterator() {
		return counts.keySet().iterator();
	}

	public Stream<T> stream() {
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator(), 0), false);
	}
	
	public int size() {return counts.size();}
}