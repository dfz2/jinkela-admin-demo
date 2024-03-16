package dev.jinkela.demo.jinkelademo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
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
  public List<JinkelaMenu> listAllMenus() {
    return jinkelaMenuService.listAllMenus(); 
  }
}
