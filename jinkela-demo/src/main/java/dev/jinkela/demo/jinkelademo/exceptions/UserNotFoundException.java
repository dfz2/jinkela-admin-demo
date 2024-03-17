package dev.jinkela.demo.jinkelademo.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseJinkelaErrorResponseException {

  public UserNotFoundException() {
    super(HttpStatus.NOT_FOUND, "用户不存在");
  }

}
