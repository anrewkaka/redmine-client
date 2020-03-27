package xyz.lannt.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import xyz.lannt.client.exception.RedmineClientException;
import xyz.lannt.client.request.QueryParameter;
import xyz.lannt.client.request.RequestBody;
import xyz.lannt.client.request.issue.Issue;

public class RedmineClient {

  private RedmineClientSetting setting;

  public RedmineClient(RedmineClientSetting setting) {
    this.setting = setting;
  }

  @Autowired
  private Gson gson;

  private HttpHeaders createHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "application/json");
    headers.add("Authorization", this.setting.getApiKey());
    return headers;
  }

  private String createUrl(String uri, QueryParameter request) {
    String url = setting.getBaseUrl() + "/" + uri;

    try {
      if (!ObjectUtils.isEmpty(request)) {
        url += "?" + request.toQueryParam();
      }
    } catch (IllegalAccessException e) {
      throw new RedmineClientException(e);
    }

    return url;
  }

  public String get(String uri, QueryParameter params) {
    String url = this.createUrl(uri, params);
    return this.request(url, HttpMethod.GET, this.createHeaders(), null);
  }

  public void post(String uri, Issue issue) {
    this.request(this.createUrl(uri, null), HttpMethod.POST, this.createHeaders(), issue);
  }

  private String request(String url, HttpMethod method, HttpHeaders headers, RequestBody request) {

    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setRequestFactory(new SimpleClientHttpRequestFactory());
    HttpEntity<Object> requestEntity = null;
    if (!ObjectUtils.isEmpty(request)) {
      requestEntity = new HttpEntity<Object>(gson.toJson(request), headers);
    } else {
      requestEntity = new HttpEntity<Object>(headers);
    }
    ResponseEntity<String> response = restTemplate.exchange(url, method, requestEntity, String.class);
    if (response.getStatusCode() != HttpStatus.OK) {
      throw new RedmineClientException();
    }

    return response.getBody();
  }
}
