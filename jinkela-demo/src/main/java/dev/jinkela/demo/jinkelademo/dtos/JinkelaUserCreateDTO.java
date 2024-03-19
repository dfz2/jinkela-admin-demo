package dev.jinkela.demo.jinkelademo.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class JinkelaUserCreateDTO {
  
  @NotBlank(message = "登录用户名不能为空")
  private String username;

  @NotBlank(message = "昵称不能为空")
  private String nickname;

}
