package za.webber.projects.resources;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import za.webber.projects.model.Message;
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
    @Test
    public void testGetTopic() {
        Response response = RestAssured.get("/topic/SR1");
        response.then().contentType("application/json");

        Topic returnedTopic = response.getBody().as(Topic.class);
        assertEquals("SR1", returnedTopic.getId());
    }

    @Test
    public void testGetMessage() {
        Message myMessage = new Message();
        myMessage.setText("bob");

        RequestSpecification reqSpec = RestAssured.given();
        reqSpec.header("Content-Type", "application/json");
        reqSpec.body(myMessage);
        Response response = reqSpec.post("/topic/SR1/message");


        response.then().contentType("application/json");

        Topic returnedTopic = response.getBody().as(Topic.class);
        Message returnedMessage = returnedTopic.getMessages().get(0);
        assertEquals("SR1", returnedTopic.getId());
        assertEquals("bob", returnedMessage.getText());
    }


}
