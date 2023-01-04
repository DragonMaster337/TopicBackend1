package za.webber.projects.resources;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import za.webber.projects.model.Topic;
import static org.junit.jupiter.api.Assertions.*;


import java.util.Arrays;
import java.util.List;


@QuarkusTest
public class TopicTest
{
    @Test
    public void testGetTopics() {
        Response response = RestAssured.get("/topic");
        response.then().contentType("application/json");

        List<Topic> returnedTopics = Arrays.asList(response.getBody().as(Topic[].class));
        Topic topic = returnedTopics.get(0);
                //(Topic) response.body().as(List.class).get(0);
        assertEquals("Overwatch", topic.getTopicName());
    }

}
