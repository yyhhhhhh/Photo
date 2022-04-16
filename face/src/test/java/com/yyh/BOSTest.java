package com.yyh;

import com.yyh.commons.utils.JsonUtil;
import com.yyh.entity.ImageFace;
import com.yyh.service.BaiduAiService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @author yyh
 * @date 2022-04-13 08:34
 */
public class BOSTest {


    public static void main(String[] args) {
        BaiduAiService service = new BaiduAiService();

        JSONObject jsonObject = service.checkFaceWithUrl("https://yyh777.su.bcebos.com/yyh777/aim.jpg");
        if("null" == String.valueOf(jsonObject.get("result"))){
            return;
        }
        System.out.println("123");
        List<ImageFace> imageFace = JsonUtil.getImageFace(jsonObject);
        for (ImageFace face : imageFace) {
            System.out.println(face);
        }
        /*JSONObject result1 = jsonObject.getJSONObject("result");
        System.out.println(result1);
        String[] names = JSONObject.getNames(result1);
        System.out.println(names[1]);
        JSONArray jsonArray = result1.getJSONArray(names[1]);
        for(int i=0;i<jsonArray.length();i++){
            System.out.println(jsonArray.get(i));
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            Map<String, Object> stringObjectMap = jsonObject1.toMap();
            stringObjectMap.forEach((k,v)->{
                System.out.println(k+" "+v);
            });
            Object face_type = stringObjectMap.get("face_type");

            System.out.println(String.valueOf(face_type).replaceAll("}","").substring(String.valueOf(face_type).lastIndexOf("=")+1));
        }*/
    }
}
