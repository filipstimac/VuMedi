package com.vuMedi.interview;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Anagrams {
  public static void main(String[] args) {
    try {
      Path path = Paths.get(Anagrams.class.getClassLoader().getResource("w150.txt").toURI());

      Stream<String> lines = Files.lines(path);
      List<String> list = lines.collect(Collectors.toList());
      lines.close();

      System.out.println(findAnagrams(list, args[0]));
    }
    catch (URISyntaxException e) {
      System.out.println("Wrong URI syntax for source file.");
    } catch (IOException e) {
      System.out.println("Not able to read source file.");
    }
  }

  //time complexity O(n)
  //space complexity 0(n)
  public static int findAnagrams(List<String> list, String word) {
    int count = 0;
    char[] word1 = word.toCharArray();
    Arrays.sort(word1);

    for (String possibleAnagram : list) {
      if (possibleAnagram.length() != word.length()) {
        continue;
      }
      char[] word2 = possibleAnagram.toCharArray();
      Arrays.sort(word1);
      if (Arrays.equals(word1, word2)) {
        count++;
      }
    }

    return count;
  }
}
