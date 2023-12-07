package com.ryuuzakixp.java.wehelp.url.generator;

import java.util.HashMap;
import java.util.Map;

public class SurveyLink {
    private static final String BASE_URL = "https://app.wehelpsoftware.com/survey_persons/link";
    private String queryParams;


    private SurveyLink(Map<String, Object> data) {
        this.queryParams = "?access_token=";
    }

    public static String generate(Map<String, Object> data) {
        SurveyLink instance = new SurveyLink(data);
        return instance.getUrl();
    }

    private String getUrl() {
        return BASE_URL + queryParams;
    }
}
