package za.webber.projects.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import za.webber.projects.model.TimeRecord;
import za.webber.projects.services.TimeRecordService;

import java.util.List;

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
    @Path("{name}")
    public TimeRecord getSingle(String name) {
        return service.get(name);
    }

    @POST
    public List<TimeRecord> add(TimeRecord timeRecord) {
        service.add(timeRecord);
        return getAll();
    }
}