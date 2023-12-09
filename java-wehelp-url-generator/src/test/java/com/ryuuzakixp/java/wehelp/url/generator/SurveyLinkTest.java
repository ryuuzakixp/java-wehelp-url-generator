package com.ryuuzakixp.java.wehelp.url.generator;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class SurveyLinkTest {

    @Test
    public void generateSurveyLink_WhenDataIsValid_ReturnString() throws RequiredFieldException {
        Map<String, Object> data = createValidData();

        Map<Integer, String> customFields = new HashMap<>();
        customFields.put(1, "xxx");
        customFields.put(2, "xxx");
        customFields.put(3, "xxx");

        data.put("cf", customFields);

        String encryptKey = "xxxx";
        String url = SurveyLink.generate(data, encryptKey);

        assertNotNull(url);
        assertTrue(url.startsWith("https://app.wehelpsoftware.com/survey_persons/link?access_token="));
    }

    @Test(expected = RequiredFieldException.class)
    public void whenDataNoHasCode_ReturnRequiredFieldException() throws RequiredFieldException {
        Map<String, Object> data = createValidData();
        data.remove("code");
        String encryptKey = "xxxx";
        SurveyLink.generate(data, encryptKey);
    }

    @Test(expected = RequiredFieldException.class)
    public void whenDataNoHasExperienceId_ReturnRequiredFieldException() throws RequiredFieldException {
        Map<String, Object> data = createValidData();
        data.remove("experience_id");
        String encryptKey = "xxxx";
        SurveyLink.generate(data, encryptKey);
    }

    @Test(expected = RequiredFieldException.class)
    public void whenDataNoHasExperienceDate_ReturnRequiredFieldException() throws RequiredFieldException {
        Map<String, Object> data = createValidData();
        data.remove("experience_date");
        String encryptKey = "xxxx";
        SurveyLink.generate(data, encryptKey);
    }

    @Test(expected = RequiredFieldException.class)
    public void whenDataNoHasCompanyUnitCode_ReturnRequiredFieldException() throws RequiredFieldException {
        Map<String, Object> data = createValidData();
        data.remove("company_unit_code");
        String encryptKey = "xxxx";
        SurveyLink.generate(data, encryptKey);
    }

    @Test(expected = RequiredFieldException.class)
    public void whenDataNoHasPerson_ReturnRequiredFieldException() throws RequiredFieldException {
        Map<String, Object> data = createValidData();
        data.remove("person");
        String encryptKey = "xxxx";
        SurveyLink.generate(data, encryptKey);
    }

    @Test(expected = RequiredFieldException.class)
    public void whenDataPersonNoHasName_ReturnRequiredFieldException() throws RequiredFieldException {
        Map<String, Object> data = createValidData();
        Map<String, String> person = (Map<String, String>) data.get("person");
        data.remove("person");
        person.remove("name");
        data.put("person", person);
        String encryptKey = "xxxx";
        SurveyLink.generate(data, encryptKey);
    }

    @Test(expected = RequiredFieldException.class)
    public void whenDataPersonNoHasInternalCode_ReturnRequiredFieldException() throws RequiredFieldException {
        Map<String, Object> data = createValidData();
        Map<String, String> person = (Map<String, String>) data.get("person");
        data.remove("person");
        person.remove("internal_code");
        data.put("person", person);
        String encryptKey = "xxxx";
        SurveyLink.generate(data, encryptKey);
    }

    @Test(expected = RequiredFieldException.class)
    public void whenDataPersonNoHasCompanyUnitCode_ReturnRequiredFieldException() throws RequiredFieldException {
        Map<String, Object> data = createValidData();
        Map<String, String> person = (Map<String, String>) data.get("person");
        data.remove("person");
        person.remove("company_unit_code");
        data.put("person", person);
        String encryptKey = "xxxx";
        SurveyLink.generate(data, encryptKey);
    }

    @Test(expected = RequiredFieldException.class)
    public void whenDataPersonIsNotAnInstanceOfMap_ReturnRequiredFieldException() throws RequiredFieldException {
        Map<String, Object> data = createValidData();
        Map<String, String> person = (Map<String, String>) data.get("person");
        data.remove("person");
        data.put("person", 10);
        String encryptKey = "xxxx";
        SurveyLink.generate(data, encryptKey);
    }

    @Test
    public void whenDataIsInvalid_ReturnRequiredFieldException_checkMessage() {

        String encryptKey = "xxxx";
        try {
            Map<String, Object> data = createValidData();
            data.remove("code");
            SurveyLink.generate(data, encryptKey);
            fail("must be return RequiredFieldException");
        } catch (RequiredFieldException rfe) {
            String expect = "Required field code not found.";
            assertTrue(expect.equals(rfe.getMessage()));
        }

        try {
            Map<String, Object> data = createValidData();
            data.remove("experience_id");
            SurveyLink.generate(data, encryptKey);
            fail("must be return RequiredFieldException");
        } catch (RequiredFieldException rfe) {
            String expect = "Required field experience_id not found.";
            assertTrue(expect.equals(rfe.getMessage()));
        }

        try {
            Map<String, Object> data = createValidData();
            data.remove("experience_date");
            SurveyLink.generate(data, encryptKey);
            fail("must be return RequiredFieldException");
        } catch (RequiredFieldException rfe) {
            String expect = "Required field experience_date not found.";
            assertTrue(expect.equals(rfe.getMessage()));
        }

        try {
            Map<String, Object> data = createValidData();
            data.remove("company_unit_code");
            SurveyLink.generate(data, encryptKey);
            fail("must be return RequiredFieldException");
        } catch (RequiredFieldException rfe) {
            String expect = "Required field company_unit_code not found.";
            assertTrue(expect.equals(rfe.getMessage()));
        }

        try {
            Map<String, Object> data = createValidData();
            data.remove("person");
            SurveyLink.generate(data, encryptKey);
            fail("must be return RequiredFieldException");
        } catch (RequiredFieldException rfe) {
            String expect = "Required field person not found.";
            assertTrue(expect.equals(rfe.getMessage()));
        }

        try {
            Map<String, Object> data = createValidData();
            data.remove("person");
            data.put("person", 10);
            SurveyLink.generate(data, encryptKey);
            fail("must be return RequiredFieldException");
        } catch (RequiredFieldException rfe) {
            String expect = "Error person must be an instance of map.";
            assertTrue(expect.equals(rfe.getMessage()));
        }
    }

    @Test
    public void whenDataPersonNoHasCompanyUnitCode_ReturnRequiredFieldException_checkMessage() {

        String encryptKey = "xxxx";

        try {
            Map<String, Object> data = createValidData();
            Map<String, String> person = (Map<String, String>) data.get("person");
            data.remove("person");
            person.remove("name");
            data.put("person", person);
            SurveyLink.generate(data, encryptKey);
            fail("must be return RequiredFieldException");
        } catch (RequiredFieldException rfe) {
            String expect = "Required field person: name not found.";
            assertTrue(expect.equals(rfe.getMessage()));
        }

        try {
            Map<String, Object> data = createValidData();
            Map<String, String> person = (Map<String, String>) data.get("person");
            data.remove("person");
            person.remove("internal_code");
            data.put("person", person);
            SurveyLink.generate(data, encryptKey);
            fail("must be return RequiredFieldException");
        } catch (RequiredFieldException rfe) {
            String expect = "Required field person: internal_code not found.";
            assertTrue(expect.equals(rfe.getMessage()));
        }

        try {
            Map<String, Object> data = createValidData();
            Map<String, String> person = (Map<String, String>) data.get("person");
            data.remove("person");
            person.remove("type");
            data.put("person", person);
            SurveyLink.generate(data, encryptKey);
            fail("must be return RequiredFieldException");
        } catch (RequiredFieldException rfe) {
            String expect = "Required field person: type not found.";
            assertTrue(expect.equals(rfe.getMessage()));
        }

        try {
            Map<String, Object> data = createValidData();
            Map<String, String> person = (Map<String, String>) data.get("person");
            data.remove("person");
            person.remove("company_unit_code");
            data.put("person", person);
            SurveyLink.generate(data, encryptKey);
            fail("must be return RequiredFieldException");
        } catch (RequiredFieldException rfe) {
            String expect = "Required field person: company_unit_code not found.";
            assertTrue(expect.equals(rfe.getMessage()));
        }
    }

    private Map<String, Object> createValidData() {
        Map<String, Object> data = new HashMap<>();
        data.put("code", "xxxx");
        data.put("experience_id", "xxxx");
        data.put("experience_date", "2023-12-07 11:00:00");
        data.put("company_unit_code", "xxxx");

        Map<String, String> person = new HashMap<>();
        person.put("name", "xxxx");
        person.put("internal_code", "xxxx");
        person.put("type", "CUSTOMER");
        person.put("company_unit_code", "xxx");

        data.put("person", person);

        return data;
    }
}
