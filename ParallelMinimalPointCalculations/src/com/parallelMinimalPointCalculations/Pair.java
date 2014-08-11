package com.parallelMinimalPointCalculations;

public class Pair {
	public Point first;
	public Point second;
	
	public Pair(Point first, Point second) {
		this.first = first;
		this.second = second;
	}

	public Point getFirst() {
		return first;
	}

	public void setFirst(Point first) {
		this.first = first;
	}

	public Point getSecond() {
		return second;
	}

	public void setSecond(Point second) {
		this.second = second;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this == obj;
	}

	public Double getDistance() {
		int firstCatLen = Math.abs(first.x - second.x);
		int secondCatLen = Math.abs(first.y - second.y);
	
		return Math.sqrt((double)(firstCatLen * firstCatLen + secondCatLen * secondCatLen));
	}

	@Override
	public String toString() {
		return "Pair [first=" + first + ", second=" + second + "] = " + getDistance();
	}
	
	public void setPair(Point first, Point second)
	{
		this.first = first;
		this.second = second;
	}
	
}
