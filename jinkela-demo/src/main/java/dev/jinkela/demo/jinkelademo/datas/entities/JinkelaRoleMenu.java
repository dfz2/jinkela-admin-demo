package dev.jinkela.demo.jinkelademo.datas.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import dev.jinkela.demo.jinkelademo.datas.AuditMetadata;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Table(name = "_jinkela_role_menu")
public class JinkelaRoleMenu extends AuditMetadata{
  
  @Id
  private Long id;
  private Long jinkelaRoleId;
  private Long jinkelaMenuId;
}
