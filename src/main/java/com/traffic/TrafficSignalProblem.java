package com.traffic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class TrafficSignalProblem {
	private void computePaths(Signal source) {
		source.setMinDistance(0);
		PriorityQueue<Signal> vertexQueue = new PriorityQueue<Signal>();
		vertexQueue.add(source);
		while (!vertexQueue.isEmpty()) {
			Signal u = vertexQueue.poll();
			if (u.getRoutes() != null) {
				for (Route e : u.getRoutes()) {
					Signal v = e.getTargetSignal();
					double weight = e.getTotalTravelTime();
					double distanceThroughU = u.getMinDistance() + weight;
					if (distanceThroughU < v.getMinDistance()) {
						vertexQueue.remove(v);
						v.setMinDistance(distanceThroughU);
						v.setPrevious(u);
						vertexQueue.add(v);
					}
				}
			}
		}
	}

	private List<Signal> getShortestRouteTo(Signal target) {
		List<Signal> path = new ArrayList<Signal>();
		for (Signal vertex = target; vertex != null; vertex = vertex.getPrevious())
			path.add(vertex);
		Collections.reverse(path);
		return path;
	}

	public List<Signal> getShortestRoute(Signal sourceSignal, Signal destinationSignal) {
		this.computePaths(sourceSignal);
		return this.getShortestRouteTo(destinationSignal);
	}
}