package xyz.lannt.client.request.issue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import xyz.lannt.client.request.QueryParameter;
import xyz.lannt.client.request.RequestBody;

@Data
@Builder
@AllArgsConstructor
public class Issue implements RequestBody, QueryParameter {

  public Integer projectId;
  public Integer trackerId;
  public Integer statusId;
  public Integer priorityId;
  public String subject;
  public String description;
  public Integer categoryId;
  // - ID of the Target Versions (previously called 'Fixed Version' and still
  // referred to as such in the API)
  public Integer fixedVersionId;
  // - ID of the user to assign the issue to (currently no mechanism to assign by
  // name)
  public Integer assignedToId;
  // - ID of the parent issue
  public Integer parentIssueId;
  // - See Custom fields
  public Integer customFields;
  // - Array of user ids to add as watchers (since 2.3.0)
  public Integer watcherUserIds;
  // - Use true or false to indicate whether the issue is public or not
  public Integer isPublic;
  // - Number of hours estimated for issue
  public Integer estimatedHours;
}
