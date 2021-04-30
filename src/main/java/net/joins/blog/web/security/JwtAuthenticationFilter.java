package net.joins.blog.web.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.joins.blog.common.Constants;
import net.joins.blog.common.util.StringUtils;
import net.joins.blog.web.ApiStatus;
import net.joins.blog.web.util.RequestUtil;
import net.joins.blog.web.vo.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    final WebAuthenticationProvider authenticationProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getServletPath();
        String token = request.getHeader(Constants.X_TOKEN);
        log.info("path {} - token {}", path, token);

        if (StringUtils.isNotEmpty(token)) {
            String ipAddress = RequestUtil.getClientIP(request);
            LoginAuthentication in = new LoginAuthentication(null, null, token, ipAddress);
            try {
                Authentication out = authenticationProvider.validateToken(in);
                SecurityContextHolder.getContext().setAuthentication(out);
            } catch (Exception e) {
                ApiResponse apiResponse = ApiResponse.of().error(ApiStatus.FORBIDDEN.getCode(), e.getLocalizedMessage());
                response.setContentType("application/json");
                response.setStatus(apiResponse.getCode());
                new ObjectMapper().writeValue(response.getWriter(), apiResponse.toResponseEntity().getBody());
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

}
