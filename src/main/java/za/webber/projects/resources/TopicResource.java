package za.webber.projects.resources;

import za.webber.projects.model.Message;
import za.webber.projects.model.Topic;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

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

        return findTopic(id);

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Topic createTopic(Topic topicToCreate) {
        Topic creator = new Topic();
        creator.setId(UUID.randomUUID().toString());
        creator.setTopicName(topicToCreate.getTopicName());
        creator.setMessages(topicToCreate.getMessages());

        return creator;
    }

    @POST
    @Path("{topicId}/message")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Topic createMessage(@PathParam("topicId") String id, Message messageCreator) {
        Topic topicToAddTo = findTopic(id);
        Message message = new Message();
        message.setId(UUID.randomUUID().toString());
        message.setText(messageCreator.getText());
        topicToAddTo.addMessage(message);
        return topicToAddTo;
    }

    private Topic findTopic(String topicId) {
        List<Topic> topicList = loadTopics();
        return topicList.stream().filter( topic ->  topic.getId().equals(topicId)  ).findFirst().orElse(null);
    }
}