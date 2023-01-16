package za.webber.projects.resources.unit;

import org.junit.jupiter.api.Test;
import za.webber.projects.model.Message;
import za.webber.projects.model.Topic;
import org.junit.jupiter.api.Assertions;

import java.util.UUID;

public class TopicUnitTest {
    @Test
    public void testDeleteMessage() {
        Topic topic = new Topic();
        Message message = new Message();
        Message deleteMessage = new Message();

        message.setId(UUID.randomUUID().toString());
        message.setText("Skyrim VR");

        topic.addMessage(message);
        String messageToDeleteId = UUID.randomUUID().toString();
        deleteMessage.setId(messageToDeleteId);
        deleteMessage.setText("Skyrim SPECIAL EDITION");
        topic.addMessage(deleteMessage);

        topic.deleteMessage(messageToDeleteId);
        Assertions.assertEquals(1, topic.getMessages().size());
        Assertions.assertEquals("Skyrim VR", topic.getMessages().get(0).getText());
    }
}
