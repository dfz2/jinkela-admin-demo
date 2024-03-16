package dev.jinkela.demo.jinkelademo.datas.entities;

import java.util.ArrayList;
import java.util.List;

import dev.jinkela.demo.jinkelademo.datas.AuditMetadata;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "_jinkela_menus")
public class JinkelaMenu extends AuditMetadata {

  @Id
  private Long id;
  private Long parentId;
  private String title;
  private String name;
  private String type;
  private String icon;
  private Integer sort;
  private String permission;
  private String path;
  private String component;
  private String active;
  private String arguments;
  private Boolean keepAlive;
  private Boolean hidden;
  private Boolean enabled;

  private transient List<JinkelaMenu> children;

  public List<JinkelaMenu> getChildren(Long parentId, List<JinkelaMenu> menuItems) {

    List<JinkelaMenu> childMenus = new ArrayList<>();
    for (JinkelaMenu menuItem : menuItems) {
      if (parentId.equals(menuItem.getParentId())) {
        childMenus.add(menuItem);
      }
    }

    childMenus.forEach(it -> it.setChildren(getChildren(it.getId(), menuItems)));
    if (childMenus.isEmpty()) {
      childMenus = null;
    }

    return childMenus;
  }

}
