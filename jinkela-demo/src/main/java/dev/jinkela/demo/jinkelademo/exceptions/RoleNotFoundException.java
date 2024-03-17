package dev.jinkela.demo.jinkelademo.exceptions;

import org.springframework.http.HttpStatus;

public class RoleNotFoundException extends BaseJinkelaErrorResponseException {

  public RoleNotFoundException() {
    super(HttpStatus.NOT_FOUND, "角色不存在");
  }

}
