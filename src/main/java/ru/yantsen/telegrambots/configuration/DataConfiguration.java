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
public class DataConfiguration {

    @Bean
    public DriverManagerDataSource botDataSourse () {
        DriverManagerDataSource dataSourse = new DriverManagerDataSource();
        dataSourse.setDriverClassName("org.h2.Driver");
        dataSourse.setUrl("jdbc:h2:./data/botdb;AUTO_SERVER=TRUE");
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
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", "update");
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
