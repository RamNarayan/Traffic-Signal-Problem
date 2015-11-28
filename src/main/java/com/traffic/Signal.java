package com.traffic;

class Signal implements Comparable<Signal> {
	private final String name;
	private Route[] routes;

	private double minDistance = Double.POSITIVE_INFINITY;
	private Signal previous;
	private int eTraffic;
	private int wTraffic;
	private int nTraffic;
	private int sTraffic;

	public Signal(String name) {
		this.name = name;
	}

	public void setTraffic(int eTraffic, int wTraffic, int nTraffic, int sTraffic) {
		this.eTraffic = eTraffic;
		this.wTraffic = wTraffic;
		this.nTraffic = nTraffic;
		this.sTraffic = sTraffic;
	}

	public int getWaitingTime(DIRECTION direction) {
		int waitingTime = 0;
		if (direction == DIRECTION.NORTH) {
			waitingTime = calculateWaitingTime(nTraffic) + calculateWaitingTime(eTraffic) + calculateWaitingTime(wTraffic);
		} else if (direction == DIRECTION.SOUTH) {
			waitingTime = calculateWaitingTime(sTraffic) + calculateWaitingTime(eTraffic) + calculateWaitingTime(wTraffic);
		} else if (direction == DIRECTION.WEST) {
			waitingTime = calculateWaitingTime(wTraffic) + calculateWaitingTime(sTraffic) + calculateWaitingTime(nTraffic);
		} else if (direction == DIRECTION.EAST) {
			waitingTime = calculateWaitingTime(eTraffic) + calculateWaitingTime(sTraffic) + calculateWaitingTime(nTraffic);
		}
		return waitingTime;
	}

	private int calculateWaitingTime(int traffic) {
		int noOfTimesSignalsChanged = 0;
		int weight = 0;
		if (traffic != 0) {
			weight = 30;
			while (traffic > 0) {
				if (traffic <= 3) {
					weight = weight + traffic * 10;
					traffic = 0;
				} else {
					traffic = traffic - 3;
					weight = weight + 3 * 10;
					noOfTimesSignalsChanged++;
				}
			}
		}
		weight = weight + noOfTimesSignalsChanged * 30;
		return weight;
	}

	public int compareTo(Signal other) {
		return Double.compare(minDistance, other.minDistance);
	}

	public String toString() {
		return name;
	}

	public Route[] getRoutes() {
		return routes;
	}

	public void setRoutes(Route[] routes) {
		this.routes = routes;
	}

	public Signal getPrevious() {
		return previous;
	}

	public void setPrevious(Signal previous) {
		this.previous = previous;
	}

	public double getMinDistance() {
		return minDistance;
	}

	public void setMinDistance(double minDistance) {
		this.minDistance = minDistance;
	}

	public String getName() {
		return name;
	}
}