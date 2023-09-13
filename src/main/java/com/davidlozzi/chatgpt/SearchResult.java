package com.davidlozzi.chatgpt;

import lombok.Data;

@Data
public class SearchResult {
  private final Integer count;
  private final String page;
}
