package za.webber.projects;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class GreetingTest
{
    @Test
    public void testHello() {
        RestAssured.when().get("/hello?name=Steven").then()
                .contentType("text/plain")
                .body(equalTo("Hello Steven!"));
    }

}
