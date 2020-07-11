package com.vuMedi.interview;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class MountainStream {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int numberOfClosest = Integer.parseInt(scanner.nextLine());
    Point[] pointsArray = new Point[numberOfClosest];
    double maxDistance = Double.MAX_VALUE;
    int i = 0;

    while(true) {
      String line = scanner.nextLine();
      switch (line.charAt(0)) {
        //time complexity O(n)
        //space complexity O(1)
        case '1':
          String[] splitLine = line.split(" ");
          Point newPoint = new Point(Integer.parseInt(splitLine[1]), Integer.parseInt(splitLine[2]));
          if (i < numberOfClosest) {
            pointsArray[i] = newPoint;
            i++;
          } else if (newPoint.getDistanceToOrigin() < maxDistance) {
            pointsArray[numberOfClosest - 1] = newPoint;
          }
          maxDistance = Arrays.stream(pointsArray).max(Point::compareTo).get().getDistanceToOrigin();
          Arrays.sort(pointsArray, 0, i);
          break;
        case '2':
          Arrays.stream(pointsArray).forEach(point -> System.out.println(point.getX() + " " + point.getY()));
          break;
        case '3':
          System.exit(0);
          break;
      }
    }
  }

  public static class Point implements Comparable<Point> {
    private int x;
    private int y;
    private double distanceToOrigin;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
      this.distanceToOrigin = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
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

    public double getDistanceToOrigin() {
      return distanceToOrigin;
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

    @Override
    public int compareTo(Point o) {
      if (o == null){
        return 1;
      }

      return Double.compare(this.distanceToOrigin, o.distanceToOrigin);
    }

    @Override
    public String toString() {
      return "Point{" + "x=" + x + ", y=" + y + ", distanceToOrigin=" + distanceToOrigin + '}';
    }
  }
}
