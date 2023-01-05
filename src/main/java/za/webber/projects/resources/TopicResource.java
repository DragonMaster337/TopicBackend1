package za.webber.projects.resources;

import za.webber.projects.model.Topic;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/topic")
public class TopicResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Topic> getTopics() {
        return loadTopics();
    }
    private List<Topic> loadTopics() {

        Topic topicOW = new Topic();
        Topic topicSR = new Topic();
        topicOW.setTopicName("Overwatch");
        topicSR.setTopicName("Skyrim");
        topicOW.setId("OW1");
        topicSR.setId("SR1");

        return List.of(topicOW, topicSR);
    }

    @GET
    @Path("{topicId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Topic getTopic(@PathParam("topicId") String id) {
        List<Topic> topicList = loadTopics();
        for (Topic topic: topicList) {
            if (topic.getId().equals(id)) {
                return topic;
            }
        }
        return null;
    }
}