package pl.r80.rsk.Filter;

import org.springframework.stereotype.Controller;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class SiteFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (!"/".equals(req.getRequestURI())) {
            if (req.getSession().getAttribute("SECURITY_CONTEXT_KEY") == null) {
                res.sendRedirect(request.getServletContext().getContextPath() + "/");
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
