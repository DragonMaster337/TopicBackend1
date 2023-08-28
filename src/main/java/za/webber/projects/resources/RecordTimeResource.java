package za.webber.projects.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import za.webber.projects.model.TimeRecord;
import za.webber.projects.services.TimeRecordService;

import java.util.List;
import java.util.UUID;

@Path("/timerecord")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RecordTimeResource {

    @Inject
    TimeRecordService service;

    @GET
    public List<TimeRecord> getAll() {
        return service.findAll();
    }

    @GET
    @Path("/query")
    public TimeRecord getSingle(@QueryParam("name") String name) {
        return service.get(name);
    }

    @GET
    @Path("/create")
    @Produces(MediaType.TEXT_HTML)
    public String createTimeRecord(@QueryParam("name") String name) {
        TimeRecord timerecord = new TimeRecord();
        timerecord.setId(UUID.randomUUID().toString());
        timerecord.setName(name);
        System.out.println(name);
        timerecord.setDescription("This should actually be the current date and time.");
        TimeRecord savedTimeRecord = service.add(timerecord);
        return "Time Entry Created for " + name + " at " + savedTimeRecord.getDescription();
    }

    @POST
    public List<TimeRecord> add(TimeRecord timeRecord) {
        service.add(timeRecord);
        return getAll();
    }
}