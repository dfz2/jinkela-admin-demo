package dev.jinkela.demo.jinkelademo.services.impl;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.jinkela.demo.jinkelademo.datas.entities.JinkelaMenu;
import dev.jinkela.demo.jinkelademo.datas.repositories.JinkelaMenuRepository;
import dev.jinkela.demo.jinkelademo.dtos.JinekelaMenuDTO;
import dev.jinkela.demo.jinkelademo.exceptions.DataNotFoundException;
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

  @Transactional
  @Override
  public void modifyJinkelaMenus(JinekelaMenuDTO jinekelaMenuDTO) {
    JinkelaMenu jinkelaMenuRecord = jinkelaMenuRepository.findById(jinekelaMenuDTO.getId()).orElseThrow(() -> new DataNotFoundException("菜单不存在"));
    BeanUtils.copyProperties(jinekelaMenuDTO, jinkelaMenuRecord);
    jinkelaMenuRepository.save(jinkelaMenuRecord);
  }


  @Transactional
  @Override
  public void deleteJinkelaMenu(JinekelaMenuDTO jinekelaMenuDTO) {
    JinkelaMenu jinkelaMenuRecord = jinkelaMenuRepository.findById(jinekelaMenuDTO.getId()).orElseThrow(() -> new DataNotFoundException("菜单不存在"));
    jinkelaMenuRecord.setEnabled(false);
    jinkelaMenuRepository.save(jinkelaMenuRecord);
  }

  @Override
  public JinkelaMenu loadByJinkelaMenuId(Long jinkelaMenuId) {
      return jinkelaMenuRepository.findById(jinkelaMenuId).orElseThrow(() -> new DataNotFoundException("菜单不存在"));
  }



}
