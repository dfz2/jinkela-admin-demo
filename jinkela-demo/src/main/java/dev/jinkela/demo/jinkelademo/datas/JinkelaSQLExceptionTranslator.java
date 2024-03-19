package dev.jinkela.demo.jinkelademo.datas;

import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.lang.Nullable;

public class JinkelaSQLExceptionTranslator implements SQLExceptionTranslator{

  @Override
  @Nullable
  public DataAccessException translate(String task, @Nullable String sql, SQLException ex) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'translate'");
  }
  
}
