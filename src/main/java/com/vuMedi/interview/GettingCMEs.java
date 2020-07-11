package com.vuMedi.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class GettingCMEs {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int maxHours = Integer.parseInt(scanner.nextLine());
    int webinarNumber = Integer.parseInt(scanner.nextLine());
    List<Webinar> webinars = new ArrayList<>();
    for (int i = 0; i < webinarNumber; i++) {
      webinars.add(new Webinar(scanner.nextInt()));
    }
    scanner.nextLine();
    for (int i = 0; i < webinarNumber; i++) {
      webinars.get(i).setCMEs(scanner.nextInt());
    }
    scanner.close();

    List<Webinar> resultList = new ArrayList<>();
    AtomicInteger resultScore = new AtomicInteger(0);

    webinars.sort(Webinar::compareTo);
    findBestCombination(new ArrayList<>(), resultList, webinars, maxHours, 0, resultScore);

    System.out.println(resultScore);
    resultList.forEach(el -> System.out.println(el.getDuration() + " " + el.getCMEs()));
  }

  //time complexity O(n!)
  // space complexity O(n)
  public static void findBestCombination(List<Webinar> tempResult, List<Webinar> resultList, List<Webinar> availableWebinars, int maxHours, int index, AtomicInteger resultScore) {
    if (maxHours < 0) {
      return;
    }

    int tempScore = tempResult.stream().mapToInt(Webinar::getCMEs).sum();
    if (tempScore > resultScore.intValue()) {
      resultScore.set(tempScore);
      resultList.clear();
      resultList.addAll(tempResult);
    }

    for (int i = index; i < availableWebinars.size(); i++) {
      if (availableWebinars.get(i).getDuration() > maxHours) {
        break;
      }

      tempResult.add(availableWebinars.get(i));
      findBestCombination(tempResult, resultList, availableWebinars, maxHours - availableWebinars.get(i).getDuration(), i + 1, resultScore);
      tempResult.remove(tempResult.size() - 1);
    }
  }

  public static class Webinar implements Comparable<Webinar> {
    private int duration;
    private int CMEs;

    public Webinar(int duration) {
      this.duration = duration;
    }

    public int getDuration() {
      return duration;
    }

    public int getCMEs() {
      return CMEs;
    }

    public void setCMEs(int CMEs) {
      this.CMEs = CMEs;
    }

    @Override
    public int compareTo(Webinar o) {
      return Integer.compare(this.getDuration(), o.getDuration());
    }

    @Override
    public String toString() {
      return "Webinar{" + "duration=" + duration + ", CMEs=" + CMEs + '}';
    }
  }
}
