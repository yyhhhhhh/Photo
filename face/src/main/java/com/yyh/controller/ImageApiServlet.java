package com.yyh.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yyh.commons.contants.Contants;
import com.yyh.commons.entity.ReturnObject;
import com.yyh.commons.utils.*;
import com.yyh.entity.ImageFace;
import com.yyh.entity.ImageInfo;
import com.yyh.entity.User;
import com.yyh.service.BaiduAiService;
import com.yyh.service.ImageService;
import com.yyh.service.UserTokenService;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yyh
 * @date 2022-04-12 15:27
 */
@WebServlet(urlPatterns = "/api/image-info")
@MultipartConfig
public class ImageApiServlet extends HttpServlet {

    private UserTokenService userService = new UserTokenService();

    private ImageService imageService = new ImageService();

    private BaiduAiService baiduAiService = new BaiduAiService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CrosUtil.solveCros(resp);
        resp.setContentType("application/json;charset=utf-8");
        int pageNum = req.getParameter("pageNum") == null ? 0 : Integer.parseInt(req.getParameter("pageNum"));
        int pageSize = req.getParameter("pageNum") == null ? 0 : Integer.parseInt(req.getParameter("pageSize"));
        ObjectMapper om = new ObjectMapper();
        ReturnObject returnObject = new ReturnObject();
        String token = req.getParameter("token");
        User user = userService.getUser(token);
        if(null == user){
            returnObject.setStatusCode(401);
            resp.getWriter().print(om.writeValueAsString(returnObject));
            return;
        }
        String imageId = req.getParameter("imageId");
        if(null == imageId){
            Map<String,Object> map = new HashMap<>();
            List<ImageInfo> list = imageService.queryPreUserPhotos(user,pageNum,pageSize);
            int total = imageService.queryCountOfImage();
            map.put("imageList",list);
            map.put("total",total);
            returnObject.setStatusCode(200);
            returnObject.setRetObject(map);
            resp.getWriter().print(om.writeValueAsString(returnObject));
        }else{
            ImageInfo imageInfo = imageService.queryImageInfoAndImageFace(user, Integer.parseInt(imageId));
            returnObject.setStatusCode(200);
            returnObject.setRetObject(imageInfo);
            resp.getWriter().print(om.writeValueAsString(returnObject));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CrosUtil.solveCros(resp);
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=utf-8");
        ReturnObject returnObject = new ReturnObject();
        ObjectMapper om = new ObjectMapper();
        User user = userService.getUser(req.getParameter("token"));
        if(null == user){
            returnObject.setStatusCode(401);
            resp.getWriter().print(om.writeValueAsString(returnObject));
            return;
        }
        Part image = req.getPart("image");
        String imageText = req.getParameter("imageText");
        String publishStatus = req.getParameter("publishStatus");
        String fileName = image.getSubmittedFileName();
        fileName = UUIDUtil.getUUID() + fileName.substring(fileName.lastIndexOf("."));
        baiduAiService.putObject(fileName,image.getInputStream());
        ImageInfo imageInfo = new ImageInfo();
        imageInfo.setLogin(user.getLogin());
        imageInfo.setImageUrl(Contants.BOS_URL +fileName);
        imageInfo.setImageText(imageText);
        imageInfo.setPublishStatus(publishStatus);
        int flag = imageService.getImageInfoId(imageInfo);
        if(0 == flag){
            returnObject.setStatusCode(500);
            resp.getWriter().print(om.writeValueAsString(returnObject));
            return;
        }
        JSONObject jsonObject = baiduAiService.checkFaceWithUrl(Contants.BOS_URL + fileName);
        List<ImageFace> imageFaces = JsonUtil.getImageFace(jsonObject);
        if(null == imageFaces){
            returnObject.setStatusCode(200);
            resp.getWriter().print(om.writeValueAsString(returnObject));
            return;
        }
        imageService.saveImageFaceWithId(imageFaces,flag);
        returnObject.setStatusCode(200);
        resp.getWriter().print(om.writeValueAsString(returnObject));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CrosUtil.solveCros(resp);
        resp.setContentType("application/json;charset=utf-8");
        String token = req.getParameter("token");
        String imageId = req.getParameter("imageId");
        ObjectMapper om = new ObjectMapper();
        User user = userService.getUser(token);
        ReturnObject returnObject = new ReturnObject();
        if(null == user){
            returnObject.setStatusCode(401);
            resp.getWriter().print(om.writeValueAsString(returnObject));
            return;
        }else{
            int flag = imageService.updateImageInfoAndFace(user, Integer.parseInt(imageId));
            if(0 == flag){
                returnObject.setStatusCode(401);
                resp.getWriter().print(om.writeValueAsString(returnObject));
            }else{
                returnObject.setStatusCode(200);
                resp.getWriter().print(om.writeValueAsString(returnObject));
            }
        }
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CrosUtil.solveCros(resp);
    }
}
