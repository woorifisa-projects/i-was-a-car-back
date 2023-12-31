package xyz.iwasacar.api.common.config.database;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class RoutingDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		if (TransactionSynchronizationManager.isCurrentTransactionReadOnly()) {
			return DataSourceType.REPLICA;
		}
		return DataSourceType.MASTER;
	}

}
