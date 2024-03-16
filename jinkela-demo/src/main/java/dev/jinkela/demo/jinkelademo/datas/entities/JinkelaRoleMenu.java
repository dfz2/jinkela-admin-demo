package dev.jinkela.demo.jinkelademo.datas.entities;

import dev.jinkela.demo.jinkelademo.datas.AuditMetadata;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "_jinkela_role_menu")
public class JinkelaRoleMenu extends AuditMetadata{
  
  @Id
  private Long id;
  private Long jinkelaRoleId;
  private Long jinkelaMenuId;
}
