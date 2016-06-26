package br.com.caelum.loja.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class JPAConfiguration {
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Properties aditionalProperties) {
	LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
	JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
	
	factoryBean.setPackagesToScan("br.com.caelum.loja.models");
	factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
	factoryBean.setDataSource(dataSource);
	factoryBean.setJpaProperties(aditionalProperties);

	return factoryBean;
    }

    @Bean
    @Profile("dev")
    public Properties aditionalProperties() {
	Properties props = new Properties();
	props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
	props.setProperty("hibernate.show_sql", "true");
	props.setProperty("hibernate.hbm2ddl.auto", "update");
	return props;
    }

    @Bean
    @Profile("dev") // Identifica que essa conexão é de um banco de desenvolvimento.
    public DataSource dataSource() {
	DriverManagerDataSource dataSource = new DriverManagerDataSource();
	dataSource.setUsername("root");
	dataSource.setPassword("root");
	dataSource.setUrl("jdbc:mysql://localhost:3306/casadocodigo");
	dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	return dataSource;
    }
    
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}