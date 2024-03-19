package dev.jinkela.demo.jinkelademo.exceptions;

import org.springframework.http.HttpStatus;

public class UsernameConflictException extends BaseJinkelaErrorResponseException{

  public UsernameConflictException() {
    super(HttpStatus.CONFLICT, "登录账号已存在");
  }
  
}
