## Description

Function to help generate valid url for surveys on Wehelp.

## How to use
The distribution is hosted on mavenCentral. To use the client, you can add the mavenCentral repository to your dependencies.

## Maven
You can include as dependency add the project declaration to your pom.xml:

```xml
<dependency>
    <groupId>io.github.ryuuzakixp.wehelp.url</groupId>
    <artifactId>generator</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Usage

Example minimum data required

```java
Map<String, Object> data = new HashMap<>();
data.put("code", "xxxx");
data.put("experience_id", "xxxx");
data.put("experience_date", "2023-12-07 11:00:00");//Y-m-d H:i:s
data.put("company_unit_code", "xxxx");

Map<String, String> person = new HashMap<>();
person.put("name", "xxxx");
person.put("internal_code", "xxxx");
person.put("type", "CUSTOMER");//CUSTOMER,COLLABORATOR
person.put("company_unit_code", "xxx");

data.put("person", person);

String encryptKey = "xxxx";
String url = SurveyLink.generate(data, encryptKey);

```
Example full data

```java
Map<String, Object> data = new HashMap<>();
data.put("code", "xxxx");
data.put("experience_id", "xxxx");
data.put("experience_date", "2023-12-07 11:00:00");//Y-m-d H:i:s
data.put("company_unit_code", "xxxx");

Map<String, String> person = new HashMap<>();
person.put("name", "xxxx");
person.put("internal_code", "xxxx");
person.put("type", "CUSTOMER"); //CUSTOMER,COLLABORATOR
person.put("company_unit_code", "xxx");
person.put("created_at", "2022-10-10");//Y-m-d
person.put("date_of_birth", "1988-07-06");//Y-m-d
person.put("language", "xxx");//PORTUGUESE,SPANISH,ENGLISH
person.put("email", "xxx");
person.put("phone", "xxx");

data.put("person", person);

Map<String, String> customFields = new HashMap<>();
customFields.put("1", "xxx");//id:value
customFields.put("2", "xxx");
customFields.put("3", "xxx");

data.put("cf", customFields);

String encryptKey = "xxxx";
String url = SurveyLink.generate(data, encryptKey);//https://app.wehelpsoftware.com/survey_persons/link?access_token=xxx...

```

## Exceptions

To prevent errors when generating a valid url, we validate the mandatory fields, if the field does not exist an RequiredFieldException will be thrown.
