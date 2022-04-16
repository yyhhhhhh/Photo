package com.yyh.controller;

import cn.edu.njuit.web.server.tools.ImageTool;
import com.yyh.service.ValidateCodeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author yyh
 * @date 2022-03-31 11:54
 */
@WebServlet(urlPatterns = "/image/validate-code")
public class ValidateImageApiServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String randomCode = req.getParameter("code");
        ValidateCodeService service = new ValidateCodeService();
        String imageText = service.getValidateText(randomCode);
        if( null != imageText){
            ImageTool tool = new ImageTool();
            Integer width = imageText.length() * 16;
            Integer height = 40;
            ByteArrayOutputStream image = tool.string2Image(imageText, width, height);
            resp.setContentType("image/jpeg");
            image.writeTo(resp.getOutputStream());
        }
    }
}
