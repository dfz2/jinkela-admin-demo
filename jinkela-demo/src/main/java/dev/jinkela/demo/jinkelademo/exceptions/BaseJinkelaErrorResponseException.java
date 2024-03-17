package dev.jinkela.demo.jinkelademo.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

class BaseJinkelaErrorResponseException extends ErrorResponseException {

  public BaseJinkelaErrorResponseException(HttpStatusCode status, final String errorMessage) {
    super(status, asProblemDetail(status, errorMessage), null);
  }

  private static ProblemDetail asProblemDetail(HttpStatusCode status, String message) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(status);
    problemDetail.setProperty("errorMessage", message);
    problemDetail.setProperty("errorCode", status.value());
    problemDetail.setProperty("timestamp", Instant.now());
    return problemDetail;
  }

}
