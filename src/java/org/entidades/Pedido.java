/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author David
 */
@Entity
@Table(name = "pedidos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p")
    , @NamedQuery(name = "Pedido.findByIdPedido", query = "SELECT p FROM Pedido p WHERE p.idPedido = :idPedido")
    , @NamedQuery(name = "Pedido.findByNombreProyecto", query = "SELECT p FROM Pedido p WHERE p.nombreProyecto = :nombreProyecto")
    , @NamedQuery(name = "Pedido.findByDescripcion", query = "SELECT p FROM Pedido p WHERE p.descripcion = :descripcion")
    , @NamedQuery(name = "Pedido.findByRealizoPago", query = "SELECT p FROM Pedido p WHERE p.realizoPago = :realizoPago")})
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pedido")
    private Integer idPedido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombre_proyecto")
    private String nombreProyecto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "realizo_pago")
    private short realizoPago;
    @JoinColumn(name = "personas_id_persona", referencedColumnName = "id_persona")
    @ManyToOne(optional = false)
    private Persona personasIdPersona;
    @JoinColumn(name = "materias_prima_id_materia", referencedColumnName = "id_materia")
    @ManyToOne(optional = false)
    private MateriaPrima materiasPrimaIdMateria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedidosIdPedido")
    private List<Proyecto> proyectoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedidosIdPedido")
    private List<DetallePedido> detallePedidoList;

    public Pedido() {
    }

    public Pedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Pedido(Integer idPedido, String nombreProyecto, String descripcion, short realizoPago) {
        this.idPedido = idPedido;
        this.nombreProyecto = nombreProyecto;
        this.descripcion = descripcion;
        this.realizoPago = realizoPago;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public short getRealizoPago() {
        return realizoPago;
    }

    public void setRealizoPago(short realizoPago) {
        this.realizoPago = realizoPago;
    }

    public Persona getPersonasIdPersona() {
        return personasIdPersona;
    }

    public void setPersonasIdPersona(Persona personasIdPersona) {
        this.personasIdPersona = personasIdPersona;
    }

    public MateriaPrima getMateriasPrimaIdMateria() {
        return materiasPrimaIdMateria;
    }

    public void setMateriasPrimaIdMateria(MateriaPrima materiasPrimaIdMateria) {
        this.materiasPrimaIdMateria = materiasPrimaIdMateria;
    }

    @XmlTransient
    public List<Proyecto> getProyectoList() {
        return proyectoList;
    }

    public void setProyectoList(List<Proyecto> proyectoList) {
        this.proyectoList = proyectoList;
    }

    @XmlTransient
    public List<DetallePedido> getDetallePedidoList() {
        return detallePedidoList;
    }

    public void setDetallePedidoList(List<DetallePedido> detallePedidoList) {
        this.detallePedidoList = detallePedidoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPedido != null ? idPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.idPedido == null && other.idPedido != null) || (this.idPedido != null && !this.idPedido.equals(other.idPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.entidades.Pedido[ idPedido=" + idPedido + " ]";
    }
    
}
