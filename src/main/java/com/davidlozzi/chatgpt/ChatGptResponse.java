package com.davidlozzi.chatgpt;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatGptResponse {
  @JsonProperty("id")
  private String id;

  @JsonProperty("object")
  private String object;

  @JsonProperty("created")
  private Long created;

  @JsonProperty("model")
  private String model;

  @JsonProperty("choices")
  private Choice[] choices;

  @Data
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Choice {
    @JsonProperty("index")
    private Integer index;

    @JsonProperty("message")
    private Message message;

    @JsonProperty("finish_reason")
    private String finishReason;
  }

  @JsonProperty("usage")
  private Usage usage;

  @Data
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Usage {
    @JsonProperty("prompt_tokens")
    private Integer promptTokens;

    @JsonProperty("completion_tokens")
    private Integer completionTokens;

    @JsonProperty("total_tokens")
    private Integer totalTokens;
  }
}
