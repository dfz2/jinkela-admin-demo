package dev.jinkela.demo.jinkelademo.datas;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.reflections.Reflections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.data.util.TypeScanner;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.TransactionManager;

import cn.hutool.core.util.ReflectUtil;
import dev.jinkela.demo.jinkelademo.datas.converters.EnabledToStringWritingConverter;
import dev.jinkela.demo.jinkelademo.datas.converters.StringToEnabledReadingConverter;
import dev.jinkela.demo.jinkelademo.datas.entities.JinkelaUser;
import io.vavr.control.Try;
import lombok.SneakyThrows;

@Configuration
@EnableJdbcAuditing(auditorAwareRef = "auditorAwareRef")
@EnableJdbcRepositories(basePackageClasses = { BaseJinkelaRepository.class })
class JinkelaDataConfiguration extends AbstractJdbcConfiguration {

  @Bean
  AuditorAware<String> auditorAwareRef() {
    return () -> Optional.ofNullable(SecurityContextHolder.getContext())
        .map(SecurityContext::getAuthentication)
        .filter(Authentication::isAuthenticated)
        .map(Authentication::getPrincipal)
        .map(JinkelaUser.class::cast)
        .map(it -> String.valueOf(it.getId()));
  }

  @Bean
  PageableHandlerMethodArgumentResolverCustomizer pageableResolverCustomizer() {
    return it -> it.setOneIndexedParameters(true);
  }

  @Bean
  NamedParameterJdbcOperations namedParameterJdbcOperations(DataSource dataSource) {
    var namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    // namedParameterJdbcTemplate.getJdbcTemplate().setExceptionTranslator(new
    // SQLErrorCodeSQLExceptionTranslator());
    return namedParameterJdbcTemplate;
  }

  @Bean
  TransactionManager transactionManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

  // @Bean
  // NamingStrategy namingStrategy() {
  // return new DefaultNamingStrategy() {
  // @Override
  // public String getColumnName(RelationalPersistentProperty property) {
  // return property.getName();
  // }
  // };
  // }

  @Override
  protected List<?> userConverters() {
    return new Reflections("dev.jinkela.demo.jinkelademo.datas.converters")
        .getSubTypesOf(Converter.class)
        .stream().map(it -> Try.of(() -> ReflectUtil.newInstance(it)).get()).collect(Collectors.toList());

  }
}
