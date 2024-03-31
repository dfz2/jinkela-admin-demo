package dev.jinkela.demo.jinkelademo.services;
import java.util.List;

import dev.jinkela.demo.jinkelademo.datas.entities.JinkelaMenu;
import dev.jinkela.demo.jinkelademo.dtos.JinekelaMenuDTO;

public interface JinkelaMenuService {
  
  List<JinkelaMenu> listAllMenusByJinkelaUserId(Long jinkelaUserId);

  List<JinkelaMenu> listAllMenus();

  void modifyJinkelaMenus(JinekelaMenuDTO jinekelaMenuDTO);

  void deleteJinkelaMenu(JinekelaMenuDTO jinekelaMenuDTO);

  JinkelaMenu loadByJinkelaMenuId(Long jinkelaMenuId);

  

}
