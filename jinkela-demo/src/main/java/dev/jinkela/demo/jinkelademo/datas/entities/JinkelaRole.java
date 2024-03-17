package dev.jinkela.demo.jinkelademo.datas.entities;

import dev.jinkela.demo.jinkelademo.datas.AuditMetadata;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "_jinkela_roles")
public class JinkelaRole extends AuditMetadata {

  @Id
  private Long id;
  private String name;
  private Boolean enabled;

  @Version
  private Integer version;

  public JinkelaRole() {
  }

  public JinkelaRole(String name) {
    this.name = name;
    this.enabled = true;
  }

}
