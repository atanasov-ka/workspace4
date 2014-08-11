package com.parallelMinimalPointCalculations;
import java.util.List;
import java.util.Map;


public class Main {
	public static void main(String[] args) {
		
		PointsContainer.setThreads(8);
		List<Point>  points = PointsContainer.generatePoints(100_000, 10_000);
		Map<Point, Point> nearestPoints = null;
		long start = System.currentTimeMillis();
		try {
			nearestPoints = PointsContainer.getNearestPoints(points);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(nearestPoints.size());
		System.out.println("Time: " + (System.currentTimeMillis() - start));
	}
}
