package com.kai.api.service.translate.impl;

import com.alibaba.fastjson.JSONObject;
import com.kai.api.common.Constant;
import com.kai.api.common.Language;
import com.kai.api.service.translate.TranslateService;
import com.kai.api.util.translate.TransApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class BaiduTranslateService implements TranslateService {

    @Value("${translate.baidu.app-id}")
    private String appId;

    @Value("${translate.baidu.secret-key}")
    private String secretKey;
    @Value("${translate.baidu.url}")
    private String url;


    public String translate(String text, Language from, Language to) {
        TransApi api = new TransApi(appId, secretKey);
        log.info("执行翻译， from :【{}】，to:【{}】，message:{} ", from, to, text);
        final String transResult = api.getTransResult(text, from.name(), to.name());
        log.info("翻译结果， transResult :{} ", transResult);
        JSONObject result=JSONObject.parseObject(transResult);
        if(result!=null&& Objects.equals(result.getString("error_code"), Constant.BAIDU_SUCCESS_CODE)){
            return  result.getJSONObject("trans_result").toJSONString();
        }
        return transResult;
    }


}
