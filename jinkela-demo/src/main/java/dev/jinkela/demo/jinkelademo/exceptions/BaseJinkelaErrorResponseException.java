package dev.jinkela.demo.jinkelademo.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.ErrorResponseException;

class BaseJinkelaErrorResponseException extends ErrorResponseException {

  public BaseJinkelaErrorResponseException(HttpStatusCode status, final String errorMessage) {
    super(status, ProblemDetailUtil.asProblemDetail(status, errorMessage), null);
  }

}
