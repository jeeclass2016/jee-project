package com.vietsci.cms.api.bootstrap;

import java.util.Properties;

import javax.persistence.spi.PersistenceProvider;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Application configuration class
 * 
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.vietsci.cms.api.repository")
public class AppConfig {

  @Bean
  public DataSource dataSource() {

    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
    dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:STUDY");
    dataSource.setUsername("ivr_cms");
    dataSource.setPassword("password");

    return dataSource;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setShowSql(true);
    vendorAdapter.setGenerateDdl(true);

    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

    entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
    entityManagerFactoryBean.setDataSource(dataSource());
    entityManagerFactoryBean.setPackagesToScan(new String[] { "com.vietsci.cms.api.model" });
    entityManagerFactoryBean.setPersistenceProvider(persistenceProvider());
    entityManagerFactoryBean.setPersistenceUnitName("com.vietsci.cms.api.jpa");
    
    Properties jpaProperties = new Properties();
    jpaProperties.setProperty("jboss.as.jpa.managed", "false");
    jpaProperties.setProperty("hibernate.connection.charSet", "UTF-8");
    entityManagerFactoryBean.setJpaProperties(jpaProperties);
    entityManagerFactoryBean.afterPropertiesSet();
    return entityManagerFactoryBean;
  }
  
  @Bean
  public PersistenceProvider persistenceProvider() {
    return new HibernatePersistenceProvider();
  }

  @Bean
  public JpaTransactionManager transactionManager() {
    JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
    jpaTransactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
    return jpaTransactionManager;
  }

  @Bean
  public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
    return new PersistenceExceptionTranslationPostProcessor();
  }
  
  @Bean
  public MessageSource messageSource() {
    ResourceBundleMessageSource messageResource = new ResourceBundleMessageSource();
    messageResource.setBasename("message");
    return messageResource;
  }
}
