package es.securitasdirect.tareas.web.controller.task;

import es.securitasdirect.tareas.model.InstallationData;
import es.securitasdirect.tareas.model.TareaAviso;
import es.securitasdirect.tareas.model.external.Pair;
import es.securitasdirect.tareas.service.ExternalDataService;
import es.securitasdirect.tareas.service.InstallationService;
import es.securitasdirect.tareas.service.QueryTareaService;
import es.securitasdirect.tareas.web.controller.AgentController;
import es.securitasdirect.tareas.web.controller.BaseController;
import es.securitasdirect.tareas.web.controller.TaskController;
import es.securitasdirect.tareas.web.controller.dto.TareaResponse;
import es.securitasdirect.tareas.web.controller.dto.request.notificationtask.*;
import es.securitasdirect.tareas.web.controller.dto.response.NotificationTaskResponse;
import es.securitasdirect.tareas.web.controller.dto.response.PairListResponse;
import es.securitasdirect.tareas.web.controller.dto.support.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.wso2.ws.dataservice.DataServiceFault;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * @author jel
 */

@Controller
@RequestMapping("/notificationtask")
public class NotificationTaskController extends TaskController {

    @Inject
    private QueryTareaService queryTareaService;
    @Inject
    private ExternalDataService externalDataService;
    @Inject
    private InstallationService installationDataService;
    @Autowired  //TODO METER ESTO EN TODOS
    private AgentController agentController;

    /* TODO COMENTAR TODOS LOS GETTASK
    @RequestMapping(value = "/gettask", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    TareaResponse getNotificationTask(
            @RequestParam(value = "ccUserId", required = true) String ccUserId,
            @RequestParam(value = "callingList", required = true) String callingList,
            @RequestParam(value = "tareaId", required = true) String tareaId
    ) {
        String SERVICE_MESSAGE = "notificationTask.getTask";
        LOGGER.debug("Get notification task for params: \nccUserId:{}\ncallingList:{}\ntareaId:{}", ccUserId, callingList, tareaId);
        TareaResponse response;
        try {
            TareaAviso task = (TareaAviso) queryTareaService.queryTarea(ccUserId, callingList, tareaId);
            LOGGER.debug("Notification task obtained from service:");
            response = super.processSuccessTask(task, SERVICE_MESSAGE);
        } catch (Exception e) {
            //EN el caso de obtener una excepción, se realiza el procesamiento de la excepción que añade los errores correspondientes, y se incluye en la respuesta.
            response = new TareaResponse(processException(e, SERVICE_MESSAGE));
        }
        return response;
    }*/


