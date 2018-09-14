package com.metacube.sageclarity.predictable.config.Filters;

import com.metacube.sageclarity.predictable.controller.UserController;
import com.metacube.sageclarity.predictable.entity.User;
import com.metacube.sageclarity.predictable.enums.ExceptionType;
import com.metacube.sageclarity.predictable.exception.ApplicationLevelException;
import com.metacube.sageclarity.predictable.service.UserService;
import com.metacube.sageclarity.predictable.vo.ResponseObject;
import com.metacube.sageclarity.predictable.vo.UserVO;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@Component
@Order(1)
public class InitializationFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(InitializationFilter.class);

    @Autowired
    UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        Principal principal = req.getUserPrincipal();
        if(req.getSession().getAttribute("user") == null && principal!=null){
            User user = null;
            try {
                user = userService.getByUserName(principal.getName());
                req.getSession().setAttribute("user",new UserVO(user));
            } catch (ApplicationLevelException e) {
                logger.error(e.getMessage(), e);
            }
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
