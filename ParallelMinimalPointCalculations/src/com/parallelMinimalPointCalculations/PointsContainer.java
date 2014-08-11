package com.parallelMinimalPointCalculations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class PointsContainer {

	public static List<Point> generatePoints(int aPointsCount, int aEndXy) {

		List<Point> points = new ArrayList<>();
		//List<Point> points = new LinkedList<>();
		Random generator = new Random();
		
		for (int i = 0; i < aPointsCount; ++i) {
			points.add(new Point(generator.nextInt(aEndXy), generator
					.nextInt(aEndXy)));
		}

		return points;
	}

	public static Map<Point, Point> getNearestPoints(final List<Point> points)
			throws InterruptedException {
		final Map<Point, Point> threadSafeMap = Collections
				.synchronizedMap(new HashMap<Point, Point>());

		List<Thread> threads = new LinkedList<>();
		final int partSize = points.size() / numberOfThreads;

		for (int i = 0; i < numberOfThreads; i++) {
			final int begin = partSize * i;
			final int end = partSize + begin;

			threads.add(new Thread(new Runnable() {

				@Override
				public void run() {
					doCalculations(points, begin, end, threadSafeMap);
				}
			}));

			threads.get(i).start();
		}

		for (int i = 0; i < numberOfThreads; i++) {
			threads.get(i).join();
		}

		return threadSafeMap;
	}

	public static Double getDistance(Point first, Point second) {
		int firstCatLen = Math.abs(first.x - second.x);
		int secondCatLen = Math.abs(first.y - second.y);
	
		return Math.sqrt((double)(firstCatLen * firstCatLen + secondCatLen * secondCatLen));
	}
	
	public static void doCalculations(List<Point> inPoints, int indexFrom,
			int indexTo, Map<Point, Point> outMap) {
		for (int i = indexFrom; i < indexTo; ++i) {
			Pair closest = null;
			Pair pair = new Pair(new Point(0, 0), new Point(0, 0));
			
			for (int j = 0; j < inPoints.size(); ++j) 
			{			
				pair.setPair(inPoints.get(i), inPoints.get(j));
				
				if (closest == null) {
					closest = pair;
					
				} else {
					if (pair.getDistance() < closest.getDistance())
						closest = pair;
				}
			}
			outMap.put(closest.getFirst(), closest.getSecond());
		}
	}

	public static void setThreads(int i) {
		numberOfThreads = i;
	}

	private static int numberOfThreads = 1;

}
