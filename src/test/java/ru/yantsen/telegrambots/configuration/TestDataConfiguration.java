package ru.yantsen.telegrambots.configuration;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "ru.yantsen.telegrambots.repository")
public class TestDataConfiguration {

    @Bean
    public DriverManagerDataSource botDataSourse () {
        DriverManagerDataSource dataSourse = new DriverManagerDataSource();
        dataSourse.setDriverClassName("org.h2.Driver");
        dataSourse.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;create=true;DATABASE_TO_UPPER=false;INIT=CREATE SCHEMA IF NOT EXISTS BOT");
        dataSourse.setUsername("sa");
        dataSourse.setPassword("");
        return dataSourse;
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean emFactory = new LocalContainerEntityManagerFactoryBean();
        emFactory.setDataSource((botDataSourse()));
        emFactory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        emFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        jpaProperties.setProperty("hibernate.default_schema", "BOT");
        emFactory.setJpaProperties(jpaProperties);
        emFactory.setPackagesToScan("ru.yantsen.telegrambots.entity");
        return emFactory;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

}
