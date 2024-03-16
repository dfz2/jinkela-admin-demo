package dev.jinkela.demo.jinkelademo.datas.entities;

import dev.jinkela.demo.jinkelademo.datas.AuditMetadata;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "_jinkela_user_role")
public class JinkelaUserRole extends AuditMetadata {

  @Id
  private Long id;
  private Long jinkelaUserId;
  private Long jinkelaRoleId;
}
