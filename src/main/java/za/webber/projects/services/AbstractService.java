package za.webber.projects.services;

import java.util.HashMap;
import java.util.Map;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import za.webber.projects.model.TimeRecord;

public abstract class AbstractService {

    public final static String TIME_NAME_COL = "name";
    public final static String TIME_DESC_COL = "description";

    public String getTableName() {
        return "TimeRecords";
    }

    protected ScanRequest scanRequest() {
        return ScanRequest.builder().tableName(getTableName())
                .attributesToGet(TIME_NAME_COL, TIME_DESC_COL).build();
    }

    protected PutItemRequest putRequest(TimeRecord timeRecord) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put(TIME_NAME_COL, AttributeValue.builder().s(timeRecord.getName()).build());
        item.put(TIME_DESC_COL, AttributeValue.builder().s(timeRecord.getDescription()).build());

        return PutItemRequest.builder()
                .tableName(getTableName())
                .item(item)
                .build();
    }

    protected GetItemRequest getRequest(String name) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put(TIME_NAME_COL, AttributeValue.builder().s(name).build());

        return GetItemRequest.builder()
                .tableName(getTableName())
                .key(key)
                .attributesToGet(TIME_NAME_COL, TIME_DESC_COL)
                .build();
    }
}
