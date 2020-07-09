package com.vuMedi.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GettingCMEs {
  public static List<Webinar> result = new ArrayList<>();
  public static int resultScore;

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

    webinars.sort(Webinar::compareTo);
    findBestCombination(new ArrayList<>(), webinars, maxHours, 0);

    System.out.println(GettingCMEs.resultScore);
    GettingCMEs.result.forEach(el -> System.out.println(el.getDuration() + " " + el.getCMEs()));
  }

  public static void findBestCombination(List<Webinar> tempResult, List<Webinar> availableWebinars, int maxHours, int index) {
    if (maxHours < 0) {
      return;
    }

    int tempScore = tempResult.stream().mapToInt(Webinar::getCMEs).sum();
    if (tempScore > GettingCMEs.resultScore) {
      GettingCMEs.resultScore = tempScore;
      GettingCMEs.result = new ArrayList<>(tempResult);
    }

    for (int i = index; i < availableWebinars.size(); i++) {
      if (availableWebinars.get(i).getDuration() > maxHours) {
        break;
      }

      tempResult.add(availableWebinars.get(i));
      findBestCombination(tempResult, availableWebinars, maxHours - availableWebinars.get(i).getDuration(), i + 1);
      tempResult.remove(tempResult.size() - 1);
    }
  }

  public static class Webinar implements Comparable<Webinar> {
    private int duration;
    private int CMEs;

    public Webinar(int duration) {
      this.duration = duration;
    }

    public Webinar(int duration, int CMEs) {
      this.duration = duration;
      this.CMEs = CMEs;
    }

    public int getDuration() {
      return duration;
    }

    public void setDuration(int duration) {
      this.duration = duration;
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
