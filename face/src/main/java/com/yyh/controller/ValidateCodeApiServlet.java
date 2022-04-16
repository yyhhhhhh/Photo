package com.yyh.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yyh.entity.ValidateCode;
import com.yyh.commons.utils.CrosUtil;
import com.yyh.service.ValidateCodeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yyh
 * @date 2022-03-31 11:51
 */
@WebServlet(urlPatterns = "/api/validate-code")
public class ValidateCodeApiServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CrosUtil.solveCros(resp);
        ValidateCodeService service = new ValidateCodeService();
        ValidateCode validate = service.createValidate();
        resp.setContentType("application/json;charset=utf-8");
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(validate);
        resp.getWriter().print(json);
    }
}
