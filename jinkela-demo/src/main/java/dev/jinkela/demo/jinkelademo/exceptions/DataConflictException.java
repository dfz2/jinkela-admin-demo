package dev.jinkela.demo.jinkelademo.exceptions;

import org.springframework.http.HttpStatus;

public class DataConflictException extends BaseJinkelaErrorResponseException {

  public DataConflictException(final String errorMessage) {
    super(HttpStatus.CONFLICT, errorMessage);
  }

}
