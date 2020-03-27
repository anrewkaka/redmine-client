package xyz.lannt.client.exception;

public class RedmineClientException extends RuntimeException {

  private static final long serialVersionUID = 5180693992102811811L;

  public RedmineClientException() {
    super();
  }

  public RedmineClientException(String message) {
    super(message);
  }

  public RedmineClientException(Throwable cause) {
    super(cause);
  }
}
