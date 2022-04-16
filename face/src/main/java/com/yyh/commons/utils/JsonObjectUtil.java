package com.yyh.commons.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author yyh
 * @date 2022-03-31 10:45
 */
public class JsonObjectUtil {

    public static JSONObject getParameter(InputStream in) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String str = "";
        while (null != (str = br.readLine())){
            builder.append(str);
        }
        JSONObject loginJson = JSONObject.parseObject(builder.toString());

        return loginJson;
    }
}
