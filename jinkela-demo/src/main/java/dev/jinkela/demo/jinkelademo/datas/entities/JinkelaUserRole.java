package dev.jinkela.demo.jinkelademo.datas.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import dev.jinkela.demo.jinkelademo.datas.AuditMetadata;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Table(name = "_jinkela_user_role")
public class JinkelaUserRole extends AuditMetadata {

  @Id
  private Long id;
  private Long jinkelaUserId;
  private Long jinkelaRoleId;
}
