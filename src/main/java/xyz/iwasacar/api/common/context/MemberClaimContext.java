package xyz.iwasacar.api.common.context;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import xyz.iwasacar.api.common.auth.jwt.MemberClaim;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MemberClaimContext {

	private static final ThreadLocal<MemberClaim> threadLocal = new ThreadLocal<>();

	public static MemberClaim getClaim() {
		return threadLocal.get();
	}

	public static void setClaim(MemberClaim claim) {
		threadLocal.set(claim);
	}

	public static void remove() {
		threadLocal.remove();
	}

}
