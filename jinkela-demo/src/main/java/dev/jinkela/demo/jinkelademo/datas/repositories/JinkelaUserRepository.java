package dev.jinkela.demo.jinkelademo.datas.repositories;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.stereotype.Repository;

import dev.jinkela.demo.jinkelademo.datas.BaseJinkelaRepository;
import dev.jinkela.demo.jinkelademo.datas.entities.JinkelaUser;

@Repository
public interface JinkelaUserRepository extends BaseJinkelaRepository<JinkelaUser, Long> {

  Optional<JinkelaUser> findJinkelaUserByUsername(String usernmae);

  @Query(value = """
      SELECT _jinkela_menus.permission FROM _jinkela_menus
      LEFT JOIN _jinkela_role_menu ON _jinkela_menus.id = _jinkela_role_menu.jinkela_menu_id
      LEFT JOIN _jinkela_user_role ON _jinkela_role_menu.jinkela_role_id = _jinkela_user_role.jinkela_role_id
      WHERE _jinkela_user_role.jinkela_user_id = :jinkelaUserId 
      AND _jinkela_menus.type IN ('C', 'A')
      """)
  java.util.Set<String> listAllPermissionsByJinkelaUserId(Long jinkelaUserId);

}
