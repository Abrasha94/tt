package tt.hashtranslator.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import tt.hashtranslator.client.AuthClient;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthFilter implements Filter {

    private final AuthClient authClient;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        final String authorization = httpServletRequest.getHeader("Authorization");

        try {
            authClient.authorize(authorization);
            chain.doFilter(request, response);
        } catch (HttpClientErrorException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorize failed");
        }
    }

}
