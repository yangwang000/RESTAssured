package com.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.pojo.simple.SimplePojo;
import com.rest.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SimplePojoTest {
    ResponseSpecification responseSpecification;

    @BeforeClass
    public void beforeClass() throws Exception {
        TestUtils testUtils = new TestUtils();
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri(testUtils.getString("mockBaseUri")).
                        setContentType(ContentType.JSON).
                        log(LogDetail.ALL);

        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);
        responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void simple_pojo_example() throws JsonProcessingException {
        SimplePojo simplePojo = new SimplePojo("value1", "value2");
/*        SimplePojo simplePojo = new SimplePojo();
        simplePojo.setKey1("value1");
        simplePojo.setKey2("value2");*/

        SimplePojo deserializedPojo = given().
                body(simplePojo).
        when().
                post("/postSimplePojo").
        then().spec(responseSpecification).
                extract().
                response().
                as(SimplePojo.class);

        ObjectMapper objectMapper = new ObjectMapper();
        String deserializedPojoStr = objectMapper.writeValueAsString(deserializedPojo);
        String simplePojoStr = objectMapper.writeValueAsString(simplePojo);
        assertThat(objectMapper.readTree(deserializedPojoStr), equalTo(objectMapper.readTree(simplePojoStr)));

    }
}
