package dev.jinkela.demo.jinkelademo.dtos;

import lombok.Data;

@Data
public class JinekelaMenuDTO {
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
}
