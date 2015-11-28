package com.traffic;

import java.util.List;

import org.junit.Test;

public class TestTraffic {
	@Test
	public void test() {
		Signal e = new Signal("E");
		Signal x = new Signal("X");
		Signal a = new Signal("A");
		Signal b = new Signal("B");
		Signal c = new Signal("C");
		Signal d = new Signal("D");
		e.setRoutes(new Route[] { new Route(x, 0.5, DIRECTION.WEST), new Route(b, 5) });
		x.setRoutes(new Route[] { new Route(e, 0.5), new Route(a, 1), new Route(b, 1), new Route(c, 0.5) });
		a.setRoutes(new Route[] { new Route(x, 1, DIRECTION.NORTH), new Route(d, 6) });
		b.setRoutes(new Route[] { new Route(e, 1), new Route(x, 1, DIRECTION.SOUTH), new Route(c, 2), new Route(d, 2) });
		c.setRoutes(new Route[] { new Route(x, 0.5, DIRECTION.EAST), new Route(b, 2), new Route(d, 2) });
		d.setRoutes(new Route[] { new Route(a, 6), new Route(b, 2), new Route(c, 2) });
		x.setTraffic(2, 20, 3, 0);
		TrafficSignalProblem t = new TrafficSignalProblem();
		List<Signal> shotestRoot = t.getShortestRoute(e, b);
		for (Signal s : shotestRoot) {
			System.out.println("Signal" + s.toString() + " dis" + s.getMinDistance());
		}
		x.setTraffic(2, 2, 3, 0);
		shotestRoot = t.getShortestRoute(e, b);
		for (Signal s : shotestRoot) {
			System.out.println("Signal" + s.toString() + " dis" + s.getMinDistance());
		}
	}
}