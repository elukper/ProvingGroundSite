package hr.altima.configuration;


import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("hr.altima")
@EnableTransactionManagement
@PropertySource("classpath:databaseConnection.properties")
public class ComponentConfiguration {

	@Autowired
	private Environment environment;

	@Bean
	public DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("mysql.driverClassName"));
		dataSource.setUrl(environment.getRequiredProperty("mysql.url"));
		dataSource.setUsername(environment.getRequiredProperty("mysql.username"));
		dataSource.setPassword(environment.getRequiredProperty("mysql.password"));
		return dataSource;
	}

	private Properties hibernateProperties() {
		final Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getRequiredProperty("mysql.dialect"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("mysql.show_sql"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("mysql.format_sql"));
		return properties;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] { "hr.altima.model" });
		//		sessionFactory.setAnnotatedClasses(AbstractDbEntry.class,DbEntry.class);
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(final SessionFactory s) {
		final HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(s);
		return txManager;
	}

}
