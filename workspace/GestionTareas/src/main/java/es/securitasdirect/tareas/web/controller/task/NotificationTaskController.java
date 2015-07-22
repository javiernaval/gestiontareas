package es.securitasdirect.tareas.web.controller.task;

import es.securitasdirect.tareas.model.InstallationData;
import es.securitasdirect.tareas.model.Tarea;
import es.securitasdirect.tareas.model.TareaAviso;
import es.securitasdirect.tareas.model.TareaMantenimiento;
import es.securitasdirect.tareas.model.external.Pair;
import es.securitasdirect.tareas.service.ExternalDataService;
import es.securitasdirect.tareas.service.InstallationService;
import es.securitasdirect.tareas.service.QueryTareaService;
import es.securitasdirect.tareas.web.controller.BaseController;
import es.securitasdirect.tareas.web.controller.dto.TareaResponse;
import es.securitasdirect.tareas.web.controller.dto.request.PostponeRequest;
import es.securitasdirect.tareas.web.controller.dto.request.maintenancetask.MaintenanceTaskCreateRequest;
import es.securitasdirect.tareas.web.controller.dto.request.maintenancetask.MaintenanceTaskFinalizeRequest;
import es.securitasdirect.tareas.web.controller.dto.request.notificationtask.*;
import es.securitasdirect.tareas.web.controller.dto.response.NotificationTaskResponse;
import es.securitasdirect.tareas.web.controller.dto.support.BaseResponse;
import es.securitasdirect.tareas.web.controller.dto.support.DummyResponseGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class NotificationTaskController extends BaseController {

    @Inject
    private QueryTareaService queryTareaService;
    @Inject
    private ExternalDataService externalDataService;
    @Inject
    private InstallationService installationDataService;

    @RequestMapping(value = "/gettask", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    TareaResponse getNotificationTask(
            @RequestParam(value = "ccUserId", required = true) String ccUserId,
            @RequestParam(value = "callingList", required = true) String callingList,
            @RequestParam(value = "tareaId", required = true) String tareaId
    ) throws DataServiceFault {
        LOGGER.debug("Get notification task for params: \nccUserId:{}\ncallingList:{}\ntareaId:{}",ccUserId, callingList, tareaId);
        TareaAviso task = (TareaAviso)queryTareaService.queryTarea(ccUserId, callingList, tareaId);
        LOGGER.debug("Notification task obtained from service: \n{}", task);
        return toTareaResponse(task);
    }


    @RequestMapping(value = "/getInstallationAndTask", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    NotificationTaskResponse getInstallationAndTask(
            @RequestParam(value = "installationId", required = true) String installationId,
            @RequestParam(value = "ccUserId", required = true) String ccUserId,
            @RequestParam(value = "callingList", required = true) String callingList,
            @RequestParam(value = "tareaId", required = true) String tareaId
    ) throws DataServiceFault {
        LOGGER.debug("Get notification task for params: \nccUserId:{}\ncallingList:{}\ntareaId:{}",ccUserId, callingList, tareaId);
        TareaAviso task = (TareaAviso)queryTareaService.queryTarea(ccUserId, callingList, tareaId);
        LOGGER.debug("Notification task obtained from service: \n{}", task);
        LOGGER.debug("Get installation data for params: \ninstallationId: {}", installationId);
        InstallationData installationData = installationDataService.getInstallationData(installationId);
        LOGGER.debug("Installation data obtained from service: \n{}", installationData);
        return toNotificationTaskResponse(task, installationData);
    }


    @RequestMapping(value = "/getClosing", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    List<Pair> getClosing() throws DataServiceFault {
        List<Pair> closingList = externalDataService.getClosing();
        return closingList;
    }


    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationTaskController.class);

    @RequestMapping(value = "/aplazar", method ={RequestMethod.PUT}, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse postpone( @RequestBody PostponeNotificationTaskRequest request) {
        return super.delayTask(request.getTask(),request.getRecallType(),request.getDelayDate());
    }



    @RequestMapping(value = "/atras", method ={RequestMethod.PUT}, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse modify( @RequestBody BackNotificationTaskRequest request) {
        LOGGER.debug("Atrás\nRequest: {}", request);
        BaseResponse response = new BaseResponse();
        if(true){
            response.success(messageUtil.getProperty("notificationTask.back.success"));
        }else{
            response.danger(messageUtil.getProperty("notificationTask.back.error"));
        }
        LOGGER.debug("Atrás tarea de aviso\nResponse:{}", response);
        return response;
    }

    @RequestMapping(value = "/modificar", method ={RequestMethod.PUT}, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse modify( @RequestBody ModifyNotificationTaskRequest request) {
        LOGGER.debug("Modificar tarea\nRequest: {}", request);
        BaseResponse response = new BaseResponse();
        if(true){
            response.success(messageUtil.getProperty("notificationTask.modify.success"));
        }else{
            response.danger(messageUtil.getProperty("notificationTask.modify.error"));
        }
        LOGGER.debug("Modificación de tarea\nResponse:{}", response);
        return response;
    }

    @RequestMapping(value = "/crearmantenimiento", method ={RequestMethod.PUT}, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse modify( @RequestBody CreateMaintenanceNotificationTaskRequest request) {
        LOGGER.debug("Crear mantenimiento\nRequest: {}", request);
        BaseResponse response = new BaseResponse();
        if(true){
            response.success(messageUtil.getProperty("notificationTask.createMaintenance.success"));
        }else{
            response.danger(messageUtil.getProperty("notificationTask.createMaintenance.error"));
        }
        LOGGER.debug("Creación de mantenimieto\nResponse:{}", response);
        return response;
    }

    @RequestMapping(value = "/descartar", method ={RequestMethod.PUT}, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse discard( @RequestBody DiscardNotificationTaskRequest request) {
        LOGGER.debug("Descartar\nRequest: {}", request);
        BaseResponse response = new BaseResponse();
        if(true){
            response.success(messageUtil.getProperty("notificationTask.discard.success"));
        }else{
            response.danger(messageUtil.getProperty("notificationTask.discard.error"));
        }
        LOGGER.debug("Descarte\nResponse:{}", response);
        return response;
    }

    @RequestMapping(value = "/finalizar", method ={RequestMethod.PUT}, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    BaseResponse discard( @RequestBody FinalizeNotificationTaskRequest request) {
        LOGGER.debug("Finalizar\nRequest: {}", request);
        BaseResponse response = new BaseResponse();
        if(true){
            response.success(messageUtil.getProperty("notificationTask.finalize.success"));
        }else{
            response.danger(messageUtil.getProperty("notificationTask.finalize.error"));
        }
        LOGGER.debug("Finalización\nResponse:{}", response);
        return response;
    }

    NotificationTaskResponse toNotificationTaskResponse(TareaAviso tarea,
                                                        InstallationData installationData){
        List<Pair> tipoAvisoList;
        List<Pair> motivoAvisoList;
        List<Pair> closingList;
        List<Pair> closingListAditionalData;

        try{
            tipoAvisoList = externalDataService.getNotificationType();
        }catch (DataServiceFault dsf){
            LOGGER.error("Error obteniendo los tipos de aviso: \nFaultInfo:{}\nMessage:{}\n{}", dsf.getFaultInfo(), dsf.getMessage(),dsf.toString());
            tipoAvisoList = null;
        }

        try{
            motivoAvisoList = externalDataService.getNotificationTypeReason();
        }catch (DataServiceFault dsf){
            LOGGER.error("Error obteniendo los motivos de tipos de aviso: \nFaultInfo:{}\nMessage:{}\n{}", dsf.getFaultInfo(), dsf.getMessage(),dsf.toString());
            motivoAvisoList = null;
        }

        try{
            closingList = externalDataService.getClosing();
        }catch (DataServiceFault dsf){
            LOGGER.error("Error obteniendo los tipos de cierre: \nFaultInfo:{}\nMessage:{}\n{}", dsf.getFaultInfo(), dsf.getMessage(),dsf.toString());
            closingList = null;
        }

        try{
            closingListAditionalData = externalDataService.getDatosAdicionalesCierreTareaAviso();
        }catch (DataServiceFault dsf){
            LOGGER.error("Error datos adicionales de tipos de cierre:\nFaultInfo:{}\nMessage:{}\n{}", dsf.getFaultInfo(), dsf.getMessage(),dsf.toString());
            closingListAditionalData = null;
        }
        NotificationTaskResponse tareaResponse = new NotificationTaskResponse();

        LOGGER.info("Process task response");
        if (tarea != null) {
            tareaResponse.setTarea(tarea);
            tareaResponse.success(messageUtil.getProperty("task.success"));
        } else {
            tareaResponse.danger(messageUtil.getProperty("task.notFound"));
        }

        if(installationData!=null){
            tareaResponse.setInstallationData(installationData);
            tareaResponse.success(messageUtil.getProperty("installationData.success"));
        }else{
            tareaResponse.danger(messageUtil.getProperty("installationData.notFound"));
        }

        if(tipoAvisoList!=null){
            tareaResponse.setTipoAvisoList(tipoAvisoList);
            tareaResponse.success(messageUtil.getProperty("notificationTask.tipoAvisoList.success"));
        }else{
            tareaResponse.danger(messageUtil.getProperty("notificationTask.tipoAvisoList.notFound"));
        }
        if(motivoAvisoList!=null){
            tareaResponse.setMotivoAvisoList(motivoAvisoList);
            tareaResponse.success(messageUtil.getProperty("notificationTask.motivoAvisoList.success"));
        }else{
            tareaResponse.danger(messageUtil.getProperty("notificationTask.motivoAvisoList.notFound"));
        }
        if(closingList!=null){
            tareaResponse.setClosingList(closingList);
            tareaResponse.success(messageUtil.getProperty("notificationTask.closingList.success"));
        }else{
            tareaResponse.danger(messageUtil.getProperty("notificationTask.closingList.notFound"));
        }
        if(closingListAditionalData!=null){
            tareaResponse.setClosingListAditionalData(closingListAditionalData);
            tareaResponse.success(messageUtil.getProperty("notificationTask.closingListAditionalData.success"));
        }else{
            tareaResponse.danger(messageUtil.getProperty("notificationTask.closingListAditionalData.notFound"));
        }
        LOGGER.info("Task Response: {}", tareaResponse);
        return tareaResponse;
    }

}
