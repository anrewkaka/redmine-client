package xyz.lannt.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RedmineClientSetting {

  private String baseUrl;

  private String apiKey;
}
