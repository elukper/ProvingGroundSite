package com.feeddit.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableTransactionManagement
//@PropertySource("classpath:databaseConnection.properties")
@ComponentScan("com.feeddit")
public class ComponentConfiguration {

	//	@Bean
	//	public DataSource dataSource(final Environment environment) {
	//		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
	//		dataSource.setDriverClassName(environment.getProperty("mysql.driverClassName"));
	//		dataSource.setUrl(environment.getProperty("mysql.url"));
	//		dataSource.setUsername(environment.getProperty("mysql.username"));
	//		dataSource.setPassword(environment.getProperty("mysql.password"));
	//		return dataSource;
	//	}
	//
	//	@Bean
	//	public LocalContainerEntityManagerFactoryBean entityManagerFactory(final DataSource dataSource, final JpaVendorAdapter jpaVendorAdapter) {
	//		final LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
	//		emfb.setDataSource(dataSource);
	//		emfb.setJpaVendorAdapter(jpaVendorAdapter);
	//		emfb.setPackagesToScan("hr.altima.model.dao");
	//		return emfb;
	//	}

	//	private Properties hibernateProperties() {
	//		final Properties properties = new Properties();
	//		properties.put("hibernate.dialect", environment.getRequiredProperty("mysql.dialect"));
	//		properties.put("hibernate.show_sql", environment.getRequiredProperty("mysql.show_sql"));
	//		properties.put("hibernate.format_sql", environment.getRequiredProperty("mysql.format_sql"));
	//		return properties;
	//	}

	//	@Bean
	//	public JpaVendorAdapter jpaVendorAdapter(final Environment environment) {
	//		final HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
	//		adapter.setDatabase(Database.MYSQL);
	//		adapter.setShowSql(true);
	//		adapter.setGenerateDdl(false);
	//		adapter.setDatabasePlatform(environment.getProperty("mysql.dialect"));
	//		return adapter;
	//	}
	//
	//	@Bean
	//	public PersistenceAnnotationBeanPostProcessor paPostProcessor() {
	//		return new PersistenceAnnotationBeanPostProcessor();
	//	}
	//
	//	@Bean
	//	public BeanPostProcessor persistenceTranslation() {
	//		return new PersistenceExceptionTranslationPostProcessor();
	//	}

	//	@Bean
	//	public LocalSessionFactoryBean sessionFactory() {
	//		final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	//		sessionFactory.setDataSource(dataSource());
	//		sessionFactory.setPackagesToScan(new String[] { "hr.altima.model" });
	//		//		sessionFactory.setAnnotatedClasses(AbstractDbEntry.class,DbEntry.class);
	//		sessionFactory.setHibernateProperties(hibernateProperties());
	//		return sessionFactory;
	//	}



	//	@Bean
	//	@Autowired
	//	public HibernateTransactionManager transactionManager(final SessionFactory s) {
	//		final HibernateTransactionManager txManager = new HibernateTransactionManager();
	//		txManager.setSessionFactory(s);
	//		return txManager;
	//	}

}
