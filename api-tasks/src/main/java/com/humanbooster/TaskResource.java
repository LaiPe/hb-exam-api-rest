package com.humanbooster;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskResource {
    private final TaskDAO dao = new TaskDAO();

    @GET
    public List<TaskDTO> getAll() {
        List<Task> daoTask = dao.readAll();
        List<TaskDTO> dtoTasks = new ArrayList<>();
        for (Task task : daoTask) {
            dtoTasks.add(task.toDTO());
        }
        return dtoTasks;
    }

    @GET
    @Path("/{id}")
    public TaskDTO getById(@PathParam("id") int id) {
        Task task = dao.read(id);
        if (task == null) {
            throw new WebApplicationException(
                    "Task not found",
                    Response.Status.NOT_FOUND
            );
        }
        return task.toDTO();
    }

    @POST
    public TaskDTO create(Task task) {
        task.setCreatedAt(LocalDate.now());
        task.setUpdatedAt(LocalDate.now());
        Task daoTask = dao.create(task);
        return daoTask.toDTO();
    }

    @PUT
    @Path("/{id}")
    public TaskDTO update(@PathParam("id") int id, Task task) {
        task.setId(id);
        task.setUpdatedAt(LocalDate.now());
        return dao.update(task).toDTO();
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") int id) {
        dao.delete(id);
    }
}
