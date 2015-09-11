package es.securitasdirect.tareas.service;

import com.webservice.CCLIntegration;
import es.securitasdirect.tareas.exceptions.BusinessException;
import es.securitasdirect.tareas.exceptions.FrameworkException;
import es.securitasdirect.tareas.model.Agent;
import es.securitasdirect.tareas.model.InstallationData;
import es.securitasdirect.tareas.model.TareaAviso;
import es.securitasdirect.tareas.model.tickets.*;
import es.securitasdirect.tareas.model.tickets.operations.CreateTicket;
import es.securitasdirect.tareas.model.tickets.operations.OperateTicket;
import es.securitasdirect.tareas.model.tickets.responses.DATA;
import es.securitasdirect.tareas.support.XmlMarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.ws.dataservice.*;
import wsticketsv2.WsTicketsSoap;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 */
@Named(value = "avisoService")
@Singleton
public class AvisoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AvisoService.class);

    @Inject
    protected WsTicketsSoap wsTickets;
    @Inject
    protected XmlMarshaller xmlMarshaller;
    @Inject
    protected QueryTareaService queryTareaService;
    @Inject
    protected CCLIntegration cclIntegration;
    @Inject
    protected SPAVISOSOPERACIONESPortType spAvisosOperaciones;
    @Inject
    protected SPAIOTAREAS2PortType spAIOTAREAS2PortType;
    @Resource(name = "applicationUser")
    private String applicationUser;
    /**
     * Maps de los paises de AgentCountryJob
     */
    @Resource(name = "agentCountryJob")
    private Map<String, String> agentCountryJob;


    /**
     * creacion del XML para crear un Aviso. Se hace a través de un WS disponible para la aplicación de Tickets.
     */
    public void createTicket(Agent agent, TareaAviso tareaAviso, InstallationData installationData) {
    	
        boolean result = false;
        String idUser = agent.getAgentIBS();

        String idCountry = agentCountryJob.get(agent.getAgentCountryJob());
        String idLanguage = (agent.getCurrentLanguage() != null) ? agent.getCurrentLanguage() : "";
        String idReq = agent.getDesktopDepartment();

        /*
         * Estructura del XML
         */
        CreateTicket createTicket = new CreateTicket();

        Req createReq = new Req();
        Asgto createAsgto = new Asgto();
        Comm createComm = new Comm();
        Opcod createOpcod = new Opcod();
        Clcod createClcod = new Clcod();
        Item createItem = new Item();
        List create_list_item = new ArrayList();

        createTicket.setUser(new User());
        createTicket.setTicket(new Ticket());
        createTicket.setSvrq(new Svrq());


        /* <REQ></REQ> */
        createReq.setIdReq(idReq);
        createReq.setReqName(""); // constante
        createReq.setReqLname1(""); // constante
        createReq.setReqLname2(""); // constante
        createReq.setReqCif(""); // constante
        createReq.setReqEmpl(idUser);
        /* <ASGTO></ASGTO>*/
        createAsgto.setIdAsg(""); // constante
        createAsgto.setIdUser(idUser);
        /* <COMM></COMM>*/
        createComm.setName(""); // constante
        createComm.setlName1(""); // constante
        createComm.setlName2(""); // constante
        createComm.setInChannel("TELF"); // constante
        createComm.setValue((tareaAviso.getTelefonoAviso() != null) ? tareaAviso.getTelefonoAviso() : "");
        createComm.setComent(""); // constante
        createComm.setOutChannel(""); // constante
        createComm.setFrom(tareaAviso.getHorarioDesde());
        createComm.setTo(tareaAviso.getHorarioHasta());
        /* <OPCOD></OPCOD>*/
        createOpcod.setCodKey1(tareaAviso.getTipoAviso1());
        createOpcod.setCodKey2(tareaAviso.getMotivo1());
        /* <CLCOD></CLCOD>*/
        createClcod.setCodKey3(""); // constante
        createClcod.setCodKey4(""); // constante

        /* <ITEM></ITEM> */
        if(tareaAviso.getTipoAviso1() != null) {
            createItem.setIdItemIBS(""); // constante
            createItem.setCount("1");    // constante
            createItem.setIdProblem(tareaAviso.getMotivo1());
            createItem.setIdType(tareaAviso.getTipoAviso1());
            create_list_item.add(createItem);
        }

        /* <ITEM></ITEM> */
        if(tareaAviso.getTipoAviso2() != null) {
            createItem.setIdItemIBS(""); // constante
            createItem.setCount("1");    // constante
            createItem.setIdProblem(tareaAviso.getMotivo2());
            createItem.setIdType(tareaAviso.getTipoAviso2());
            create_list_item.add(createItem);
        }

        /* <ITEM></ITEM> */
        if(tareaAviso.getTipoAviso3() != null) {
            createItem.setIdItemIBS(""); // constante
            createItem.setCount("1");    // constante
            createItem.setIdProblem(tareaAviso.getMotivo3());
            createItem.setIdType(tareaAviso.getTipoAviso3());
            create_list_item.add(createItem);
        }

        /*
         * <USER></USER>
         */
        createTicket.getUser().setIdUser(idUser);
        createTicket.getUser().setIdCountry(idCountry);
        createTicket.getUser().setIdLanguage(idLanguage);
        createTicket.getUser().setT("NOSESSION");  // constante

        /*
         *
         * <TICKET></TICKET>
         */
        createTicket.getTicket().setNumInst(tareaAviso.getNumeroInstalacion());
        createTicket.getTicket().setObserv((tareaAviso.getObservaciones() != null) ? tareaAviso.getObservaciones() : "");
        createTicket.getTicket().setCodZIP(installationData.getCodigoPostal());
        createTicket.getTicket().setCloseTicket("0"); // constante
        createTicket.getTicket().setDataAditional(""); // constante
        createTicket.getTicket().setNoteClose(""); // constante
        createTicket.getTicket().setMorDebt("0"); // constante
        createTicket.getTicket().setTypePanel(installationData.getPanel());

        /* <REQ></REQ>
         */
        createTicket.getTicket().setReq(createReq);
        createTicket.getTicket().setAsgto(createAsgto);
        createTicket.getTicket().setComm(createComm);
        createTicket.getTicket().setOpcod(createOpcod);
        createTicket.getTicket().setClcod(createClcod);

        /*
         * <SVRQ></SVRQ>
         */
        createTicket.getSvrq().setMakeSVRQ("0"); // constante
        createTicket.getSvrq().setIdTec(""); // constante
        createTicket.getSvrq().setInsBoli("0"); // constante
        createTicket.getSvrq().setItems(new ArrayList<Item>());
        createTicket.getSvrq().setItems(create_list_item);


        String xmlCreateTicket = xmlMarshaller.marshalObject(createTicket);
        xmlCreateTicket = xmlCreateTicket.replaceAll("\n", "");

        String xmlResult = wsTickets.create(xmlCreateTicket);

        DATA data = xmlMarshaller.unmarshalData(xmlResult);

        LOGGER.debug("xmlCreateTicket: {} xmlResult:{}", xmlCreateTicket, xmlResult);

        if(data.getERR() == null && data.getTICKET() != null && data.getTICKET().getNumTK() > 0) {
            LOGGER.debug("Sucessfully created Ticket");
        } else {
            throw new BusinessException(BusinessException.ErrorCode.ERROR_FINALIZE_TASK, data.getERR().getDesc(),data.getERR().getCod());
        }

    }

    /**
     * creacion del XML para actualizar un Aviso. Se hace a través de un WS disponible para la aplicación de Tickets.
     */
    public void updateTicket(Agent agent, TareaAviso tareaAviso, InstallationData installationData) {
    	try{
	        boolean result = false;
	        String idUser = agent.getAgentIBS();
	
	        String idCountry = agentCountryJob.get(agent.getAgentCountryJob());
	        String idLanguage = (agent.getCurrentLanguage() != null) ? agent.getCurrentLanguage() : "";
	        String idReq = agent.getDesktopDepartment();
	
	
	        /*
	         * Estructura del XML
	         */
	        OperateTicket operateTicket = new OperateTicket();
	
	        operateTicket.setUSER(new OperateTicket.USER());
	        operateTicket.setTICKET(new OperateTicket.TICKET());
	
	        OperateTicket.TICKET.CODIFICATIONS codifications = new OperateTicket.TICKET.CODIFICATIONS();
	
	
	        OperateTicket.TICKET.CONTACTO contacto = new OperateTicket.TICKET.CONTACTO();
	        contacto.setCodforma("TELF"); // constante
	        contacto.setComentario("");
	        contacto.setDesde(tareaAviso.getHorarioDesde());
	        contacto.setHasta(tareaAviso.getHorarioHasta());
	        contacto.setNombre(tareaAviso.getPersonaContacto());
	        contacto.setValor((tareaAviso.getTelefonoAviso() != null) ? tareaAviso.getTelefonoAviso() : "");
	        operateTicket.getTICKET().setCONTACTO(contacto);
	
	        OperateTicket.TICKET.CLOSE close = new OperateTicket.TICKET.CLOSE();
	        close.setCloseTicket(0); // constante
	        close.setDataAditional(""); // constante
	        close.setNotaCierre(tareaAviso.getObservaciones());
	        operateTicket.getTICKET().setCLOSE(close);
	
	        /* <REQ></REQ> */
	        OperateTicket.TICKET.REQ req = new OperateTicket.TICKET.REQ();
	        req.setIdReq(idReq);
	        req.setReqName(""); // constante
	        req.setReqLname1(""); // constante
	        req.setReqLname2(""); // constante
	        req.setReqCif(""); // constante
	        req.setReqEmpl(idUser);
	        /* <ASGTO></ASGTO>*/
	        OperateTicket.TICKET.ASGTO asgto = new OperateTicket.TICKET.ASGTO();
	        asgto.setIdAsg(""); // constante
	        asgto.setIdUser(idUser);
	        /* <COMM></COMM>*/
	        //OperateTicket.TICKET.COMM comm = new OperateTicket.TICKET.COMM();
	        //comm.setName(""); // constante
	        //comm.setLName1(""); // constante
	        //comm.setLName2(""); // constante
	        //comm.setInChannel("TELF"); // constante
	        //comm.setValue((tareaAviso.getTelefonoAviso() != null) ? tareaAviso.getTelefonoAviso() : "");
	        //comm.setComent(""); // constante
	        //comm.setOutChannel(""); // constante
	        //comm.setFrom(tareaAviso.getHorarioDesde());
	        //comm.setTo(tareaAviso.getHorarioHasta());
	        /* <OPCOD></OPCOD>*/
	        OperateTicket.TICKET.OPCOD opcod = new OperateTicket.TICKET.OPCOD();
	        opcod.setCodKey1(Integer.valueOf(tareaAviso.getTipoAviso1()));
	        opcod.setCodKey2(Integer.valueOf(tareaAviso.getMotivo1()));
	        /* <CLCOD></CLCOD>*/
	        OperateTicket.TICKET.CLCOD clcod = new OperateTicket.TICKET.CLCOD();
	        clcod.setCodKey3(""); // constante
	        clcod.setCodKey4(""); // constante
	
	        operateTicket.getTICKET().setREQ(req);
	        operateTicket.getTICKET().setASGTO(asgto);
	        //operateTicket.getTICKET().setCOMM(comm);
	        operateTicket.getTICKET().setOPCOD(opcod);
	        operateTicket.getTICKET().setCLCOD(clcod);
	
	
	        /* <CODIF></CODIF> */
	        if(tareaAviso.getTipoAviso1() != null) {
	            OperateTicket.TICKET.CODIFICATIONS.CODIF createCODIF = new OperateTicket.TICKET.CODIFICATIONS.CODIF();
	            createCODIF.setCount(1);    // constante
	            createCODIF.setIdProblem(Integer.parseInt(tareaAviso.getMotivo1()));
	            createCODIF.setIdType(Integer.parseInt(tareaAviso.getTipoAviso1()));
	            createCODIF.setIdItemIBS("");
	            codifications.getCODIF().add(createCODIF);
	        }
	
	        /* <CODIF></CODIF> */
	        if(tareaAviso.getTipoAviso2() != null) {
	            OperateTicket.TICKET.CODIFICATIONS.CODIF createCODIF = new OperateTicket.TICKET.CODIFICATIONS.CODIF();
	            createCODIF.setCount(1);    // constante
	            createCODIF.setIdProblem(Integer.parseInt(tareaAviso.getMotivo2()));
	            createCODIF.setIdType(Integer.parseInt(tareaAviso.getTipoAviso2()));
	            createCODIF.setIdItemIBS("");
	            codifications.getCODIF().add(createCODIF);
	        }
	
	        /* <CODIF></CODIF> */
	        if(tareaAviso.getTipoAviso3() != null) {
	            OperateTicket.TICKET.CODIFICATIONS.CODIF createCODIF = new OperateTicket.TICKET.CODIFICATIONS.CODIF();
	            createCODIF.setCount(1);    // constante
	            createCODIF.setIdProblem(Integer.parseInt(tareaAviso.getMotivo3()));
	            createCODIF.setIdType(Integer.parseInt(tareaAviso.getTipoAviso3()));
	            createCODIF.setIdItemIBS("");
	            codifications.getCODIF().add(createCODIF);
	        }
	
	        operateTicket.getTICKET().setCODIFICATIONS(codifications);
	
	        /*
	         * <USER></USER>
	         */
	        operateTicket.getUSER().setIdUser(idUser);
	        operateTicket.getUSER().setIdCountry(Integer.parseInt(idCountry));
	        operateTicket.getUSER().setIdLang(idLanguage);
	        operateTicket.getUSER().setT("NOSESSION");  // constante
	
	
	        /*
	         *
	         * <TICKET></TICKET>
	         */
	        operateTicket.getTICKET().setNumTicket(tareaAviso.getIdAviso());
	        operateTicket.getTICKET().setNumInst(Integer.parseInt(tareaAviso.getNumeroInstalacion()));
	        operateTicket.getTICKET().setObserv(tareaAviso.getObservaciones());
	        operateTicket.getTICKET().setCodZIP(Integer.valueOf(installationData.getCodigoPostal()));
	        operateTicket.getTICKET().setCloseTicket(0); // constante
	        operateTicket.getTICKET().setDataAditional(""); // constante
	        operateTicket.getTICKET().setNoteClose(""); // constante
	        operateTicket.getTICKET().setMorDebt(0); // constante
	        operateTicket.getTICKET().setTypePanel(installationData.getPanel());
	
	
	
	        String xmlCreateTicket = xmlMarshaller.marshalObject(operateTicket);
	        xmlCreateTicket = xmlCreateTicket.replaceAll("\n", "");
	
	        String xmlResult = wsTickets.updateTicket(xmlCreateTicket);
	
	        DATA data = xmlMarshaller.unmarshalData(xmlResult);
	
	
	
	        LOGGER.debug("xmlCreateTicket: {} xmlResult:{}", xmlCreateTicket, xmlResult);
	
	        /*
	        <DATA>
	         <TICKET numTK="11504305" msg="Aviso actualizado." />
	          <ERR>
	           <UPDATE cod="-1" desc="Ticket Actualizado con éxito" />
	         </ERR>
	        </DATA>
	         */
	
	        if(data.getERR() != null && data.getERR().getUPDATE().getCod() == -1) {
                LOGGER.debug("Sucessfully updated Task {}", tareaAviso);
            } else {
                LOGGER.error("Error updating Task {}" , tareaAviso);
                throw new BusinessException(BusinessException.ErrorCode.ERROR_FINALIZE_TASK,data.getERR().getCod(),data.getERR().getDesc());
            }
	
	    }catch(Exception e){
            LOGGER.error(e.getMessage(),e);
			throw new FrameworkException(e);
		}

    }

    /**
     * @param naviso   nº de aviso
     * @param gblidusr matrícula del usuario: recibido de IWS en parámetro bp_agent
     * @param idaplaza tipo de aplazamiento: si lo admite, dejarlo vacío. Si no, poner “APR”
     * @param fhasta   fecha a la que se aplaza dd/mm/aaaa
     * @return
     * @throws Exception
     */
    public boolean delayTicket(Integer naviso, String gblidusr, String idaplaza, Date fhasta) throws Exception {

        String cnota = ""; // constante
        boolean result = false;
        try {
            List<RowErrorAA> rowErrorAAs = spAvisosOperaciones.aplazarAviso(naviso, gblidusr, idaplaza, new SimpleDateFormat("dd/MM/yyyy' 'HH:mm:ss").format(fhasta), cnota);

            if (rowErrorAAs != null && rowErrorAAs.size() == 1
                    && ((RowErrorAA) ((List) rowErrorAAs).get(0)).getReturnCode() != null
                    && ((RowErrorAA) ((List) rowErrorAAs).get(0)).getReturnCode().equals(new BigInteger("0"))) {
                result = true;
            } else if (rowErrorAAs != null && !rowErrorAAs.isEmpty()) {
                LOGGER.error("Error aplazando aviso {}", naviso);
                result = false;
            }

        } catch (DataServiceFault e) {
            LOGGER.error("Error aplazando aviso", e);
            return false;
        }
        return result;

    }


    /**
     * @param idAviso                          nº de aviso
     * @param idAgente                         matrícula del usuario: recibido de IWS en parámetro bp_agent
     * @param codTipoCierre                    código del tipo de cierre, seleccionado por pantalla. Valor de la tabla TIPOCIERRE
     * @param codTipoCierreAdicional           datos adicionales de cierre, seleccionado por pantalla. Valor de la tabla DATOADICIONAL
     * @param finalizarDesdeCrearMantenimiento valor de la tabla ESTADOS: “2” si se finaliza, “3” si se finaliza por crear un Mantenimiento
     * @param idMantenimiento Identificador del mantenimiento creado desde la pantalla externa
     *
     * @return
     * @throws Exception
     */
    public boolean closeTicket(Integer idAviso,
                               String idAgente,
                               String codTipoCierre,
                               Integer codTipoCierreAdicional,
                               boolean finalizarDesdeCrearMantenimiento,
                               Integer idMantenimiento ) throws Exception {

        Integer deuda = 0; // constante
        Integer idmante = 0; // constante
        String branch = ""; // constante
        String nota = ""; // nota de cierre asociada a las observaciones del Aviso

        // “2” si se finaliza 	“3” si se finaliza por crear un Mantenimiento
        Integer statusdest = finalizarDesdeCrearMantenimiento ? 2 : 3;

        boolean result = false;
        try {

            List<RowErrorCA> rowErrorCAs = spAvisosOperaciones.cerrarAviso(idAviso, idAgente, codTipoCierre, nota, statusdest,
                    deuda, codTipoCierreAdicional, idmante, branch);

            if (rowErrorCAs != null && rowErrorCAs.size() == 1
                    && ((RowErrorCA) ((List) rowErrorCAs).get(0)).getReturnCode() != null
                    && ((RowErrorCA) ((List) rowErrorCAs).get(0)).getReturnCode().equals(new BigInteger("0"))) {
                result = true;
            } else if (rowErrorCAs != null && !rowErrorCAs.isEmpty()) {
                LOGGER.error("Error cerrando aviso {}", idAviso);
                result = false;
            }
        } catch (Exception e) {
            LOGGER.error("Error cerrando aviso", e);
            throw e;
        }

        return result;
    }


    /**
     * @param naviso   nº de aviso
     * @return
     * @throws Exception
     */
    public boolean unmarkTicket(Integer naviso) throws Exception {

        boolean result = false;

        try {
            spAIOTAREAS2PortType.setAvisoNoCargado(naviso);
            result = true; // TODO devuelve void
        } catch (Exception e){
        // TODO no devuelve la excepcion DataServiceFault
        //catch (DataServiceFault e) {
            LOGGER.error("Error desmarcando aviso", e);
            return false;
        }
        return result;

    }

}
