package dev.jinkela.demo.jinkelademo.datas.repositories;

import org.springframework.stereotype.Repository;

import dev.jinkela.demo.jinkelademo.datas.BaseJinkelaRepository;
import dev.jinkela.demo.jinkelademo.datas.entities.JinkelaUserRole;

@Repository
public interface JinkelaUserRoleRepository  extends BaseJinkelaRepository<JinkelaUserRole, Long>{
  
}
