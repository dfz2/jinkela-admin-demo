package dev.jinkela.demo.jinkelademo.datas.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import dev.jinkela.demo.jinkelademo.datas.BaseJinkelaRepository;
import dev.jinkela.demo.jinkelademo.datas.entities.JinkelaUser;

@Repository
public interface JinkelaUserRepository extends BaseJinkelaRepository<JinkelaUser, Long> {
  
  Optional<JinkelaUser> findJinkelaUserByUsername(String usernmae);

}
