package dev.jinkela.demo.jinkelademo.datas.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import dev.jinkela.demo.jinkelademo.datas.AuditMetadata;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
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
  private String params;
  private Boolean keepAlive;
  private Boolean hidden;
  private Boolean enabled;


  @Transient
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
