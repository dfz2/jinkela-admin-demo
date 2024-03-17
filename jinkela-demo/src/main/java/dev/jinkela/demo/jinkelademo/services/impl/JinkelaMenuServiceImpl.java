package dev.jinkela.demo.jinkelademo.services.impl;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.jinkela.demo.jinkelademo.datas.entities.JinkelaMenu;
import dev.jinkela.demo.jinkelademo.datas.repositories.JinkelaMenuRepository;
import dev.jinkela.demo.jinkelademo.services.JinkelaMenuService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class JinkelaMenuServiceImpl implements JinkelaMenuService {
  private final JinkelaMenuRepository jinkelaMenuRepository;

  @Override
  public List<JinkelaMenu> listAllMenusByJinkelaUserId(Long jinkelaUserId) {
    return buildMenuTree(() -> jinkelaMenuRepository.listAllMenusByJinkelaUserId(jinkelaUserId));
  }


  @Override
  public List<JinkelaMenu> listAllMenus() {
    return buildMenuTree(() -> jinkelaMenuRepository.findAll());
  }

  private List<JinkelaMenu> buildMenuTree(Supplier<List<JinkelaMenu>> supplier) {
    List<JinkelaMenu> menus = supplier.get();
    List<JinkelaMenu> parentMenus = menus.stream().filter(it -> it.getParentId() == 0L).toList();
    parentMenus.forEach(it -> it.setChildren(it.getChildren(it.getId(), menus)));
    return parentMenus;
  }


}
