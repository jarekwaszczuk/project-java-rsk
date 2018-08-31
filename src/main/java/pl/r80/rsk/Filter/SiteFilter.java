package pl.r80.rsk.Filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.r80.rsk.Firm.FirmService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

@Controller
public class SiteFilter implements Filter {

    private final FirmService firmService;
    private static final Logger LOGGER = LoggerFactory.getLogger(SiteFilter.class);

    @Autowired
    public SiteFilter(FirmService firmService) {
        this.firmService = firmService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        LOGGER.info("[{} {}] Żądanie {} z: {}", LocalDate.now(), LocalTime.now(), req.getMethod(), req.getRequestURI());

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
