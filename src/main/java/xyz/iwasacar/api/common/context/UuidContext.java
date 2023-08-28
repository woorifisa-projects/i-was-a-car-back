package xyz.iwasacar.api.common.context;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UuidContext {

	private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

	public static String getUuid() {
		return threadLocal.get();
	}

	public static void setUUid(String sessionId) {
		threadLocal.set(sessionId);
	}

	public static void remove() {
		threadLocal.remove();
	}

}
