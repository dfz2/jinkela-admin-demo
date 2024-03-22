package dev.jinkela.demo.jinkelademo.datas.repositories;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.jinkela.demo.jinkelademo.datas.entities.JinkelaRole;

@SpringBootTest
public class JinkelaRoleRepositoryTest {

  @Autowired
  private JinkelaRoleRepository jinkelaRoleRepository;

  @Test
  void test() {

    List<JinkelaRole> all = jinkelaRoleRepository.findAll();
    System.out.println(all);
  }
}
