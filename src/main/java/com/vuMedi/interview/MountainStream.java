package com.vuMedi.interview;

import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class MountainStream {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int numberOfClosest = Integer.parseInt(scanner.nextLine());
    SortedMap<Double, Set<Point>> distanceToPoint = new TreeMap<>();

    String line = " ";
    while(true) {
      line = scanner.nextLine();
      switch (line.charAt(0)) {
        case '1':
          String[] splitLine = line.split(" ");
          addPoint(Integer.parseInt(splitLine[1]), Integer.parseInt(splitLine[2]), distanceToPoint);
          if (distanceToPoint.size() > numberOfClosest) {
            distanceToPoint.remove(distanceToPoint.lastKey());
          }
          break;
        case '2':
          int i = 0;
          for  (Set<Point> pointList : distanceToPoint.values()) {
            for (Point point : pointList) {
              System.out.println(point.getX() + " " + point.getY());
              i++;
              if (i == numberOfClosest) break;
            }
          }
          break;
        case '3':
          System.exit(0);
          break;
      }
    }
  }

  public static void addPoint(int x, int y, SortedMap<Double, Set<Point>> distanceToPoint) {
    double distance = distanceToOrigin(x, y);
    addValues(distance, new Point(x, y), distanceToPoint);
  }

  public static void addValues(Double key, Point value, SortedMap<Double, Set<Point>> distanceToPoint) {
    Set<Point> tempList;
    if (distanceToPoint.containsKey(key)) {
      tempList = distanceToPoint.get(key);
      if(tempList == null)
        tempList = new HashSet<>();
    } else {
      tempList = new HashSet<>();
    }
    tempList.add(value);
    distanceToPoint.put(key,tempList);
  }

  public static double distanceToOrigin(int x, int y) {
    return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
  }

  public static class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public int getX() {
      return x;
    }

    public void setX(int x) {
      this.x = x;
    }

    public int getY() {
      return y;
    }

    public void setY(int y) {
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o)
        return true;
      if (!(o instanceof Point))
        return false;
      Point point = (Point) o;
      return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }
  }
}
