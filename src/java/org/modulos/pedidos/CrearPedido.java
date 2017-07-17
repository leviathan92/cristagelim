/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modulos.pedidos;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.dao.MateriaPrimaFacadeLocal;
import org.dao.PedidoFacadeLocal;
import org.dao.PersonaFacadeLocal;
import org.entidades.MateriaPrima;
import org.entidades.Pedido;
import org.entidades.Persona;

/**
 *
 * @author David
 */
@Named(value = "crearPedido")
@ViewScoped
public class CrearPedido implements Serializable {

    @EJB
    private PersonaFacadeLocal pfl;
    @EJB
    private PedidoFacadeLocal pedidoFacadeLocal;
    @EJB
    private MateriaPrimaFacadeLocal materiaPrimaFacadeLocal;
    private int idPedido;
    private String nombreProyecto;
    private String descripcion;
    private short realizoPago;
    private Persona p;

    public CrearPedido() {

    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
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

    public String crear() {
        p = pfl.find(1);
        MateriaPrima materiaPrima = materiaPrimaFacadeLocal.find(1);
        Pedido pedido = new Pedido(null, nombreProyecto, descripcion, realizoPago);
        pedido.setMateriasPrimaIdMateria(materiaPrima);
        pedido.setPersonasIdPersona(p);
        pedidoFacadeLocal.create(pedido);
        return "/admin/Pedidos/listarPedidos.xhtml?faces-redirect=true";
        
    }
    
    public String cancelar() {
        return "/admin/Pedidos/listarPedidos.xhtml?faces-redirect=true";

    }

}