    @RequestMapping(value = "/getInstallationAndTask", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    NotificationTaskResponse getInstallationAndTask(
            @RequestParam(value = "installationId", required = true) String installationId,  //TODO QUITAR ESTE PARAMETRO
            @RequestParam(value = "ccUserId", required = true) String ccUserId, //TODO QUITAR ESTE PARAMETRO
            @RequestParam(value = "callingList", required = true) String callingList,
            @RequestParam(value = "tareaId", required = true) String tareaId
    ) {
        NotificationTaskResponse response = new NotificationTaskResponse();
        if (agentController.isLogged()) {  //TODO
            try {
                //Buscar Tarea
                TareaAviso task = (TareaAviso) queryTareaService.queryTarea(
                        agentController.getAgent().getIdAgent(), //TODO
                        agentController.getAgent().getAgentCountryJob(),//TODO
                        agentController.getAgent().getDesktopDepartment()//TODO
                        , callingList, tareaId);
                if (task != null) {
                    response.setTarea(task);
                    //Buscamos la instalación
                    if (task.getNumeroInstalacion() != null) {
                        InstallationData installationData = installationDataService.getInstallationData(task.getNumeroInstalacion());
                        if (installationData != null) {
                            response.setInstallationData(installationData);
                        } else {
                            response.danger("getTask.noInstallation");
                        }
                    } else {
                        response.danger("getTask.noInstallation");
                    }
                } else {
                    response.danger("getTask.notFound");
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
                processException(e);
            }
        } else {
            response.danger("agent.notLoggedIn"); //TODO
        }
        return response;

        /*

        TareaAviso task = null;
        String TASK_SERVICE_MESSAGE = "notificationTask.getTask";
        try{
            //Buscar Tarea
            task = (TareaAviso)queryTareaService.queryTarea(ccUserId, callingList, tareaId);
            TareaResponse taskResponse = processSuccessTask(task,TASK_SERVICE_MESSAGE);
            response.addMessages(taskResponse.getMessages());
            response.setTarea(taskResponse.getTarea());
        }catch(Exception e){
            response = new NotificationTaskResponse(processException(e, TASK_SERVICE_MESSAGE));
        }
        if(task!=null){
            LOGGER.debug("Notification task obtained from service: \n{}", task);
        }else{
            LOGGER.debug("Failed to obtain notification task from service");
        }
        LOGGER.debug("Get installation data for params: \ninstallationId: {}", installationId);
        InstallationData installationData = null;
        String INSTALLATION_SERVICE_MESSAGE = "installationData";
        try{
            installationData = installationDataService.getInstallationData(installationId);
            TareaResponse installationResponse = processSuccessInstallation(installationData);
            response.addMessages(installationResponse.getMessages());
            response.setInstallationData(installationResponse.getInstallationData());
        }catch(Exception e){
            response = new NotificationTaskResponse(processException(e, INSTALLATION_SERVICE_MESSAGE));
        }
        if(installationData!=null){
            LOGGER.debug("Installation data obtained from service: \n{}", installationData);
        }else{
            LOGGER.debug("Failed to obtain installation data service.");
        }
        return response;
        */
    }


    @RequestMapping(value = "/getClosing", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    PairListResponse getClosing() {
        String SERVICE_MESSAGE = "notificationtask.getclosing";
        PairListResponse response;
        List<Pair> closingList = null;
        try {
            closingList = externalDataService.dummyPairList();
            response = new PairListResponse();
            response.setPairList(closingList);
            //processresponse
        } catch (Exception e) {
            response = new PairListResponse(processException(e, SERVICE_MESSAGE));
        }
        return response;
    }


    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationTaskController.class);

    @RequestMapping(value = "/aplazar", method = {RequestMethod.PUT}, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse postpone(@RequestBody PostponeNotificationTaskRequest request) {
        return super.delayTask(request.getTask(), request.getRecallType(), request.getDelayDate(), "notificationtask.postpone");
    }

    @RequestMapping(value = "/atras", method = {RequestMethod.PUT}, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse modify(@RequestBody BackNotificationTaskRequest request) {
        LOGGER.debug("Atrás\nRequest: {}", request);
        BaseResponse response = new BaseResponse();
        if (true) {
            response.success(messageUtil.getProperty("notificationTask.back.success"));
        } else {
            response.danger(messageUtil.getProperty("notificationTask.back.error"));
        }
        LOGGER.debug("Atrás tarea de aviso\nResponse:{}", response);
        return response;
    }

    @RequestMapping(value = "/modificar", method = {RequestMethod.PUT}, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse modify(@RequestBody ModifyNotificationTaskRequest request) {
        LOGGER.debug("Modificar tarea\nRequest: {}", request);
        BaseResponse response = new BaseResponse();
        if (true) {
            response.success(messageUtil.getProperty("notificationTask.modify.success"));
        } else {
            response.danger(messageUtil.getProperty("notificationTask.modify.error"));
        }
        LOGGER.debug("Modificación de tarea\nResponse:{}", response);
        return response;
    }

    @RequestMapping(value = "/crearmantenimiento", method = {RequestMethod.PUT}, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse modify(@RequestBody CreateMaintenanceNotificationTaskRequest request) {
        LOGGER.debug("Crear mantenimiento\nRequest: {}", request);
        BaseResponse response = new BaseResponse();
        if (true) {
            response.success(messageUtil.getProperty("notificationTask.createMaintenance.success"));
        } else {
            response.danger(messageUtil.getProperty("notificationTask.createMaintenance.error"));
        }
        LOGGER.debug("Creación de mantenimieto\nResponse:{}", response);
        return response;
    }

    @RequestMapping(value = "/descartar", method = {RequestMethod.PUT}, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse discard(@RequestBody DiscardNotificationTaskRequest request) {
        LOGGER.debug("Descartar\nRequest: {}", request);
        BaseResponse response = new BaseResponse();
        if (true) {
            response.success(messageUtil.getProperty("notificationTask.discard.success"));
        } else {
            response.danger(messageUtil.getProperty("notificationTask.discard.error"));
        }
        LOGGER.debug("Descarte\nResponse:{}", response);
        return response;
    }

    @RequestMapping(value = "/finalizar", method = {RequestMethod.PUT}, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse discard(@RequestBody FinalizeNotificationTaskRequest request) {
        LOGGER.debug("Finalizar\nRequest: {}", request);
        BaseResponse response = new BaseResponse();
        if (true) {
            response.success(messageUtil.getProperty("notificationTask.finalize.success"));
        } else {
            response.danger(messageUtil.getProperty("notificationTask.finalize.error"));
        }
        LOGGER.debug("Finalización\nResponse:{}", response);
        return response;
    }

    private NotificationTaskResponse toNotificationTaskResponse(TareaAviso tarea,
                                                                InstallationData installationData,
                                                                NotificationTaskResponse response) {
        NotificationTaskResponse tareaResponse = new NotificationTaskResponse();
        if (response != null) {
            tareaResponse.addMessages(response.getMessages());
        }
        LOGGER.info("Process task response");
        if (tarea != null) {
            tareaResponse.setTarea(tarea);
            tareaResponse.success(messageUtil.getProperty("task.success"));
        } else {
            tareaResponse.danger(messageUtil.getProperty("task.notFound"));
        }

        if (installationData != null) {
            tareaResponse.setInstallationData(installationData);
            tareaResponse.success(messageUtil.getProperty("installationData.success"));
        } else {
            tareaResponse.danger(messageUtil.getProperty("installationData.notFound"));
        }

        LOGGER.info("Task Response: {}", tareaResponse);
        return tareaResponse;
    }

    private NotificationTaskResponse toNotificationTaskResponse(TareaAviso tarea, InstallationData installationData) {
        return toNotificationTaskResponse(tarea, installationData, null);
    }


}
