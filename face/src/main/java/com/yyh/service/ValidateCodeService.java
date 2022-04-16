package com.yyh.service;

import com.yyh.entity.ValidateCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * @author yyh
 * @date 2022-03-31 11:06
 */
public class ValidateCodeService {

    public static Map<String,String> validateCodeStore = new HashMap<>();

    public ValidateCode createValidate(){
        String randomCode = UUID.randomUUID().toString();
        Random random = new Random();
        Integer num1 = random.nextInt(20);
        Integer num2 = random.nextInt(20);
        String imageText = "";
        String result = "";
        if(num1 > num2){
            imageText = num1 + "-" + num2 + "=?";
            result = String.valueOf(num1 - num2);
        }else if(num1 < 10 && num2 < 10){
            imageText = num1 + "*" + num2 + "=?";
            result = String.valueOf(num1 * num2);
        }else {
            imageText = num1 + "+" + num2 + "=?";
            result = String.valueOf(num1 + num2);
        }
        validateCodeStore.put(randomCode,imageText+","+result);

        return new ValidateCode(randomCode,"/image/validate-code?code="+randomCode);
    }

    public boolean validate(String randomCode,String validateCode){
        if(validateCodeStore.containsKey(randomCode)){
            String validateString = validateCodeStore.get(randomCode);
            String answer = validateString.split(",")[1];

            validateCodeStore.remove(randomCode);
            return answer.equals(validateCode);
        }
        return false;
    }

    public String getValidateText(String randomCode){
        if(validateCodeStore.containsKey(randomCode)){
            String validateString = validateCodeStore.get(randomCode);
            return validateString.split(",")[0];
        }
        return null;
    }
}
