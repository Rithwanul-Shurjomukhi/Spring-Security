package com.user.security.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*
 *  This is a spring boot configuration class
 *  This class is responsible for creating
 *  database configuration
 *  for mysql database.
 *
 *  @author : T.M. Rithwanul Islam
 *  @purpose : learning
 *  @designation : Senior Software engineer
 */

@Configuration
@EnableTransactionManagement
public class DatabaseSource {

    /*
     * This method is injecting bean in the datasource
     * it also binds some of the properties here.
     * Here we are using mysql cj driver, use jdbc as a
     * connector.
     */

    @Bean
    DataSource datasource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/schooldb?allowPublicKeyRetrieval=true&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        return dataSource;
    }

    /*
     * This method is generating EntityManager Factory
     * setting adapter and properties in the bean.
     * adapter is user to set the mysql type database
     */
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(datasource());
        bean.setPackagesToScan("");
        bean.setJpaVendorAdapter(adapter);
        bean.setJpaProperties(additionalProperties());
        return bean;

    }

    /*
     * Adding extra properties in the Entity manager bean
     * Here we are sequentially setting the hbm2ddl, show_sql,
     * format_sql in the properties file.
     */
    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty("hibernate.globally_quoted_identifiers", "true");
        return properties;
    }
}