package za.webber.projects.service;

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
    }

    public List<TimeRecord> findAll() {
        return timeRecordTable.scan().items().stream().collect(Collectors.toList());
    }

    public List<TimeRecord> add(TimeRecord timeRecord) {
        timeRecordTable.putItem(timeRecord);
        return findAll();
    }

    public TimeRecord get(String name) {
        Key partitionKey = Key.builder().partitionValue(name).build();
        return timeRecordTable.getItem(partitionKey);
    }
}