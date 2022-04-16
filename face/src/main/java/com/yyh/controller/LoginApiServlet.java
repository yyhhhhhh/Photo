package com.yyh.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yyh.commons.entity.ReturnObject;
import com.yyh.commons.utils.CrosUtil;
import com.yyh.commons.utils.JsonObjectUtil;
import com.yyh.service.AccountService;
import com.yyh.service.UserTokenService;
import com.yyh.service.ValidateCodeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author yyh
 * @date 2022-03-30 21:38
 */
@WebServlet(urlPatterns = "/api/login")
public class LoginApiServlet extends HttpServlet {

    private UserTokenService service = new UserTokenService();
    private ValidateCodeService codeService = new ValidateCodeService();
    private AccountService accountService = new AccountService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        CrosUtil.solveCros(resp);
        JSONObject loginJson = JsonObjectUtil.getParameter(req.getInputStream());
        String login = loginJson.getString("login");
        String password = loginJson.getString("password");
        String randomCode = loginJson.getString("randomCode");
        String validateCode = loginJson.getString("validateCode");
        ObjectMapper om = new ObjectMapper();
        ReturnObject returnObject = null;
        resp.setContentType("application/json;charset=utf-8");
        if(!codeService.validate(randomCode,validateCode)){
            returnObject = new ReturnObject();
            returnObject.setMessage("验证码错误");
            resp.getWriter().print(om.writeValueAsString(returnObject));
            return;
        }
        String token = service.getToken(login);
        returnObject = accountService.loginValidate(login, password);
        if(returnObject.getStatusCode() == 200){
            returnObject.setRetObject(token);
            returnObject.setMessage("登录成功");
            resp.getWriter().print(om.writeValueAsString(returnObject));
        }else{
            resp.getWriter().print(om.writeValueAsString(returnObject));
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CrosUtil.solveCros(resp);
    }
}
