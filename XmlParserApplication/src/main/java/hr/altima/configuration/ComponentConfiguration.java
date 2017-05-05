package hr.altima.configuration;


import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
public class ComponentConfiguration {

	@Autowired
	private Environment environment;

	@Bean
	@Profile("dev")
	public DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("mysql.driverClassName"));
		dataSource.setUrl(environment.getRequiredProperty("mysql.url"));
		dataSource.setUsername(environment.getRequiredProperty("mysql.username"));
		dataSource.setPassword(environment.getRequiredProperty("mysql.password"));
		return dataSource;
	}

	@Bean
	@Profile("test")
	public DataSource h2DataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.addScript("classpath:createH2DB.sql")
				.build();
	}

	private Properties hibernateProperties() {
		final Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getRequiredProperty("mysql.dialect"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("mysql.show_sql"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("mysql.format_sql"));
		return properties;
	}

	@Bean
	@Profile("dev")
	public LocalSessionFactoryBean sessionFactory() {
		final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] { "hr.altima.model" });
		//		sessionFactory.setAnnotatedClasses(AbstractDbEntry.class,DbEntry.class);
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	@Bean
	@Profile("test")
	public LocalSessionFactoryBean h2SessionFactory() {
		final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(h2DataSource());
		sessionFactory.setPackagesToScan(new String[] { "hr.altima.model" });
		final Properties props = new Properties();
		props.setProperty("dialect", "org.hibernate.dialect.H2Dialect");
		sessionFactory.setHibernateProperties(props);
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
