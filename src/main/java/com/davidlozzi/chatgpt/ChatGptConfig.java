package com.davidlozzi.chatgpt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ChatGptConfig {
  public static String endpoint;
  public static String apikey;

  @Autowired
  public ChatGptConfig(@Value("${endpoint}") String endpoint, @Value("${apikey}") String apikey) {
    ChatGptConfig.endpoint = endpoint;
    ChatGptConfig.apikey = apikey;
  }
}
