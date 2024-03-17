package dev.jinkela.demo.jinkelademo.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;

import lombok.experimental.UtilityClass;

@UtilityClass
class ProblemDetailUtil {

  public static ProblemDetail asProblemDetail(HttpStatusCode status, String message) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(status);
    withProblemDetail(problemDetail, status, message);
    return problemDetail;
  }

  public static ProblemDetail withProblemDetail(ProblemDetail problemDetail, HttpStatusCode status, String message) {
    problemDetail.setProperty("errorMessage", message);
    problemDetail.setProperty("errorCode", status.value());
    problemDetail.setProperty("timestamp", Instant.now());
    return problemDetail;
  }

}
