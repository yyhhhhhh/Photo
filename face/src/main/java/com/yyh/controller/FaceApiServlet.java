package com.yyh.controller;

import cn.edu.njuit.web.server.tools.ImageTool;
import com.yyh.commons.utils.CrosUtil;
import com.yyh.service.BaiduAiService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

/**
 * @author yyh
 * @date 2022-03-30 21:19
 */
@WebServlet(urlPatterns = "/api/ai-face")
@MultipartConfig
public class FaceApiServlet extends HttpServlet {

    private BaiduAiService service = new BaiduAiService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CrosUtil.solveCros(resp);
        Part part = req.getPart("image");
        ImageTool imageTool = new ImageTool();
        String image2Base64 = imageTool.image2Base64(part.getInputStream());
        JSONObject res = service.checkFace(image2Base64);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().print(res.toString(2));
    }
}
