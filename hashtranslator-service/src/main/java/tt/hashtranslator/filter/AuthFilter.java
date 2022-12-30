package tt.hashtranslator.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import tt.hashtranslator.client.AuthClient;

import javax.security.sasl.AuthenticationException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthFilter implements Filter {

    private final AuthClient authClient;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        final String authorization = httpServletRequest.getHeader("Authorization");

        final HttpStatus authorize = authClient.authorize(authorization);

        if (authorize.is4xxClientError()) {
            throw new AuthenticationException("Authorization failed");
        }

        chain.doFilter(request, response);
    }

}
