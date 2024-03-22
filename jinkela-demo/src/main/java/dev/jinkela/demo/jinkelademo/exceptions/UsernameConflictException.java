package dev.jinkela.demo.jinkelademo.exceptions;

import org.springframework.http.HttpStatus;

public class UsernameConflictException extends BaseJinkelaErrorResponseException{

  public UsernameConflictException() {
    super(HttpStatus.CONFLICT, "账号已存在");
  }
  
}
