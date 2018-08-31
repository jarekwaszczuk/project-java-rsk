package pl.r80.rsk.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.r80.rsk.Firm.FirmService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class SiteFilter implements Filter {

    private final FirmService firmService;

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

        if (!"/".equals(req.getRequestURI())) {
            if (req.getSession().getAttribute("SECURITY_CONTEXT_KEY") == null) {
                res.sendRedirect(request.getServletContext().getContextPath() + "/");
            }
//            else{
//                Integer kontekst = req.getSession().getAttribute("KONTEKST");
//                Optional<Firm> firmDB = firmService.findById(kontekst);
//                Firm firm = firmDB.get();
//
//                setKontekst(firm, model);
//
//            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

//    private Model setKontekst(Firm firm, Model model){
//        model.addAttribute("firmKontekst", firm);
//        return model;
//    }
}
