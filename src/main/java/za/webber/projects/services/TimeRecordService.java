package za.webber.projects.services;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import za.webber.projects.model.TimeRecord;

@ApplicationScoped
public class TimeRecordService extends AbstractService {

    private DynamoDbTable<TimeRecord> timeRecordTable;

    @Inject
    TimeRecordService(DynamoDbEnhancedClient dynamoEnhancedClient) {
        timeRecordTable = dynamoEnhancedClient.table(getTableName(),
                TableSchema.fromClass(TimeRecord.class));
        timeRecordTable.createTable();
    }

    public List<TimeRecord> findAll() {
        return timeRecordTable.scan().items().stream().collect(Collectors.toList());
    }

    public TimeRecord add(TimeRecord timeRecord) {
        timeRecordTable.putItem(timeRecord);
        System.out.println("Created, now trying to find it from the Database with ane " + timeRecord.getName());
        return get(timeRecord.getId());
    }

    public TimeRecord get(String id) {
        System.out.println("In the getMethod for name "+ id);
        Key partitionKey = Key.builder().partitionValue(id).build();
        TimeRecord foundTimeRecord = timeRecordTable.getItem(partitionKey);
        if (foundTimeRecord == null) {
            System.out.println("It's null :-(");
        }
        return foundTimeRecord;

    }
}