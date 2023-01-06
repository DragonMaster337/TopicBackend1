package za.webber.projects.resources;

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
        List<Topic> topicList = loadTopics();

//        for (Topic topic: topicList) {
//            if (topic.getId().equals(id)) {
//                return topic;
//            }
//        }
//        return null;

//      Iterator<Topic> topicIter = topicList.iterator();
//      while (topicIter.hasNext()){
//          Topic topicNext = topicIter.next();
//          if (topicNext.getId().equals(id)) {
//              return topicNext;
//          }
//      }
//      return null;

//        for (int i = 0; i < topicList.size(); i++) {
//            Topic topic = topicList.get(i);
//            if (topic.getId().equals(id)) {
//                return topic;
//            }
//        }
//        return null;

        // Functional search for topic
        return topicList.stream().filter( topic ->  topic.getId().equals(id)  ).findFirst().orElse(null);

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
}