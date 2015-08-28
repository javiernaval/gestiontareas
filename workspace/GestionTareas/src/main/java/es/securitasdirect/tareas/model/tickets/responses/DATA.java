//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.25 at 10:59:55 AM CEST 
//


package es.securitasdirect.tareas.model.tickets.responses;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TICKET">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="numTK" type="{http://www.w3.org/2001/XMLSchema}int" />
 *                 &lt;attribute name="status" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="SREQ">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="numSVRQ" type="{http://www.w3.org/2001/XMLSchema}int" />
 *                 &lt;attribute name="status" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ERR">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="UPDATE">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="cod" type="{http://www.w3.org/2001/XMLSchema}int" />
 *                           &lt;attribute name="desc" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="cod" type="{http://www.w3.org/2001/XMLSchema}int" />
 *                 &lt;attribute name="desc" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "ticket",
    "sreq",
    "err"
})
@XmlRootElement(name = "DATA")
public class DATA {

    @XmlElement(name = "TICKET", required = true)
    protected DATA.TICKET ticket;
    @XmlElement(name = "SREQ", required = true)
    protected DATA.SREQ sreq;
    @XmlElement(name = "ERR", required = true)
    protected DATA.ERR err;

    /**
     * Gets the value of the ticket property.
     * 
     * @return
     *     possible object is
     *     {@link DATA.TICKET }
     *     
     */
    public DATA.TICKET getTICKET() {
        return ticket;
    }

    /**
     * Sets the value of the ticket property.
     * 
     * @param value
     *     allowed object is
     *     {@link DATA.TICKET }
     *     
     */
    public void setTICKET(DATA.TICKET value) {
        this.ticket = value;
    }

    /**
     * Gets the value of the sreq property.
     * 
     * @return
     *     possible object is
     *     {@link DATA.SREQ }
     *     
     */
    public DATA.SREQ getSREQ() {
        return sreq;
    }

    /**
     * Sets the value of the sreq property.
     * 
     * @param value
     *     allowed object is
     *     {@link DATA.SREQ }
     *     
     */
    public void setSREQ(DATA.SREQ value) {
        this.sreq = value;
    }

    /**
     * Gets the value of the err property.
     * 
     * @return
     *     possible object is
     *     {@link DATA.ERR }
     *     
     */
    public DATA.ERR getERR() {
        return err;
    }

    /**
     * Sets the value of the err property.
     * 
     * @param value
     *     allowed object is
     *     {@link DATA.ERR }
     *     
     */
    public void setERR(DATA.ERR value) {
        this.err = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="UPDATE">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="cod" type="{http://www.w3.org/2001/XMLSchema}int" />
     *                 &lt;attribute name="desc" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="cod" type="{http://www.w3.org/2001/XMLSchema}int" />
     *       &lt;attribute name="desc" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "update"
    })
    public static class ERR {

        @XmlElement(name = "UPDATE", required = true)
        protected DATA.ERR.UPDATE update;
        @XmlAttribute
        protected String cod;
        @XmlAttribute
        protected String desc;

        /**
         * Gets the value of the update property.
         * 
         * @return
         *     possible object is
         *     {@link DATA.ERR.UPDATE }
         *     
         */
        public DATA.ERR.UPDATE getUPDATE() {
            return update;
        }

        /**
         * Sets the value of the update property.
         * 
         * @param value
         *     allowed object is
         *     {@link DATA.ERR.UPDATE }
         *     
         */
        public void setUPDATE(DATA.ERR.UPDATE value) {
            this.update = value;
        }

        /**
         * Gets the value of the cod property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public String getCod() {
            return cod;
        }

        /**
         * Sets the value of the cod property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setCod(String value) {
            this.cod = value;
        }

        /**
         * Gets the value of the desc property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDesc() {
            return desc;
        }

        /**
         * Sets the value of the desc property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDesc(String value) {
            this.desc = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;attribute name="cod" type="{http://www.w3.org/2001/XMLSchema}int" />
         *       &lt;attribute name="desc" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class UPDATE {

            @XmlAttribute
            protected Integer cod;
            @XmlAttribute
            protected String desc;

            /**
             * Gets the value of the cod property.
             * 
             * @return
             *     possible object is
             *     {@link Integer }
             *     
             */
            public Integer getCod() {
                return cod;
            }

            /**
             * Sets the value of the cod property.
             * 
             * @param value
             *     allowed object is
             *     {@link Integer }
             *     
             */
            public void setCod(Integer value) {
                this.cod = value;
            }

            /**
             * Gets the value of the desc property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDesc() {
                return desc;
            }

            /**
             * Sets the value of the desc property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDesc(String value) {
                this.desc = value;
            }

        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attribute name="numSVRQ" type="{http://www.w3.org/2001/XMLSchema}int" />
     *       &lt;attribute name="status" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class SREQ {

        @XmlAttribute
        protected Integer numSVRQ;
        @XmlAttribute
        protected String status;

        /**
         * Gets the value of the numSVRQ property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getNumSVRQ() {
            return numSVRQ;
        }

        /**
         * Sets the value of the numSVRQ property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setNumSVRQ(Integer value) {
            this.numSVRQ = value;
        }

        /**
         * Gets the value of the status property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getStatus() {
            return status;
        }

        /**
         * Sets the value of the status property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setStatus(String value) {
            this.status = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;attribute name="numTK" type="{http://www.w3.org/2001/XMLSchema}int" />
     *       &lt;attribute name="status" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class TICKET {

        @XmlAttribute
        protected Integer numTK;
        @XmlAttribute
        protected String status;

        /**
         * Gets the value of the numTK property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getNumTK() {
            return numTK;
        }

        /**
         * Sets the value of the numTK property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setNumTK(Integer value) {
            this.numTK = value;
        }

        /**
         * Gets the value of the status property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getStatus() {
            return status;
        }

        /**
         * Sets the value of the status property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setStatus(String value) {
            this.status = value;
        }

    }

}
