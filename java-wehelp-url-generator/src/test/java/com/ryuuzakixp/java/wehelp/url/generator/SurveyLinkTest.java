package com.ryuuzakixp.java.wehelp.url.generator;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class SurveyLinkTest {

    @Test
    public void generateSurveyLink_WhenDataIsValid_ReturnString() {
        Map<String, Object> data = new HashMap<>();
        data.put("code", "123");
        
        String url = SurveyLink.generate(data);

        assertNotNull(url);
        assertTrue(url.startsWith("https://app.wehelpsoftware.com/survey_persons/link?access_token="));
    }
}
