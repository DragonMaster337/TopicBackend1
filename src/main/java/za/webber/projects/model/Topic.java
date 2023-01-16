package za.webber.projects.model;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    String topicName;
    List<Message> messages = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addMessage(Message messageToAdd) {
        messages.add(messageToAdd);
    }

    public void deleteMessage(String messageId){
        for (int i=0; i< messages.size(); i++){
            if (messages.get(i).getId().equals(messageId)) {
                messages.remove(i);
            }
        }
    }
}
