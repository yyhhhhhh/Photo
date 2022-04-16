package com.yyh.commons.utils;

import com.yyh.entity.ImageFace;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yyh
 * @date 2022-04-13 09:57
 */
public class JsonUtil {

    public static final List<String> jsonList = Stream.of
            ("expression","emotion","face_type","glasses","gender")
            .collect(Collectors.toList());

    public static final List<String> jsonNot = Stream.of
                    ("angle","location","face_probability")
            .collect(Collectors.toList());

    public static List<ImageFace> getImageFace(JSONObject jsonObject){
        List<ImageFace> list = new ArrayList<>();
        if("null".equals(String.valueOf(jsonObject.get("result")))){
            return null;
        }
        JSONObject result = jsonObject.getJSONObject("result");
        String[] names = JSONObject.getNames(result);
        JSONArray jsonArray = result.getJSONArray(names[1]);
        for(int i=0;i<jsonArray.length();i++){
            List<String> stringList = new ArrayList<>();
            ImageFace imageFace = new ImageFace();
            Map<String, Object> stringObjectMap = jsonArray.getJSONObject(i).toMap();
            stringObjectMap.forEach((k,v)->{
                if(jsonList.contains(k)){
                    stringList.add(String.valueOf(v).replaceAll("}","").substring(String.valueOf(v).lastIndexOf("=")+1));
                }else if(!jsonNot.contains(k)){
                    stringList.add(String.valueOf(v));
                }
            });
            imageFace.setGlasses(stringList.get(0));
            imageFace.setExpression(stringList.get(1));
            imageFace.setEmotion(stringList.get(2));
            imageFace.setFaceBeauty(Integer.valueOf(stringList.get(3).substring(0,stringList.get(3).indexOf("."))));
            imageFace.setGender(stringList.get(4));
            imageFace.setFaceType(stringList.get(5));
            imageFace.setFaceToken(stringList.get(6));
            imageFace.setFaceAge(Integer.valueOf(stringList.get(7)));
            imageFace.setFaceTime(DateUtil.formatDateTime(new Date()));
            list.add(imageFace);
        }
        return list;
    }
}
