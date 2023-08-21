package xyz.iwasacar.api.common.config;

import static org.hibernate.engine.jdbc.internal.FormatStyle.*;

import java.util.Locale;

import com.p6spy.engine.common.P6Util;
import com.p6spy.engine.logging.Category;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

public class P6spySqlFormatConfiguration implements MessageFormattingStrategy {

	@Override
	public String formatMessage(int connectionId, String now, long elapsed,
		String category, String prepared, String sql, String url) {

		sql = this.formatSql(category, sql);

		return now + " | " + elapsed + "ms | " + category + " | connection "
			+ connectionId + " | " + P6Util.singleLine(prepared) + sql;
	}

	private String formatSql(String category, String sql) {
		if (sql == null || sql.trim().equals("")) {
			return sql;
		}

		// Only format Statement, distinguish DDL And DML
		if (Category.STATEMENT.getName().equals(category)) {
			String tmpSql = sql.trim().toLowerCase(Locale.ROOT);
			if (tmpSql.startsWith("create") || tmpSql.startsWith("alter")
				|| tmpSql.startsWith("comment") || tmpSql.startsWith("drop")) {
				sql = DDL.getFormatter().format(sql);
			} else {
				sql = BASIC.getFormatter().format(sql);
			}
			sql = "|\nHeFormatSql(P6Spy sql, Hibernate format):" + sql;
		}

		return sql;
	}

}
