package com.traffic;

class Route {
	private final Signal targetSignal;
	private final double travelTime;
	private DIRECTION direction;

	/**
	 * @param targetSignal
	 * @param distance
	 * @param direction
	 */
	public Route(Signal targetSignal, double distance, DIRECTION direction) {
		this.targetSignal = targetSignal;
		this.travelTime = distance * 60;
		this.direction = direction;
	}

	/**
	 * @param targetSignal
	 * @param distance
	 */
	public Route(Signal targetSignal, double distance) {
		this.targetSignal = targetSignal;
		this.travelTime = distance * 60;
	}

	public Signal getTargetSignal() {
		return targetSignal;
	}

	public double getTotalTravelTime() {
		return travelTime + targetSignal.getWaitingTime(direction);
	}

	public DIRECTION getDirection() {
		return direction;
	}
}