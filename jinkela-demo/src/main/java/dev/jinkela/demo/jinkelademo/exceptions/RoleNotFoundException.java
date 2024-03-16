package dev.jinkela.demo.jinkelademo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "角色不存在")
public class RoleNotFoundException extends RuntimeException{
  
}
