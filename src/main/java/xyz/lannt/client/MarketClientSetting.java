package xyz.lannt.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MarketClientSetting {

  private String baseUrl;

  private String apiKey;
}
