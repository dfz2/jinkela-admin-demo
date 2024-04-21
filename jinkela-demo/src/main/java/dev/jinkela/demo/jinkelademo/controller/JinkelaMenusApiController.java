package dev.jinkela.demo.jinkelademo.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.jinkela.demo.jinkelademo.datas.entities.JinkelaMenu;
import dev.jinkela.demo.jinkelademo.services.JinkelaMenuService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/jinkela/menus")
@RequiredArgsConstructor
public class JinkelaMenusApiController {
  
  private final JinkelaMenuService jinkelaMenuService;

  @GetMapping
  @PreAuthorize("hasAuthority('caidanliebiao')")
  public List<JinkelaMenu> listAllMenus() {
    return jinkelaMenuService.listAllMenus(); 
  }


  @GetMapping("/{jinkelaMenuId}")
  @PreAuthorize("hasAuthority('caidanxiangxi')")
  public JinkelaMenu loadMenuById(@PathVariable Long jinkelaMenuId){
    return jinkelaMenuService.loadByJinkelaMenuId(jinkelaMenuId);
  }

  @PutMapping("/{jinkelaMenuId}")
  @PreAuthorize("hasAuthority('gengxincaidan')")
  public void modifyJinkelaMenus(@RequestBody JinkelaMenu jinkelaMenu) {
    jinkelaMenuService.modifyJinkelaMenus(jinkelaMenu);
  }


  @DeleteMapping("/{jinkelaMenuId}")
  @PreAuthorize("hasAuthority('shanchucaidan')")
  public void deleteJinkelaMenu(@RequestBody Long jinkelaMenuId) {
    jinkelaMenuService.deleteJinkelaMenuById(jinkelaMenuId);
  }
}
