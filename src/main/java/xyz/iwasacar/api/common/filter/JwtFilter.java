// package xyz.iwasacar.api.common.filter;
//
// import java.util.HashMap;
// import java.util.Map;
//
// import javax.servlet.Filter;
// import javax.servlet.FilterChain;
// import javax.servlet.ServletRequest;
// import javax.servlet.ServletResponse;
// import javax.servlet.http.HttpServletResponse;
//
// import org.springframework.http.HttpStatus;
//
// import com.fasterxml.jackson.databind.ObjectMapper;
//
// import lombok.RequiredArgsConstructor;
// import xyz.iwasacar.api.common.auth.jwt.Jwt;
// import xyz.iwasacar.api.common.auth.jwt.JwtProvider;
// import xyz.iwasacar.api.domain.members.service.MemberService;
//
// @RequiredArgsConstructor
// public class JwtFilter implements Filter {
//
// 	private final JwtProvider jwtProvider;
//
// 	private final ObjectMapper objectMapper;
//
// 	private final MemberService memberService;
//
// 	@Override
// 	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException {
// 		Object attribute = request.getAttribute(VerifyUserFilter.AUTHENTICATE_USER);
// 		if (attribute instanceof AuthenticateUser authenticateUser) {
// 			Map<String, Object> claims = new HashMap<>();
// 			String authenticateUserJson = objectMapper.writeValueAsString(authenticateUser);
// 			claims.put(VerifyUserFilter.AUTHENTICATE_USER, authenticateUserJson);
// 			Jwt jwt = jwtProvider.createJwt(claims);
// 			userService.updateRefreshToken(authenticateUser.getEmail(), jwt.getRefreshToken());
// 			String json = objectMapper.writeValueAsString(jwt);
// 			response.setContentType("application/json");
// 			response.setCharacterEncoding("UTF-8");
// 			response.getWriter().write(json);
// 			return;
// 		}
//
// 		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
// 		httpServletResponse.sendError(HttpStatus.BAD_REQUEST.value());
// 	}
// }
//
