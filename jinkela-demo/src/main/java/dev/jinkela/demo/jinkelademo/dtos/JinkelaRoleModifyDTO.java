package dev.jinkela.demo.jinkelademo.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JinkelaRoleModifyDTO extends JinkelaRoleCreateDTO  {

  private Integer version;
}
