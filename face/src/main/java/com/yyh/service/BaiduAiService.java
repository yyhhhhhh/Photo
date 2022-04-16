package com.yyh.service;

import com.baidu.aip.face.AipFace;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.model.PutObjectResponse;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.HashMap;

/**
 * @author yyh
 * @date 2022-03-30 21:22
 */
public class BaiduAiService {

    public static final String APP_ID = "25865523";
    public static final String API_KEY = "CYGBwgbVxB5uAIQsuLdTzqRW";
    public static final String SECRET_KEY = "LOGsqQlB27Sc2NozX86GdZNVVMQUVpmN";
    public static final String ACCESS_KEY_ID = "48ffe1eca70e4fdfb8f4611a049fde0d";
    public static final String SECRET_ACCESS_KEY = "7206b5b139cc46c0bfa54f80fbfb6c3c";
    public static final String ENDPOINT = "yyh777.su.bcebos.com";

    public JSONObject checkFace(String imageBase64){
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        HashMap<String, Object> options = new HashMap<>();
        options.put("face_field", "age,beauty,expression,face_shape,gender,glasses,landmark,landmark72,landmark150,quality,eye_status,emotion,face_type");
        options.put("max_face_num", "10");
        options.put("face_type", "LIVE");
        options.put("liveness_control", "NONE");

        String image = imageBase64;
        String imageType = "BASE64";

        JSONObject res = client.detect(image, imageType, options);
        return res;
    }

    public JSONObject checkFaceWithUrl(String url){
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        HashMap<String, Object> options = new HashMap<>();
        options.put("face_field", "age,beauty,expression,gender,glasses,emotion,face_type,face_token");
        options.put("max_face_num", "10");
        options.put("face_type", "LIVE");
        options.put("liveness_control", "NONE");

        String imageUrl = url;
        String imageType = "URL";
        JSONObject res = client.detect(imageUrl, imageType, options);
        return res;
    }

    public void putObject(String fileKey, InputStream in){
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID,SECRET_ACCESS_KEY));
        config.setEndpoint(ENDPOINT);
        BosClient client = new BosClient(config);
        PutObjectResponse putObjectResponse = client.putObject("yyh777", fileKey, in);
        System.out.println(putObjectResponse.getETag());
    }
}
