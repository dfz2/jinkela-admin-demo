package dev.jinkela.demo.jinkelademo.services;
import java.util.List;

import dev.jinkela.demo.jinkelademo.datas.entities.JinkelaMenu;

public interface JinkelaMenuService {
  
  List<JinkelaMenu> listAllMenusByJinkelaUserId(Long jinkelaUserId);

  List<JinkelaMenu> listAllMenus();

  

}
