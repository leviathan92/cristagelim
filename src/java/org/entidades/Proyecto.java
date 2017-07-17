/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author David
 */
@Entity
@Table(name = "proyectos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proyecto.findAll", query = "SELECT p FROM Proyecto p")
    , @NamedQuery(name = "Proyecto.findByIdProyecto", query = "SELECT p FROM Proyecto p WHERE p.idProyecto = :idProyecto")
    , @NamedQuery(name = "Proyecto.findByNombreProyecto", query = "SELECT p FROM Proyecto p WHERE p.nombreProyecto = :nombreProyecto")
    , @NamedQuery(name = "Proyecto.findByFechaInicio", query = "SELECT p FROM Proyecto p WHERE p.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Proyecto.findByTiempoEstimado", query = "SELECT p FROM Proyecto p WHERE p.tiempoEstimado = :tiempoEstimado")})
public class Proyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_proyecto")
    private Integer idProyecto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombre_proyecto")
    private String nombreProyecto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tiempo_estimado")
    private int tiempoEstimado;
    @JoinColumn(name = "personas_id_persona", referencedColumnName = "id_persona")
    @ManyToOne(optional = false)
    private Persona personasIdPersona;
    @JoinColumn(name = "dificultades_id_dificultad", referencedColumnName = "id_dificultad")
    @ManyToOne(optional = false)
    private Dificultad dificultadesIdDificultad;
    @JoinColumn(name = "estados_id_estado", referencedColumnName = "id_estado")
    @ManyToOne(optional = false)
    private Estado estadosIdEstado;
    @JoinColumn(name = "pedidos_id_pedido", referencedColumnName = "id_pedido")
    @ManyToOne(optional = false)
    private Pedido pedidosIdPedido;

    public Proyecto() {
    }

    public Proyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Proyecto(Integer idProyecto, String nombreProyecto, Date fechaInicio, int tiempoEstimado) {
        this.idProyecto = idProyecto;
        this.nombreProyecto = nombreProyecto;
        this.fechaInicio = fechaInicio;
        this.tiempoEstimado = tiempoEstimado;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public int getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(int tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }

    public Persona getPersonasIdPersona() {
        return personasIdPersona;
    }

    public void setPersonasIdPersona(Persona personasIdPersona) {
        this.personasIdPersona = personasIdPersona;
    }

    public Dificultad getDificultadesIdDificultad() {
        return dificultadesIdDificultad;
    }

    public void setDificultadesIdDificultad(Dificultad dificultadesIdDificultad) {
        this.dificultadesIdDificultad = dificultadesIdDificultad;
    }

    public Estado getEstadosIdEstado() {
        return estadosIdEstado;
    }

    public void setEstadosIdEstado(Estado estadosIdEstado) {
        this.estadosIdEstado = estadosIdEstado;
    }

    public Pedido getPedidosIdPedido() {
        return pedidosIdPedido;
    }

    public void setPedidosIdPedido(Pedido pedidosIdPedido) {
        this.pedidosIdPedido = pedidosIdPedido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProyecto != null ? idProyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proyecto)) {
            return false;
        }
        Proyecto other = (Proyecto) object;
        if ((this.idProyecto == null && other.idProyecto != null) || (this.idProyecto != null && !this.idProyecto.equals(other.idProyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.entidades.Proyecto[ idProyecto=" + idProyecto + " ]";
    }
    
}
