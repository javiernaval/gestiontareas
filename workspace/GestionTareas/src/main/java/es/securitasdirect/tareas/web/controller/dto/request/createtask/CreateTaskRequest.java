package es.securitasdirect.tareas.web.controller.dto.request.createtask;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import es.securitasdirect.tareas.model.Tarea;
import es.securitasdirect.tareas.model.tareaexcel.TareaOtrasCampanas;
import es.securitasdirect.tareas.web.controller.dto.support.BaseRequest;

/**
 * Created by Javier Naval on 06/07/2015.
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class CreateTaskRequest extends BaseRequest {

    private Tarea task;

    public CreateTaskRequest() {
    }

    public CreateTaskRequest(Tarea task) {
        this.task = task;
    }

    public Tarea getTask() {
        return task;
    }

    public void setTask(Tarea task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "CreateTaskRequest{" +
                "task=" + task +
                '}';
    }
}
