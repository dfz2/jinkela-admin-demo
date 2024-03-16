package dev.jinkela.demo.jinkelademo.datas.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.jinkela.demo.jinkelademo.datas.BaseJinkelaRepository;
import dev.jinkela.demo.jinkelademo.datas.entities.JinkelaMenu;

@Repository
public interface JinkelaMenuRepository extends BaseJinkelaRepository<JinkelaMenu, Long> {

  @Query(nativeQuery = true, value = """
      SELECT _jinkela_menus.*
      FROM _jinkela_menus
      LEFT JOIN _jinkela_role_menu ON _jinkela_menus.id = _jinkela_role_menu.jinkela_menu_id
      LEFT JOIN _jinkela_user_role ON _jinkela_role_menu.jinkela_role_id = _jinkela_user_role.jinkela_role_id
      WHERE _jinkela_user_role.jinkela_user_id = :jinkelaUserId
      AND _jinkela_menus.type IN ('C', 'M')
      """)
  java.util.List<JinkelaMenu> listAllMenusByJinkelaUserId(Long jinkelaUserId);

}
