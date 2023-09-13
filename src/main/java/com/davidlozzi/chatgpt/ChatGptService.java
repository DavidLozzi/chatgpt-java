package com.davidlozzi.chatgpt;

import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;

public class ChatGptService {
  public ChatGptResponse callChat(List<Message> messages) {
    RestOperations restOperations = new RestTemplateBuilder()
        .rootUri(ChatGptConfig.endpoint)
        .defaultHeader("Authorization", "Bearer " + ChatGptConfig.apikey)
        .build();

    List<String> searchResults = SearchService.search(messages.get(messages.size() - 1).getContent());
    searchResults = searchResults.subList(0, Math.min(5, searchResults.size()));

    Message systemPrompt = new Message();
    systemPrompt.setRole("system");
    systemPrompt.setContent(
        "You are a search expert at Slalom Consulting, with access to all of its knowledge. Users are going to ask specific questions and you are to answer only using the content provided below after CONTENT. If you cannot answer the question with this content, respond with 'Sorry, I don't know', do not answer using your own knowledge. When responding, include footnotes to the related CONTENT. The footnote should include the TITLE and the PAGE number. \n\nCONTENT\n"
            + String.join("\n\n", searchResults));
    messages.add(0, systemPrompt);

    ChatGptRequest request = new ChatGptRequest();
    request.setMessages(messages);
    request.setModel("gpt-3.5-turbo");

    ResponseEntity<ChatGptResponse> response = restOperations.postForEntity(ChatGptConfig.endpoint, request,
        ChatGptResponse.class);

    return response.getBody();
  }
}
