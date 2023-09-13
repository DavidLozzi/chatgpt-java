package com.davidlozzi.chatgpt;

import java.util.List;

import lombok.Data;

@Data
public class ChatGptRequest {
  private String model;
  private List<Message> messages;
}
