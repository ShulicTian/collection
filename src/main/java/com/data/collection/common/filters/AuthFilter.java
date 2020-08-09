package com.data.collection.common.filters;

import com.data.collection.common.exception.SessionExpireException;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Arrays;

public class AuthFilter extends AuthorizationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        String[] perms = (String[]) o;
        if (Arrays.asList(perms).contains("user")) {
            servletResponse.getWriter().write("TEst");
        }
        return false;
    }
}
