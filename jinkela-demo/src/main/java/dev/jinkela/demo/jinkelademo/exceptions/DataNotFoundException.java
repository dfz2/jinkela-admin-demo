package dev.jinkela.demo.jinkelademo.exceptions;

import org.springframework.http.HttpStatus;

public class DataNotFoundException extends BaseJinkelaErrorResponseException {

  public DataNotFoundException(String errorMessage) {
    super(HttpStatus.NOT_FOUND, errorMessage);
  }
  
}
