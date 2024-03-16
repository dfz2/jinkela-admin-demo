package dev.jinkela.demo.jinkelademo.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class JinkelaRoleCreateDTO {

  @NotBlank
  private String name;
  
}
