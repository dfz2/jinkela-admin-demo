package dev.jinkela.demo.jinkelademo.exceptions;

import org.springframework.http.HttpStatus;

public class DataConflictException extends BaseJinkelaErrorResponseException {

  public DataConflictException() {
    super(HttpStatus.CONFLICT, "提交数据存在冲突");
  }

}
