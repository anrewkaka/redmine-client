package xyz.lannt.redmine.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.AllArgsConstructor;
import xyz.lannt.client.BittrexMarketClient;
import xyz.lannt.client.request.issue.Issue;

@AllArgsConstructor
public class Issues {
  private List<Issue> values;

  public Issues() {
    this.values = new ArrayList<Issue>();
  }

  public List<Issue> getAll() {
    return Collections.unmodifiableList(values);
  }

  public void add(Issue issue) {
    this.values.add(issue);
  }

  public void save(BittrexMarketClient client) {
    this.values.stream().forEach(e -> {
      client.post("issue.json", e);
    });
  }
}
