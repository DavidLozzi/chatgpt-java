package com.davidlozzi.chatgpt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SearchService {

  public static List<String> search(String query) {
    ObjectMapper mapper = new ObjectMapper();
    List<String> results = new ArrayList<>();
    try {
      JsonNode jsonData = mapper.readTree(new File("src/main/resources/data.json"));

      String[] keywords = query.split("\\s");
      List<Object> resultsObj = new ArrayList<>();

      for (JsonNode node : jsonData) {
        Integer matchCount = 0;
        String page = "TITLE: " + node.get("title").asText() + "\n" +
            "CONTENT: " + node.get("contents").asText() + "\n" +
            "PAGE: " + node.get("page").asText();

        for (String keyword : keywords) {
          if (keyword.length() < 3) {
            continue;
          }
          Pattern regex = Pattern.compile(".*\\b(" + keyword + ")\\b.*", Pattern.CASE_INSENSITIVE);
          Matcher matcher = regex.matcher(page);
          if (matcher.find()) {
            matchCount += matcher.groupCount();
          }
        }
        Integer count = matchCount;
        resultsObj.add(new SearchResult(count, page));
      }

      resultsObj.stream().sorted((o1, o2) -> {
        SearchResult r1 = (SearchResult) o1;
        SearchResult r2 = (SearchResult) o2;
        return r2.getCount().compareTo(r1.getCount());
      }).forEach(o -> {
        SearchResult r = (SearchResult) o;
        results.add(r.getPage());
      });
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return results;
  }

}
