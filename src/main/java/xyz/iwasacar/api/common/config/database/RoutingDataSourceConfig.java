package xyz.iwasacar.api.common.config.database;

import static xyz.iwasacar.api.common.config.database.DbConstUtil.*;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Profile(PROFILE_PROD)
@EnableJpaRepositories(
	basePackages = BASE_PACKAGES,
	entityManagerFactoryRef = ENTITY_MANAGER_FACTORY,
	transactionManagerRef = TRANSACTION_MANAGER
)
@Configuration
public class RoutingDataSourceConfig {

	@Bean(ROUTING_DATA_SOURCE)
	public DataSource routingDataSource(
		@Qualifier(MASTER_DATE_SOURCE) final DataSource master,
		@Qualifier(REPLICA_DATE_SOURCE) final DataSource replica
	) {

		RoutingDataSource routingDataSource = new RoutingDataSource();

		Map<Object, Object> dataSourceMap = new HashMap<>();
		dataSourceMap.put(DataSourceType.MASTER, master);
		dataSourceMap.put(DataSourceType.REPLICA, replica);

		routingDataSource.setTargetDataSources(dataSourceMap);
		routingDataSource.setDefaultTargetDataSource(master);

		return routingDataSource;
	}

	@Bean(DATA_SOURCE)
	public DataSource dataSource(@Qualifier(ROUTING_DATA_SOURCE) DataSource routingDataSource) {
		return new LazyConnectionDataSourceProxy(routingDataSource);
	}

	@Bean(ENTITY_MANAGER_FACTORY)
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
		@Qualifier(DATA_SOURCE) DataSource dataSource) {

		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();

		entityManagerFactory.setDataSource(dataSource);
		entityManagerFactory.setPackagesToScan(BASE_PACKAGES);
		entityManagerFactory.setJpaVendorAdapter(this.jpaVendorAdapter());
		entityManagerFactory.setPersistenceUnitName(ENTITY_MANAGER);

		return entityManagerFactory;
	}

	private JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setGenerateDdl(false);
		adapter.setShowSql(false);

		adapter.setDatabasePlatform(HIBERNATE_DIALECT);

		return adapter;
	}

	@Bean(TRANSACTION_MANAGER)
	public PlatformTransactionManager platformTransactionManager(
		@Qualifier(ENTITY_MANAGER_FACTORY) LocalContainerEntityManagerFactoryBean emf
	) {

		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(emf.getObject());

		return jpaTransactionManager;
	}

}
