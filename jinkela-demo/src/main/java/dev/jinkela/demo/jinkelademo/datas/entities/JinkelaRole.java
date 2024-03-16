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
@Table(name = "_jinkela_roles")
public class JinkelaRole extends AuditMetadata{
  
  @Id
  private Long id;
  private String name;
  private Boolean enabled;


  
  public JinkelaRole() {
  }



  public JinkelaRole(String name) {
    this.name = name;
    this.enabled = true;
  }


}
