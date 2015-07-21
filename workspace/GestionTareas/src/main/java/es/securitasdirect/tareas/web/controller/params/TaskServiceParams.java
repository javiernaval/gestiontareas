package es.securitasdirect.tareas.web.controller.params;

/**
 * Constantes de los parametros que nos pasan desde el IWS.
 * Al documento Integracion_IWS_Web_DiseñoTecnico_v2.5 contiene un listado y explicaciones.
 */
public interface TaskServiceParams {

    public static String NUMERO_INSTALACION = "ins_no";
    public static String COMPENSACION = "compensation";


    /**
     * TAREA COMUNES
     */
    public static String TAREA_COMMONS_PERSONA_CONTACTO = "NOMBRE";

    /**
     * Generales
     */
    //Ejemplo DATE_FORMAT                    = "2015-02-18T03:05:20.000+01:00";
    public static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";



    /*
     * Tarea de tipo Listado Assistant
     */
    public static String ASSISTANT_INSTALACION = "instalacion";
    public static String ASSISTANT_MANTENIMIENTO = "";
    public static String ASSISTANT_TECNICO = "instalacion";
    public static String ASSISTANT_DEPARTAMENTO = "instalacion";
    public static String ASSISTANT_GRUPOPANEL = "instalacion";
    public static String ASSISTANT_TOTALSINIVA = "instalacion";
    public static String ASSISTANT_TOTALCONIVA = "instalacion";
    public static String ASSISTANT_NPARTE = "instalacion";
    public static String ASSISTANT_ARCHIVO_FECHA = "instalacion";
    public static String ASSISTANT_SUBIDA_INC_FECHA = "instalacion";
    public static String ASSISTANT_PAGO_FECHA = "instalacion";
    public static String ASSISTANT_INCIDENCIA = "instalacion";
    public static String ASSISTANT_SUBINCIDENCIA = "instalacion";
    public static String ASSISTANT_SOLICITUD = "instalacion";
    public static String ASSISTANT_CAMBIOS = "instalacion";
    public static String ASSISTANT_BO_GESTION_FECHA = "instalacion";
    public static String ASSISTANT_BO_MATRICULA = "instalacion";
    public static String ASSISTANT_BO_RECEPCION_FECHA = "instalacion";
    public static String ASSISTANT_BO_EMPRESA_PARTICULAR = "instalacion";
    public static String ASSISTANT_BO_COMENTARIOS = "instalacion";
    public static String ASSISTANT_CONTACTO_TELEFONO = "instalacion";


    /**
     * Tarea de tipo Encuesta mantenimientos
     */
    public static String ENCUESTAMNTOS_MANTENIMIENTO = "MANTENIMIENTO";
    public static String ENCUESTAMNTOS_TECNICO = "TECNICO";
    public static String ENCUESTAMNTOS_RESPONSABLE = "JE";
    public static String ENCUESTAMNTOS_CENTROCOSTE = "CC";
    public static String ENCUESTAMNTOS_RAZON = "RAZON";
    public static String ENCUESTAMNTOS_SOLUCION = "SOLUCION";
    public static String ENCUESTAMNTOS_COMPROMISO = "COMPROMISO";
    public static String ENCUESTAMNTOS_DPTO_DESTINO = "DPTO_DESTINO";

    /**
     * Tarea de tipo Encuesta mercados
     */
    public static String ENCUESTASMKT_FECHA = "mantenimiento";
    public static String ENCUESTASMKT_MOTIVO = "mantenimiento";

    public static String KEYBOX_CONTRATO = "KEYBOX_CONTRATO";
    public static String KEYBOX_FECHA_FACTURA = "KEYBOX_FECHA_FACTURA";
    public static String KEYBOX_NUMERO_FACTURA = "KEYBOX_NUMERO_FACTURA";
    public static String KEYBOX_IMPORTE_LINEA = "KEYBOX_IMPORTE_LINEA";
    public static String KEYBOX_ID_ITEM = "KEYBOX_ID_ITEM";

    public  static String OTRASCAMPANAS_CAMPO1 = "campo";
    public  static String OTRASCAMPANAS_CAMPO2 = "campo";
    public  static String OTRASCAMPANAS_CAMPO3 = "campo";
    public  static String OTRASCAMPANAS_TIPOTAREA = "campo";

    public  static String LIMPIEZA_CUOTA_CONTRATO = "campo";
    public static  String LIMPIEZA_CUOTA_DPT_ASIGNADO = "campo";
    public static  String LIMPIEZA_CUOTA_DESCRIPTCION = "campo";

