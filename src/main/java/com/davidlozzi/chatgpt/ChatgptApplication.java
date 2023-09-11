package com.davidlozzi.chatgpt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ChatgptApplication {

  public static void main(String[] args) {
    SpringApplication.run(ChatgptApplication.class, args);
  }

  @PostMapping("/chat")
  @ResponseBody
  public ResponseEntity chat() {
    return new ResponseEntity<>("Hello World!", HttpStatus.OK);
  }
}
