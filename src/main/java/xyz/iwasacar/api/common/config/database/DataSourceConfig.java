package xyz.iwasacar.api.common.config.database;

import static xyz.iwasacar.api.common.config.database.DbConstUtil.*;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.zaxxer.hikari.HikariDataSource;

@Profile(PROFILE_PROD)
@Configuration
public class DataSourceConfig {

	@Primary
	@Bean(MASTER_DATE_SOURCE)
	@ConfigurationProperties(prefix = MASTER_PREFIX)
	public DataSource masterDataSource() {
		return DataSourceBuilder
			.create()
			.type(HikariDataSource.class)
			.build();
	}

	@Bean(REPLICA_DATE_SOURCE)
	@ConfigurationProperties(prefix = REPLICA_PREFIX)
	public DataSource replicaDataSource() {
		return DataSourceBuilder
			.create()
			.type(HikariDataSource.class)
			.build();
	}

}
