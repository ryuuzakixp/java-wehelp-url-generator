package com.ryuuzakixp.java.wehelp.url.generator;

import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.json.JSONObject;

public class SurveyLink {

    private static final String BASE_URL = "https://app.wehelpsoftware.com/survey_persons/link";
    private String queryParams;

    private SurveyLink(Map<String, Object> data, String encryptKey) throws RequiredFieldException {

        validationRequiredFields(data);
        
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("alg", "HS256");
        headerMap.put("typ", "JWT");

        JSONObject jsonHeader = new JSONObject(headerMap);
        JSONObject jsonData = new JSONObject(data);

        String payload = jsonData.toString();
        String header = jsonHeader.toString();

        byte[] hmacBytes = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, encryptKey).hmac(header + payload);
        String accessToken = base64UrlEncode(header) + '.' + base64UrlEncode(payload) + '.' + base64UrlEncode(hmacBytes);

        this.queryParams = "?access_token=" + accessToken;
    }

    public static String generate(Map<String, Object> data, String encryptKey) throws RequiredFieldException {
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

    private String base64UrlEncode(byte[] input) {
        String inputBase64 = Base64.getEncoder().encodeToString(input);
        return inputBase64.replace("+", "_").replace("/", "-");
    }

    private void validationRequiredFields(Map<String, Object> data) throws RequiredFieldException {
        List<String> requiredKeys = Arrays.asList("code", "experience_id", "experience_date", "company_unit_code", "person");

        for (String key : requiredKeys) {
            if (!data.containsKey(key)) {
                throw new RequiredFieldException("Required field " + key + " not found.");
            }
        }

        List<String> requiredKeysPerson = Arrays.asList("name", "internal_code", "type", "company_unit_code");

        if (data.get("person") instanceof Map) {
            Map<String, String> person = (Map<String, String>) data.get("person");

            for (String key : requiredKeysPerson) {
                if (!person.containsKey(key)) {
                    throw new RequiredFieldException("Required field person: " + key + " not found.");
                }
            }
        } else {
            throw new RequiredFieldException("Error person must be an instance of map.");
        }
    }
}
