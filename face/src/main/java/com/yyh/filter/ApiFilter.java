package com.yyh.filter;

import com.alibaba.fastjson.JSONObject;
import com.yyh.commons.utils.JsonObjectUtil;
import com.yyh.entity.User;
import com.yyh.commons.utils.CrosUtil;
import com.yyh.service.UserTokenService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yyh
 * @date 2022-03-30 21:44
 */
@WebFilter(urlPatterns = "/api/*")
public class ApiFilter implements Filter {

    private UserTokenService service = new UserTokenService();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("utf-8");
        CrosUtil.solveCros(response);
        String url = request.getRequestURI();
        if(url.contains("/api/login") || url.indexOf("validate-code") > 0){
            filterChain.doFilter(request,response);
            return;
        }
        String token = request.getParameter("token");
        if(null == token){
            JSONObject jb = new JSONObject();
            jb.put("error","未登录,拒绝访问");
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(401);
            response.getWriter().print(jb.toJSONString());
        }else{
            UserTokenService service = new UserTokenService();
            User user = service.getUser(token);
            if(null != user){
                filterChain.doFilter(request,response);
            }else{
                JSONObject jb = new JSONObject();
                jb.put("error","token无效");
                response.setContentType("application/json;charset=utf-8");
                response.setStatus(401);
                response.getWriter().print(jb.toJSONString());
            }
        }
    }
}
