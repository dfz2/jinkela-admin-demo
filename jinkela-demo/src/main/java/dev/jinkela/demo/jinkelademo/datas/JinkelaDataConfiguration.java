package dev.jinkela.demo.jinkelademo.datas;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.util.TypeScanner;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.TransactionManager;

import dev.jinkela.demo.jinkelademo.datas.entities.JinkelaUser;
import io.vavr.control.Try;

@Configuration
@EnableJdbcAuditing(auditorAwareRef = "auditorAwareRef")
@EnableJdbcRepositories(basePackageClasses = { BaseJinkelaRepository.class })
class JinkelaDataConfiguration extends AbstractJdbcConfiguration {

  @Bean
  AuditorAware<String> auditorAwareRef() {
    return new AuditorAware<String>() {

      @Override
      public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
            .map(SecurityContext::getAuthentication)
            .filter(Authentication::isAuthenticated)
            .map(Authentication::getPrincipal)
            .map(JinkelaUser.class::cast)
            .map(it -> String.valueOf(it.getId()));
      }

    };

  }

  @Bean
  PageableHandlerMethodArgumentResolverCustomizer pageableResolverCustomizer() {
    return it -> it.setOneIndexedParameters(true);
  }

  @Bean
  NamedParameterJdbcOperations namedParameterJdbcOperations(DataSource dataSource) {
    return new NamedParameterJdbcTemplate(dataSource);
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
    Set<Class<?>> classes = TypeScanner.typeScanner(AbstractJdbcConfiguration.class.getClassLoader()) //
        .forTypesAnnotatedWith(WritingConverter.class, ReadingConverter.class) //
        .scanPackages("dev.jinkela.demo.jinkelademo.datas.converters") //
        .collectAsSet();
    return classes.stream().map(it -> Try.of(() -> it.getDeclaredConstructor().newInstance()).get()).toList();
  }
}
