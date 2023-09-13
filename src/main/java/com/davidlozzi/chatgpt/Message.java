package com.davidlozzi.chatgpt;

import lombok.Data;

@Data
public class Message {
  private String role;
  private String content;
}
