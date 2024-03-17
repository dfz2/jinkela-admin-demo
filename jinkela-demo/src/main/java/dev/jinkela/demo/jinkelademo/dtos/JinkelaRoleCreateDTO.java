package dev.jinkela.demo.jinkelademo.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JinkelaRoleCreateDTO {

  @NotBlank(message = "角色名称不能为空")
  private String name;

}
