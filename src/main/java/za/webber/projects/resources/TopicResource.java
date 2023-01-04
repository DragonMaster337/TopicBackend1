package za.webber.projects.resources;

import za.webber.projects.model.Topic;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/topic")
public class TopicResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Topic> getTopics() {
        Topic bob = new Topic();
        bob.setTopicName("Overwatch");

        return List.of(bob);
    }
}