package dev.jinkela.demo.jinkelademo.exceptions;

import org.springframework.http.HttpStatus;

public class DbSaveException extends BaseJinkelaErrorResponseException{

  public DbSaveException() {
    super(HttpStatus.INTERNAL_SERVER_ERROR, "数据保存异常");
  }
  
}
