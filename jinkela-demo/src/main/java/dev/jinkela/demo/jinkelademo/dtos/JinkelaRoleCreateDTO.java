package dev.jinkela.demo.jinkelademo.dtos;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JinkelaRoleCreateDTO {

  @NotBlank(message = "角色名称不能为空")
  @Length(max = 50, message = "角色名称最大长度为[50]位")
  private String name;

  @Length(max = 200, message = "备注最大长度为[200]位")
  private String remark;

}