    /*Tarea Mantenimiento */
    /**
     *
     1.1.1.1.2.	Mapeo tarea mantenimiento
     Campo pantalla	Datos WS
     ESTADO                 NOMBRE_CAMPO            NOMBRE_CAMPO_WS     COMENTARIOS
     --installationData--   numeroInstalacion	    INSTALACION
     --installationdata--   titular	                                    (se lee del WS de getInstalacion [ref])
     --installationData--   Persona de contacto     NOMBRE              (no existe en el modelo)
     --installationData--   panel 	                                    WS de getInstalacion[ref]   (no viene en el modelo)
     --installationdata--   telefono	            CONTACT_INFO
     --installationData--   version                                     (no viene en el modelo)	WS de getInstalacion[ref]
     --CONFIRMAR--          numeroContrato	        CTR_NO              (confirmar)
     --NO_MOSTRADO--        tipoMantenimiento       TIPO_MANTENIMIENTO  (no viene en el modelo)
     --X--                  direccion	            DIRECCION
     --CONFIRMAR--          fechaEvento	            F_CREACION_TAREA?
     --X--                  ciudad	                CIUDAD
     --CONFIRMAR--          agenteAsignado	        AGENT_ID            (CONFIRMAR, SI ES EL LOGIN_ID HAY QUE LLAMAR A UN WS PARA TRADUCIRLO)
     --CONFIRMAR--                                  Tipo cancelacion    (no viene en el modelo, o es tipificacion?)
     --MODIFICACION--       tipoCancelacion         Tipo cancelacion    (no viene en el modelo, o es tipificacion?)
     --CONFIRMAR--                                  Texto cancelacion   (no viene en el modelo, o es tipificacion?)
     --MODIFICACION--       textCancelacion         Texto cancelacion   (no viene en el modelo, o es tipificacion?)
     --X--                  telefono1               TELEFONO1           (no viene en el modelo)
     --X--                  telefono2               TELEFONO2           (no viene en el modelo)
     --X--                  telefono3               TELEFONO3           (no viene en el modelo)
     --DESCONOCIDO--        Key1
     --DESCONOCIDO--        Key2
     --NO_INDICADO--        agenteCierre
     --NO_INDICADO--        opcionTipificacion
     --NO_INDICADO--        textoCancelacion
     */
    //INSTALLATION DATA
    public static String TAREA_MANTENIMIENTO_INS_NUMERO_INSTALACION = "INSTALACION";
    public static String TAREA_MANTENIMIENTO_INS_PERSONA_CONTACTO = "NOMBRE";
    public static String TAREA_MANTENIMIENTO_INS_TELEFONO = "CONTACT_INFO";
    //TAREA MANTENIMIENTO
    public static String TAREA_MANTENIMIENTO_NUMERO_CONTRATO = "CTR_NO";
    public static String TAREA_MANTENIMIENTO_TIPO_MANTENIMIENTO = "TIPO_MANTENIMIENTO";
    public static String TAREA_MANTENIMIENTO_DIRECCION = "DIRECCION";
    public static String TAREA_MANTENIMIENTO_CIUDAD = "CIUDAD";
    public static String TAREA_MANTENIMIENTO_FECHAEVENTO_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
    public static String TAREA_MANTENIMIENTO_FECHAEVENTO= "F_CREACION_TAREA";
    public static String TAREA_MANTENIMIENTO_AGENTEASIGNADO= "AGENT_ID";
    public static String TAREA_MANTENIMIENTO_TIPO_CANCELACION = "Tipo cancelacion";
    public static String TAREA_MANTENIMIENTO_TELEFONO_1 = "TELEFONO1";
    public static String TAREA_MANTENIMIENTO_TELEFONO_2 = "TELEFONO2";
    public static String TAREA_MANTENIMIENTO_TELEFONO_3 = "TELEFONO3";
    public static String TAREA_MANTENIMIENTO_KEY1= "TAREA_MANTENIMIENTO_KEY1";
    public static String TAREA_MANTENIMIENTO_KEY2= "TAREA_MANTENIMIENTO_KEY2";
    //No se utiliza en pantalla ni tenemos equivalencia.
    public static String TAREA_MANTENIMIENTO_AGENTECIERRE= "TAREA_MANTENIMIENTO_AGENTECIERRE";
    public static String TAREA_MANTENIMIENTO_OPCIONTIPIFICACION= "TAREA_MANTENIMIENTO_OPCIONTIPIFICACION";
    public static String TAREA_MANTENIMIENTO_TEXTO_CANCELACION = "Tipo cancelacion";


    /**
     * NOTIFICATION TASK
     */



    /**
     * TAREA_AVISO
     */
    //Servicio previo de donde se obtiene el ID_AVISO
    public static String TAREA_AVISO_CALLING_LIST_RESPONSE_ID_AVISO = "ID_AVISO";
    //Servicio de Consulta de Aviso



}
