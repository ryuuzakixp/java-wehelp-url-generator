package com.ryuuzakixp.java.wehelp.url.generator;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.json.JSONObject;

public class SurveyLink {
    private static final String BASE_URL = "https://app.wehelpsoftware.com/survey_persons/link";
    private String queryParams;

    private SurveyLink(Map<String, Object> data, String encryptKey) {

        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("alg", "HS256");
        headerMap.put("typ", "JWT");

        JSONObject jsonHeader = new JSONObject(headerMap);
        JSONObject jsonData = new JSONObject(data);

        String playload = jsonData.toString();
        String header = jsonHeader.toString();

        //String signature = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, encryptKey).hmacHex(header + playload);
        String signature = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, encryptKey).hmacHex(header + playload);
        
        String accessToken = base64UrlEncode(header) + '.' + base64UrlEncode(playload) + '.'
                + base64UrlEncode(signature);

        this.queryParams = "?access_token=" + accessToken;
    }

    public static String generate(Map<String, Object> data, String encryptKey) {
        SurveyLink instance = new SurveyLink(data, encryptKey);
        return instance.getUrl();
    }

    private String getUrl() {
        return BASE_URL + queryParams;
    }

    private String base64UrlEncode(String input) {
        String inputBase64 = Base64.getUrlEncoder().encodeToString(input.getBytes());
        return inputBase64.replace("+", "_").replace("/", "-");
    }
}
