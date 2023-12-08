package com.ryuuzakixp.java.wehelp.url.generator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class SurveyLinkTest {

    @Test
    public void generateSurveyLink_WhenDataIsValid_ReturnString() {
        Map<String, Object> data = new HashMap<>();
        data.put("code", "bc6dcb454033a89fdb2d576ade5dd561");
        data.put("experience_id", "");
        data.put("experience_date", "2023-12-07 11:00:00");
        data.put("company_unit_code", "BBLWRI");

        Map<String, Object> personData = new HashMap<>();
        personData.put("name", "John Doe");
        personData.put("internal_code", "001");
        personData.put("type", "CUSTOMER");
        personData.put("company_unit_code", "BBLWRI");

        data.put("person", personData);

        String url = SurveyLink.generate(data, "953ef82aaa021b40dfa9d09ffc793e3cf2af58ca");
        System.out.println(url);
        assertNotNull(url);
        assertTrue(url.startsWith("https://app.wehelpsoftware.com/survey_persons/link?access_token="));
    }

    @Test
    public void testReplace() {
        String inputBase64 = "bc6dcb454033a8+9fdb2d57+/6ade5/dd5+61";
        String result = inputBase64.replace("+", "_").replace("/", "-");
        assertEquals("bc6dcb454033a8_9fdb2d57_-6ade5-dd5_61", result);
    }
}
