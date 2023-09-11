package xyz.iwasacar.api.common.filter;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import xyz.iwasacar.api.common.context.UuidContext;

public class UuidContextFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws
		IOException, ServletException {

		String uuid = UUID.randomUUID().toString().split("-")[0];
		UuidContext.setUUid(uuid);
		chain.doFilter(request, response);
		UuidContext.remove();
	}

}
