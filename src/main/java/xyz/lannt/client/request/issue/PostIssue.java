package xyz.lannt.client.request.issue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import xyz.lannt.client.request.RequestBody;

@Data
@Builder
@AllArgsConstructor
public class PostIssue implements RequestBody {
    public Issue issue;
}
