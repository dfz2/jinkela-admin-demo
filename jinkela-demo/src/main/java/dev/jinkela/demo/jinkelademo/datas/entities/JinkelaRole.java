package dev.jinkela.demo.jinkelademo.datas.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import dev.jinkela.demo.jinkelademo.datas.AuditMetadata;
import dev.jinkela.demo.jinkelademo.datas.converters.Enabled;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "_jinkela_roles")
public class JinkelaRole extends AuditMetadata {

  @Id
  private Long id;
  private String name;
  private String remark;

  private Boolean enabled;
  private Boolean deleted;

  @Version
  private Integer version;

  public JinkelaRole() {
  }

  public JinkelaRole(String name) {
    this.name = name;
    this.enabled = true;
    this.deleted = false;
  }

}
